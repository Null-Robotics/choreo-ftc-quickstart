package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.localizer.config.OTOSConfig;
import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.ILocalizer;
import dev.nullftc.wpimath.geometry.Twist2d;

public class OTOSLocalizer implements ILocalizer {
    private final SparkFunOTOS otos;
    private SparkFunOTOS.Pose2D lastSDKPose;
    private Pose2D currPose;
    private Vector2d currVelocity;

    public OTOSLocalizer(SparkFunOTOS otos) {
        this.otos = otos;
        this.lastSDKPose = new SparkFunOTOS.Pose2D(0.0, 0.0, 0.0);
        this.currPose = new Pose2D(DistanceUnit.MM, 0.0, 0.0, AngleUnit.RADIANS, 0.0);
        this.currVelocity = new Vector2d();

        this.otos.setAngularUnit(AngleUnit.RADIANS);
        this.otos.setLinearUnit(DistanceUnit.METER);

        this.otos.setLinearScalar(OTOSConfig.LINEAR_SCALAR);
        this.otos.setAngularScalar(OTOSConfig.ANGULAR_SCALAR);

        this.otos.calibrateImu();
    }

    @NonNull
    @Override
    public Pose2D getPose() {
        return currPose;
    }

    @Override
    public void setPose(@NonNull Pose2D pose2D) {
        otos.setPosition(new SparkFunOTOS.Pose2D(
                pose2D.getX(otos.getLinearUnit()),
                pose2D.getY(otos.getLinearUnit()),
                pose2D.getHeading(otos.getAngularUnit())
        ));
    }

    @Override
    public double getAngle() {
        return currPose.getHeading(otos.getAngularUnit());
    }

    @Override
    public void setAngle(double v) {
        otos.setPosition(new SparkFunOTOS.Pose2D(
                currPose.getY(DistanceUnit.METER),
                currPose.getY(DistanceUnit.METER),
                v
        ));
    }

    @NonNull
    @Override
    public Vector2d getVelocity() {
        return currVelocity;
    }

    @Override
    public void update() {
        lastSDKPose = otos.getPosition();

        currPose = new Pose2D(
                otos.getLinearUnit(),
                lastSDKPose.x,
                lastSDKPose.y,
                otos.getAngularUnit(),
                lastSDKPose.h
        );

        SparkFunOTOS.Pose2D velocity = otos.getVelocity();
        currVelocity.set(velocity.x, velocity.y);
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
