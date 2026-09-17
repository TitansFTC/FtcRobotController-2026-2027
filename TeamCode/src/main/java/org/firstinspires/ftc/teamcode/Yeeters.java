package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Yeeters {
    double count = 0;
    double count2 =0;
    double YP = 0;
    private DcMotorEx Yeeter = null;
    public Yeeters(HardwareMap hardwareMap) {
        Yeeter = hardwareMap.get(DcMotorEx.class, "Yeeter");

        //Yeeter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void loop (Gamepad gamepad, Telemetry telemetry){
        if (gamepad.dpad_up){
            count = 1;
        }
        if (!gamepad.dpad_up && count ==1){
            count = 0;
            YP+=.05;
        }
        if (gamepad.dpad_down){
            count2 = 1;
        }
        if (!gamepad.dpad_down && count2 ==1){
            count2 = 0;
            YP-=.05;
        }
        if (gamepad.a){
            Yeeter.setPower(YP);
        }
        if (gamepad.b){
            Yeeter.setPower(0);
        }
    }

}
