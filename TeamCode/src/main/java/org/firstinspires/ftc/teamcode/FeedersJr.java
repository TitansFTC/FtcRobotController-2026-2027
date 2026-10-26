package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.CRServoImpl;
public class FeedersJr {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};
    private DcMotorEx intake = null;
    private CRServoImpl sintake = null;
    private CRServoImpl sintakeLeft = null;
    private CRServoImpl sintakeRight = null;
    public FeedersJr(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "Intake");
        sintakeLeft = hardwareMap.get(CRServoImpl.class, "sintakeLeft");
        sintakeRight = hardwareMap.get(CRServoImpl.class, "sintakeRight");
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
        double intakePower = 0;
        double sintakeLeftPower = 0;
        double sintakeRightPower = 0;
        if (gamepad.a) {
            intakePower = FAST_POWER_FRACTION;
            sintakeLeftPower = -FAST_POWER_FRACTION;
            sintakeRightPower = FAST_POWER_FRACTION;
        }
        if (gamepad.y) {
            intakePower = -FAST_POWER_FRACTION;
        }
            intake.setPower(intakePower);
            sintakeLeft.setPower(sintakeLeftPower);
            sintakeRight.setPower(sintakeRightPower);
    }
    public void stop()  {
        double intakePower = 0;
        double sintakeLeftPower = 0;
        double sintakeRightPower = 0;
    }
}