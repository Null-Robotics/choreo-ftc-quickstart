package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDFController implements Controller<Double> {

    private PIDCoefficients coefficients;
    private final double kV, kA, kStatic;

    private double integral = 0.0;
    private double previousError = 0.0;
    private final ElapsedTime loopTimer = new ElapsedTime();

    public PIDFController(PIDCoefficients coefficients, double kV, double kA, double kStatic) {
        this.coefficients = coefficients;
        this.kV = kV;
        this.kA = kA;
        this.kStatic = kStatic;
    }

    public void reset() {
        integral = 0.0;
        previousError = 0.0;
        loopTimer.reset();
    }

    public void setCoefficients(PIDCoefficients coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public Double update(Double current, Double setpoint) {
        double dt = loopTimer.seconds();
        loopTimer.reset();

        double error = setpoint - current;
        integral += error * dt;
        double derivative = (dt > 0) ? (error - previousError) / dt : 0.0;
        previousError = error;

        double pidOutput = coefficients.p * error +
                coefficients.i * integral +
                coefficients.d * derivative;

        double feedforward = kV * setpoint + kStatic;

        return pidOutput + feedforward;
    }

    public Double update(Double current, Double setpoint, Double setpointAccel) {
        double dt = loopTimer.seconds();
        loopTimer.reset();

        double error = setpoint - current;
        integral += error * dt;
        double derivative = (dt > 0) ? (error - previousError) / dt : 0.0;
        previousError = error;

        double pidOutput = coefficients.p * error +
                coefficients.i * integral +
                coefficients.d * derivative;

        double feedforward = kV * setpoint + kA * setpointAccel + kStatic;

        return pidOutput + feedforward;
    }
}
