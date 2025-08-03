package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.joml.Vector2d;

import dev.nullftc.choreolib.drive.IDrivetrain;

public class MecanumDrive implements IDrivetrain {
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    public MecanumDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        this.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stall(boolean shouldBreak) {
        if (shouldBreak) {
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    @Override
    public void set(Vector2d movement, double turn) {
        double strafe = -movement.y;
        double forward = -movement.x;

        double R = Math.hypot(strafe, forward);
        double robotAngle = Math.atan2(forward, -strafe) + Math.PI / 4;

        double flP = R * Math.cos(robotAngle) + turn;
        double frP = R * Math.sin(robotAngle) - turn;
        double blP = R * Math.sin(robotAngle) + turn;
        double brP = R * Math.cos(robotAngle) - turn;

        frontLeft.setPower(flP);
        frontRight.setPower(frP);
        backLeft.setPower(blP);
        backRight.setPower(brP);
    }
}

