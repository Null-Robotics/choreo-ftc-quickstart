package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController implements Controller<Double> {
    private PIDCoefficients coefficients;

    private double integral = 0.0;
    private double previousError = 0.0;

    private final ElapsedTime loopTimer = new ElapsedTime();

    public PIDController(PIDCoefficients coefficients) {
        this.coefficients = coefficients;
    }

    public void setCoefficients(PIDCoefficients coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public void reset() {
        integral = 0.0;
        previousError = 0.0;
        loopTimer.reset();
    }

    @Override
    public Double update(Double current, Double setpoint) {
        double dt = loopTimer.seconds();
        loopTimer.reset();

        double error = setpoint - current;
        integral += error * dt;

        double derivative = (dt > 0) ? (error - previousError) / dt : 0.0;
        previousError = error;

        return coefficients.p * error + coefficients.i * integral + coefficients.d * derivative;
    }
}
