package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.localizer.config.PinpointConfig;
import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.ILocalizer;
import dev.nullftc.wpimath.geometry.Twist2d;

public class PinpointLocalizer implements ILocalizer {
    private final GoBildaPinpointDriver pinpoint;

    private Pose2D currPose;
    private Vector2d currVelocity;

    public PinpointLocalizer(GoBildaPinpointDriver pinpoint) {
        this.pinpoint = pinpoint;
        this.currPose = new Pose2D(DistanceUnit.MM, 0.0, 0.0, AngleUnit.RADIANS, 0.0);
        this.currVelocity = new Vector2d();

        if (PinpointConfig.ENABLE_CUSTOM_YAW_SCALAR) {
            pinpoint.setYawScalar(PinpointConfig.YAW_SCALAR);
        }

        /**
         * If you are not using official goBILDA Odometry pods you should change the below line to
         * GoBildaPinpointDriver#setEncoderResolution(double, DistanceUnit), where double is the ticks per/unit.
         */
        pinpoint.setEncoderResolution(PinpointConfig.OFFICIAL_POD_TYPE);

        pinpoint.setEncoderDirections(
                PinpointConfig.X_FLIPPED ? GoBildaPinpointDriver.EncoderDirection.REVERSED : GoBildaPinpointDriver.EncoderDirection.FORWARD,
                PinpointConfig.Y_FLIPPED ? GoBildaPinpointDriver.EncoderDirection.REVERSED : GoBildaPinpointDriver.EncoderDirection.FORWARD
        );

        pinpoint.setOffsets(PinpointConfig.X_OFFSET, PinpointConfig.Y_OFFSET, PinpointConfig.OFFSET_UNIT);

        this.pinpoint.recalibrateIMU();
        this.pinpoint.resetPosAndIMU();
    }

    @NonNull
    @Override
    public Pose2D getPose() {
        return currPose;
    }

    @Override
    public void setPose(@NonNull Pose2D pose2D) {
        pinpoint.setPosition(pose2D);
    }

    @Override
    public double getAngle() {
        return currPose.getHeading(AngleUnit.RADIANS);
    }

    @Override
    public void setAngle(double v) {
        pinpoint.setHeading(v, AngleUnit.RADIANS);
    }

    @NonNull
    @Override
    public Vector2d getVelocity() {
        return currVelocity;
    }

    @Override
    public void update() {
        currPose = pinpoint.getPosition();
        double vx = pinpoint.getVelX(DistanceUnit.METER);
        double vy = pinpoint.getVelY(DistanceUnit.METER);
        currVelocity.set(vx, vy);
    }

    @NonNull
    @Override
    public Twist2d toTwist2d(Pose2D start, Pose2D end) {
        double dx = end.getX(DistanceUnit.METER) - start.getX(DistanceUnit.METER);
        double dy = end.getY(DistanceUnit.METER) - start.getY(DistanceUnit.METER);
        double dTheta = end.getHeading(AngleUnit.RADIANS) - start.getHeading(AngleUnit.RADIANS);

        return new Twist2d(dx, dy, dTheta);
    }
}
