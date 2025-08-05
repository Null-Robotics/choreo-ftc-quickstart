package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;

public class RamseteController implements Controller<Pose2D> {
    private final double b;
    private final double zeta;

    public RamseteController(double b, double zeta) {
        this.b = b;
        this.zeta = zeta;
    }

    @Override
    public Pose2D update(Pose2D current, Pose2D setpoint) {
        double xError = setpoint.getX(Constants.PREFERRED_DISTANCE_UNIT) - current.getX(Constants.PREFERRED_DISTANCE_UNIT);
        double yError = setpoint.getY(Constants.PREFERRED_DISTANCE_UNIT) - current.getY(Constants.PREFERRED_DISTANCE_UNIT);

        double thetaError = normalizeAngle(setpoint.getHeading(Constants.PREFERRED_ANGULAR_UNIT) - current.getHeading(Constants.PREFERRED_ANGULAR_UNIT));

        double cosTheta = Math.cos(current.getHeading(Constants.PREFERRED_ANGULAR_UNIT));
        double sinTheta = Math.sin(current.getHeading(Constants.PREFERRED_ANGULAR_UNIT));

        double errorXRobot = cosTheta * xError + sinTheta * yError;
        double errorYRobot = -sinTheta * xError + cosTheta * yError;

        double spX = setpoint.getX(Constants.PREFERRED_DISTANCE_UNIT);
        double spY = setpoint.getY(Constants.PREFERRED_DISTANCE_UNIT);
        double spH = setpoint.getHeading(Constants.PREFERRED_ANGULAR_UNIT);

        double x = current.getX(Constants.PREFERRED_DISTANCE_UNIT);
        double y = current.getY(Constants.PREFERRED_DISTANCE_UNIT);
        double h = current.getHeading(Constants.PREFERRED_ANGULAR_UNIT);

        double k = 2 * zeta * Math.sqrt(spX * spX + spH * spH);

        double v = spX * Math.cos(thetaError) + b * errorXRobot;
        double omega = spH + b * thetaError + k * errorYRobot;

        return new Pose2D(
                Constants.PREFERRED_DISTANCE_UNIT,
                v,
                0,
                Constants.PREFERRED_ANGULAR_UNIT,
                omega
        );
    }

    @Override
    public void reset() {}

    private double normalizeAngle(double angle) {
        double twoPi = 2.0 * Math.PI;
        angle = angle % twoPi;
        if (angle >= Math.PI) {
            angle -= twoPi;
        } else if (angle < -Math.PI) {
            angle += twoPi;
        }
        return angle;
    }
}
