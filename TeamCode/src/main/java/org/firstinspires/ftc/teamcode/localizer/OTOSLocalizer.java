package org.firstinspires.ftc.teamcode.localizer;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import dev.nullftc.choreolib.drive.ILocalizer;

public class OTOSLocalizer implements ILocalizer {

    private SparkFunOTOS sparkFunOTOS;

    public OTOSLocalizer(SparkFunOTOS sparkFunOTOS) {
        this.sparkFunOTOS = sparkFunOTOS;
    }

    @NonNull
    @Override
    public Pose2D getPose() {
        SparkFunOTOS.Pose2D pose = sparkFunOTOS.getPosition();
        DistanceUnit distanceUnit = sparkFunOTOS.getLinearUnit();
        AngleUnit angleUnit = sparkFunOTOS.getAngularUnit();
        return new Pose2D(distanceUnit, pose.x, pose.y, angleUnit, pose.h);
    }

    @Override
    public void setPose(Pose2D pose2D) {
        DistanceUnit distanceUnit = sparkFunOTOS.getLinearUnit();
        AngleUnit angleUnit = sparkFunOTOS.getAngularUnit();

        sparkFunOTOS.setPosition(
                new SparkFunOTOS.Pose2D(
                        pose2D.getX(distanceUnit),
                        pose2D.getY(distanceUnit),
                        pose2D.getHeading(angleUnit)
                )
        );
    }

    @Override
    public double getAngle() {
        return sparkFunOTOS.getPosition().h;
    }

    @Override
    public void setAngle(double v) {
        SparkFunOTOS.Pose2D position = sparkFunOTOS.getPosition();
        sparkFunOTOS.setPosition(new SparkFunOTOS.Pose2D(
                position.x,
                position.y,
                position.h
        ));
    }
}
