package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.ILocalizer;

public class TwoWheelLocalizer implements ILocalizer {
    @Override
    public Pose2D getPose() {
        return null;
    }

    @Override
    public void setPose(Pose2D pose2D) {

    }

    @Override
    public double getAngle() {
        return 0;
    }

    @Override
    public void setAngle(double v) {

    }

    @NonNull
    @Override
    public Vector2d getVelocity() {
        return null;
    }

    @Override
    public void update() {

    }
}
