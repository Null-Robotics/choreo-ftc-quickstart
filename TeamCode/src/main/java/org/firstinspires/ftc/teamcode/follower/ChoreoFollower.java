package org.firstinspires.ftc.teamcode.follower;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import dev.nullftc.choreolib.drive.IFollower;
import dev.nullftc.choreolib.kinematics.ChassisSpeeds;

public class ChoreoFollower implements IFollower {
    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void reset(@NonNull Pose2D pose2D) {

    }

    @NonNull
    @Override
    public ChassisSpeeds update(@NonNull Pose2D pose2D, double v) {
        return null;
    }
}
