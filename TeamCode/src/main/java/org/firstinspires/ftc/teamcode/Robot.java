package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import dev.nullftc.choreolib.drive.IDrivetrain;
import dev.nullftc.choreolib.drive.ILocalizer;

public class Robot {
    private IDrivetrain drive;
    private ILocalizer localizer;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }
}
