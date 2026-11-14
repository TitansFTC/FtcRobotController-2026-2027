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
    private DcMotorEx shovel = null;
    private boolean shovelPos = true;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        shovel = hardwareMap.get(DcMotorEx.class, "shovel");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shovel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shovel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shovel.setTargetPosition(0);
        shovel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public static final double OUTWARD_POWER = 0.5;
    public static final double INWARD_POWER = -0.5;

    public void loop (Gamepad gamepad, Telemetry telemetry) {
        double intakepower = 0;
        if (gamepad.left_bumper) {
            intakepower = OUTWARD_POWER;
        }

        if (gamepad.right_bumper) {
            intakepower = INWARD_POWER;
        }

        if (gamepad.yWasPressed()){
            shovelPos=!shovelPos;
        }

        if (shovelPos){
            shovel.setTargetPosition(0);
            shovel.setVelocity(750);
        }else{
            shovel.setTargetPosition(-225);
            shovel.setVelocity(-200);
        }


        intake.setPower(intakepower);
        telemetry.addData("Intake", intakepower);
        telemetry.addData("ShovelTar", shovel.getTargetPosition());
        telemetry.addData("ShovelPos", shovel.getCurrentPosition());
    }

    public void stop() {
        intake.setPower(0);
    }
}
