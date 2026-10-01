package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServoImpl;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private DcMotorEx intake = null;
    private CRServoImpl spinnyL = null;
    private CRServoImpl spinnyR = null;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        spinnyL = hardwareMap.get(CRServoImpl.class, "spinnyL");
        spinnyR = hardwareMap.get(CRServoImpl.class, "spinnyR");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;

    public void loop (Gamepad gamepad, Telemetry telemetry) {
        double spinnyPower =0;
        if (gamepad.right_bumper){
            spinnyPower=1;
        }
        if (gamepad.left_bumper){
            spinnyPower=-1;
        }

        spinnyR.setPower(-spinnyPower);
        spinnyL.setPower(spinnyPower);

        double intakepower = 0;
            if (gamepad.a) {
                intakepower = 0.5;
            }
            if (gamepad.b) {
                intakepower = -0.5;
            }
            intake.setPower(intakepower);
        telemetry.addData("Intake", intakepower);
    }
}
