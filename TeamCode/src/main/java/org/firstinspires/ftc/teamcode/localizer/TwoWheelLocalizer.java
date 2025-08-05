package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.joml.Vector2d;

import java.util.function.DoubleSupplier;

import dev.nullftc.choreolib.drive.ILocalizer;
import dev.nullftc.wpimath.geometry.Twist2d;

public class TwoWheelLocalizer implements ILocalizer {
    private final DoubleSupplier backWheel;
    private final DoubleSupplier leftWheel;
    private final IMU imu;
    private Pose2D position = new Pose2D(Constants.PREFERRED_DISTANCE_UNIT, 0.0, 0.0, Constants.PREFERRED_ANGULAR_UNIT, 0.0);
    private Twist2d latestTwist = new Twist2d(0.0, 0.0, 0.0);

    public TwoWheelLocalizer(DoubleSupplier backWheel, DoubleSupplier leftWheel, IMU imu) {
        this.imu = imu;
        this.backWheel = backWheel;
        this.leftWheel = leftWheel;
    }

    @NonNull
    @Override
    public Pose2D getPose() {
        return position;
    }

    @Override
    public void setPose(@NonNull Pose2D pose2D) {
        this.position = pose2D;
    }

    @Override
    public double getAngle() {
        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, Constants.PREFERRED_ANGULAR_UNIT).firstAngle;
    }

    @Override
    public void setAngle(double v) {
        position = new Pose2D(
                Constants.PREFERRED_DISTANCE_UNIT,
                position.getX(Constants.PREFERRED_DISTANCE_UNIT),
                position.getX(Constants.PREFERRED_DISTANCE_UNIT),
                Constants.PREFERRED_ANGULAR_UNIT,
                v
        );
    }

    @NonNull
    @Override
    public Vector2d getVelocity() {
        // todo
        return new Vector2d();
    }

    @Override
    public void update() {
        // todo
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
