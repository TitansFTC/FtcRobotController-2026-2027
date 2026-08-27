package org.firstinspires.ftc.teamcode;
//import com.acmerobotics.dashboard.FtcDashboard;
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

        private DcMotorEx leftFront = null;
        private DcMotorEx leftBack = null;
        private DcMotorEx rightFront = null;
        private DcMotorEx rightBack = null;

        public DriveBase(HardwareMap hardwareMap) {
            leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
            leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
            rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
            rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        }

   public void loop(Gamepad gamepad) {
       double leftFrontPower = (gamepad.left_stick_y - gamepad.right_stick_x) - gamepad.left_stick_x;
       double leftBackPower = (gamepad.left_stick_y - gamepad.right_stick_x) + gamepad.left_stick_x;
       double rightFrontPower = (-gamepad.left_stick_y - gamepad.right_stick_x) - gamepad.left_stick_x;
       double rightBackPower = (-gamepad.left_stick_y - gamepad.right_stick_x) + gamepad.left_stick_x;

       double powerFraction = FAST_POWER_FRACTION;
       if (gamepad.right_trigger > 0.8) {
           powerFraction = SLOW_POWER_FRACTION;
       }
       leftFront.setPower(leftFrontPower * powerFraction);
       leftBack.setPower(leftBackPower * powerFraction);
       rightFront.setPower(rightFrontPower * powerFraction);
       rightBack.setPower(rightBackPower * powerFraction);



       }


    public void stop () {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        }

}






