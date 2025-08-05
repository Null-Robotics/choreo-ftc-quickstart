package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.localizer.config.PinpointConfig;
import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.ILocalizer;
import dev.nullftc.wpimath.geometry.Twist2d;

public class ThreeWheelLocalizer implements ILocalizer {
    @NonNull
    @Override
    public Pose2D getPose() {
        return null;
    }

    @Override
    public void setPose(@NonNull Pose2D pose2D) {

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

    @NonNull
    @Override
    public Twist2d toTwist2d(Pose2D start, Pose2D end) {
        double dx = end.getX(Constants.PREFERRED_DISTANCE_UNIT) - start.getX(Constants.PREFERRED_DISTANCE_UNIT);
        double dy = end.getY(Constants.PREFERRED_DISTANCE_UNIT) - start.getY(Constants.PREFERRED_DISTANCE_UNIT);
        double dTheta = end.getHeading(Constants.PREFERRED_ANGULAR_UNIT) - start.getHeading(Constants.PREFERRED_ANGULAR_UNIT);

        return new Twist2d(dx, dy, dTheta);
    }
}
