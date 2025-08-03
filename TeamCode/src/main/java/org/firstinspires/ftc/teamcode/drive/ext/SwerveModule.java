package org.firstinspires.ftc.teamcode.drive.ext;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

/**
 * If you are planning on running swerve, you should add the {@link com.acmerobotics.dashboard.config.Config} annotation below for ease
 * of tuning.
 */
public class SwerveModule {

    //#region PID Coefficients
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kS = 0.0;
    //#endregion

    public static double MAX_SERVO = 1, MAX_MOTOR = 1;

    public static boolean MOTOR_FLIPPING = true;

    private DcMotorEx moduleMotor;
    private CRServo servo;

    public SwerveModule(DcMotorEx moduleMotor, CRServo servo) {
        this.moduleMotor = moduleMotor;
        this.servo = servo;
    }

}
