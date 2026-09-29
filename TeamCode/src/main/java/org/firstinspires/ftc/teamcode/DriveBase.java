package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
    public static final double FAST_POWER_FRACTION = 1.0;
        public static final double SLOW_POWER_FRACTION = 0.2;
        public static final double HALF_POWER_FRACTION = 0.5;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};

        private DcMotorEx left = null;
        private DcMotorEx right = null;

        public DriveBase(HardwareMap hardwareMap) {
            left = hardwareMap.get(DcMotorEx.class, "left");
            right = hardwareMap.get(DcMotorEx.class, "right");
        }

   public void loop(Gamepad gamepad/*, Telemetry telemetry*/) {
            double leftPower = (gamepad.left_stick_y - gamepad.right_stick_x);
            double rightPower = (-gamepad.left_stick_y - gamepad.right_stick_x);
       double powerFraction = FAST_POWER_FRACTION;
       if (gamepad.right_trigger > 0.8) {
           powerFraction = SLOW_POWER_FRACTION;
       }
       left.setPower(leftPower * powerFraction);
       right.setPower(rightPower * powerFraction);


      // telemetry.addData("rightFront", rightPower);

       }
    public void stop () {
        left.setPower(0);
        right.setPower(0);
        }

}






