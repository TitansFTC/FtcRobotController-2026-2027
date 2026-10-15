package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class FeedersJr {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};
    private DcMotorEx intake = null;
    private Servo sintake = null;

    public FeedersJr(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "Intake");
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
            double intakePower = 0;
            double sintakePower = 0;
        if (gamepad.a) {
            intakePower = FAST_POWER_FRACTION;
            sintakePower = FAST_POWER_FRACTION;
        }
        if (gamepad.y){
            intakePower = -FAST_POWER_FRACTION;
      }
        intake.setPower(intakePower);
        sintake.setPosition(sintakePower);
    }
    public void stop() {
    }
}