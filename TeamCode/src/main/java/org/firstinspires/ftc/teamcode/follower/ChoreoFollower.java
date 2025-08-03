package org.firstinspires.ftc.teamcode.follower;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.joml.Vector2d;

import java.io.IOException;
import java.util.Vector;

import dev.nullftc.choreolib.Choreo;
import dev.nullftc.choreolib.drive.IDrivetrain;
import dev.nullftc.choreolib.drive.IFollower;
import dev.nullftc.choreolib.drive.ILocalizer;
import dev.nullftc.choreolib.kinematics.ChassisSpeeds;
import dev.nullftc.choreolib.trajectory.Trajectory;
import dev.nullftc.choreolib.trajectory.TrajectorySample;

public class ChoreoFollower<T extends TrajectorySample<T>> implements IFollower {

    private Trajectory<T> trajectory;
    private T closestSample;
    private Pose2D closestPose;
    boolean finished = false;

    public ChoreoFollower(String string) throws IOException {
        this.trajectory = Choreo.INSTANCE.<T>loadTrajectory(string).orElseThrow(() -> new RuntimeException("Trajectory not found!"));
    }

    @NonNull
    @Override
    public ChassisSpeeds update(@NonNull Pose2D pose2D, double v) {
        return new ChassisSpeeds(new Vector2d(), 0.0);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void reset(@NonNull Pose2D pose2D) {}
}
