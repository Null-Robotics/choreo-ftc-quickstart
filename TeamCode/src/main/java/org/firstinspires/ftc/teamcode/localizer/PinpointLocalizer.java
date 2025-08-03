package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.ILocalizer;

public class PinpointLocalizer implements ILocalizer {

    private GoBildaPinpointDriver pinpointDriver;

    public PinpointLocalizer(GoBildaPinpointDriver pinpointDriver) {
        this.pinpointDriver = pinpointDriver;
    }

    @NonNull
    @Override
    public Pose2D getPose() {
        return pinpointDriver.getPosition();
    }

    @Override
    public void setPose(Pose2D pose2D) {
        pinpointDriver.setPosition(pose2D);
    }

    @Override
    public double getAngle() {
        return pinpointDriver.getHeading(AngleUnit.RADIANS);
    }

    @Override
    public void setAngle(double v) {
        pinpointDriver.setHeading(v, AngleUnit.RADIANS);
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
