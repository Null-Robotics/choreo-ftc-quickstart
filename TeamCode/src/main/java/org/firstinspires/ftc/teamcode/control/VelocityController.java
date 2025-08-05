package org.firstinspires.ftc.teamcode.control;

import org.joml.Vector2d;

public class VelocityController implements Controller<Vector2d> {
    private final double kP;

    public VelocityController(double kP) {
        this.kP = kP;
    }

    @Override
    public Vector2d update(Vector2d current, Vector2d setpoint) {
        double errorX = setpoint.x - current.x;
        double errorY = setpoint.y - current.y;

        double controlX = kP * errorX;
        double controlY = kP * errorY;

        return new Vector2d(controlX, controlY);
    }

    @Override
    public void reset() {

    }
}
