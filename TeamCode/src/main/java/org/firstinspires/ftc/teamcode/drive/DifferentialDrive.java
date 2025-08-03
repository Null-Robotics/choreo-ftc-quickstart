package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.joml.Vector2d;

import java.util.HashSet;
import java.util.List;

import dev.nullftc.choreolib.drive.IDrivetrain;

public class DifferentialDrive implements IDrivetrain {
    HashSet<DcMotor> leftMotors = new HashSet<>();
    HashSet<DcMotor> rightMotors = new HashSet<>();

    public DifferentialDrive(HardwareMap hardwareMap, String[] leftMotorNames, String[] rightMotorNames) {
        for (String name : leftMotorNames) {
            DcMotor motor = hardwareMap.get(DcMotor.class, name);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftMotors.add(motor);
        }

        for (String name : rightMotorNames) {
            DcMotor motor = hardwareMap.get(DcMotor.class, name);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightMotors.add(motor);
        }
    }

    @Override
    public void set(Vector2d movement, double turn) {
        double forward = movement.y;
        double leftPower = forward + turn;
        double rightPower = forward - turn;

        double max = Math.max(1.0, Math.abs(leftPower));
        max = Math.max(max, Math.abs(rightPower));

        leftPower /= max;
        rightPower /= max;

        for (DcMotor motor : leftMotors) {
            motor.setPower(leftPower);
        }

        for (DcMotor motor : rightMotors) {
            motor.setPower(rightPower);
        }
    }
}
