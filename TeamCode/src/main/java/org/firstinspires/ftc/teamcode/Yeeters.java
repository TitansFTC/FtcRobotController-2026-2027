package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Yeeters {
    double YP = 0;
    private DcMotorEx yeeter = null;
    private static final double kP = 25;
    private static final double kI = 1;
    private static final double kD = 0.0;
    private static double kF = 5;
    private static final double EPSILON = 0.03;
    private static final double alpha = 1114.84;
    private static final double beta = 1.68;
    private static final double yeeterDefault = 1205;
    private static final double yeeterFarVelocity = 1000;
    private boolean yeeterActive = true;
    private double yeeterTargetVelocity = yeeterDefault;
    public Yeeters(HardwareMap hardwareMap) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, kI, 0, kF);
        yeeter = hardwareMap.get(DcMotorEx.class, "Yeeter");
        yeeter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        yeeter.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        //yeeter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void loop (Gamepad gamepad, Telemetry telemetry, double distance) {
        // Manual Control
        if (gamepad.dpadUpWasPressed()){
            yeeterTargetVelocity += 5;
        }
        if (gamepad.dpadDownWasPressed()){
            yeeterTargetVelocity -= 5;
        }

        if (distance == 0) {
            yeeterTargetVelocity = yeeterDefault;
        } else {
            yeeterTargetVelocity = computeYeeterVelocity(distance);
        }

        if (yeeterActive) {
            yeeter.setVelocity(yeeterTargetVelocity);
        } else {
            yeeter.setVelocity(0);
        }

        telemetry.addData("yeeterTargetVelocity", yeeterTargetVelocity);
        telemetry.addData("yeeterVelocity", yeeter.getVelocity());
    }
    public double computeYeeterVelocity(double distance) {
        return alpha + beta*distance; // Boost constant
    }

    public boolean isReady() {
        return (Math.abs(yeeter.getVelocity()- yeeterTargetVelocity) < EPSILON * yeeterTargetVelocity);
    }
    public void startYeeters(double velocity) {
        yeeter.setVelocity(velocity);
    }

    public void stop() {
        yeeter.setPower(0);
    }
}
