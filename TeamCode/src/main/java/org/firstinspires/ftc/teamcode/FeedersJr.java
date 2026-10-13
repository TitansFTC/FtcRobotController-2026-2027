package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class FeedersJr {
    public static double feedersJr = 0;
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};
    private DcMotorEx Intake = null;
    public FeedersJr(HardwareMap hardwareMap) {
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.a) {
            feedersJr = FAST_POWER_FRACTION;
        }
    }
    public void stop() {
    }
}