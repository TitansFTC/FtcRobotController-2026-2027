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

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

        intake.setPower(intakepower);
        telemetry.addData("Intake", intakepower);
    }

    public void stop() {
        intake.setPower(0);
    }
}
