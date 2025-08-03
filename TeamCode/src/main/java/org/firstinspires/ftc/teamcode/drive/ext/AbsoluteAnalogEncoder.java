package org.firstinspires.ftc.teamcode.drive.ext;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class AbsoluteAnalogEncoder {
    public static double DEFAULT_RANGE = 3.3;
    public static boolean VALUE_REJECTION = false;
    private final AnalogInput encoder;
    private double offset, analogRange;
    private boolean inverted;

    public AbsoluteAnalogEncoder(AnalogInput enc){
        this(enc, DEFAULT_RANGE);
    }
    public AbsoluteAnalogEncoder(AnalogInput enc, double aRange){
        encoder = enc;
        analogRange = aRange;
        offset = 0;
        inverted = false;
    }

    public AbsoluteAnalogEncoder zero(double off){
        offset = off;
        return this;
    }

    public AbsoluteAnalogEncoder setInverted(boolean invert){
        inverted = invert;
        return this;
    }

    public boolean getDirection() {
        return inverted;
    }

    private double pastPosition = 1;
    public double getCurrentPosition() {
        double pos = norm((!inverted ? 1 - getVoltage() / analogRange : getVoltage() / analogRange) * Math.PI*2 - offset);
        if(!VALUE_REJECTION || Math.abs(normDelta(pastPosition)) > 0.1 || Math.abs(normDelta(pos)) < 1) pastPosition = pos;
        return pastPosition;
    }

    public AnalogInput getEncoder() {
        return encoder;
    }


    public double getVoltage(){
        return encoder.getVoltage();
    }

    private static double norm(double angle) {
        return ((angle % (2 * Math.PI)) + (2 * Math.PI)) % (2 * Math.PI);
    }

    private static double normDelta(double angle) {
        double a = ((angle + Math.PI) % (2 * Math.PI));
        if (a < 0) a += 2 * Math.PI;
        return a - Math.PI;
    }
}