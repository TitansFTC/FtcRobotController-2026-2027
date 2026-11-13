package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.CRServoImpl;
public class Feeders {
    public static final double FORWARD_POWER = -1.0;
    public static final double BACKWARD_POWER = 1.0;
    private CRServoImpl feeder = null;
    public Feeders(HardwareMap hardwareMap) {
        feeder = hardwareMap.get(CRServoImpl.class, "feeder");
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.b) {
            feeder.setPower(FORWARD_POWER);
        }else{
            feeder.setPower(BACKWARD_POWER);
        }
    }
    public void backwards() {
        feeder.setPower(BACKWARD_POWER);
    }
    public void forwards() {
        feeder.setPower(FORWARD_POWER);
    }
    public void stop()  {
       feeder.setPower(0);
    }
}