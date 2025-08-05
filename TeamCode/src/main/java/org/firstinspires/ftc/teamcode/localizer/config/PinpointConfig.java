package org.firstinspires.ftc.teamcode.localizer.config;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.GoBildaOdometryPods;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * The configuration values for the goBILDA Pinpoint Driver
 */
@Config
public class PinpointConfig {
    public static double X_OFFSET = 0.0;
    public static double Y_OFFSET = 0.0;
    public static DistanceUnit OFFSET_UNIT = DistanceUnit.MM;

    public static boolean X_FLIPPED = false;
    public static boolean Y_FLIPPED = false;

    public static boolean ENABLE_CUSTOM_YAW_SCALAR = false;
    public static double YAW_SCALAR = 0;

    /**
     * If you are not using goBILDA Odometry pods alongside your pinpoint you will need to manually configure this
     * in {@link org.firstinspires.ftc.teamcode.localizer.PinpointLocalizer}.
     */
    public static GoBildaOdometryPods OFFICIAL_POD_TYPE = GoBildaOdometryPods.goBILDA_SWINGARM_POD;
}
