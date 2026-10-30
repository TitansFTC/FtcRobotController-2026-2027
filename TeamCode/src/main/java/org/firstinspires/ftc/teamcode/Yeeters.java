package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.CRServoImpl;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class Yeeters {
    double count = 0;
    double count2 =0;
    double YP = 0;
    private DcMotorEx Yeeter = null;
    private DcMotorEx Yeeter2 = null;
    private ServoImpl gate = null;
    private CRServoImpl feeder = null;
    private static final double kP = 75;
    private static final double kI = 10;
    private static final double kD = 0.0;
    private static double kF = 13;
    private static final double EPSILON = 0.03;
    private static final double alpha = 750.0;
    private static final double beta = 1.10;
    private static final double yeeterNearVelocity = 865;
    private static final double yeeterFarVelocity = 1000;
    private boolean yeeterActive = false;
    private double yeeterTargetVelocity = 1250;
    private boolean yeeterManual = false;
    public Yeeters(HardwareMap hardwareMap) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, kI, 0, kF);
        Yeeter = hardwareMap.get(DcMotorEx.class, "Yeeter");
        Yeeter2 = hardwareMap.get(DcMotorEx.class, "Yeeter2");
        feeder=hardwareMap.get(CRServoImpl.class, "feeder");
        Yeeter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        Yeeter.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        Yeeter2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        Yeeter2.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        gate = hardwareMap.get(ServoImpl.class, "gate");
        //Yeeter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void loop (Gamepad gamepad, Telemetry telemetry){
        if (gamepad.dpad_up){
            count = 1;
        }
        if (!gamepad.dpad_up && count ==1){
            count = 0;
            yeeterTargetVelocity+=10;
        }
        //PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, 0, 0, kF);
        //Yeeter.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        if (gamepad.dpad_down){
            count2 = 1;
        }
        if (!gamepad.dpad_down && count2 ==1){
            count2 = 0;
            yeeterTargetVelocity-=10;
        }
        if (gamepad.aWasPressed()){
            yeeterActive=!yeeterActive;
        }

        if (yeeterActive) {                  //Fix Swerve
            Yeeter.setVelocity(yeeterTargetVelocity);
            Yeeter2.setVelocity(-yeeterTargetVelocity);
        } else {
            Yeeter.setVelocity(0);
            Yeeter2.setVelocity(0);
        }
        if (gamepad.x){
            gate.setPosition(.5);
        }
        if (gamepad.y){
            gate.setPosition(0);
        }
        if (gamepad.b){
            feeder.setPower(-.5);
        }else{
            feeder.setPower(1);
        }
        telemetry.addData("yeeterTargetVelocity", yeeterTargetVelocity);
        telemetry.addData("yeeterVelocity", Yeeter.getVelocity());
        telemetry.addData("yeeter2Velocity", Yeeter2.getVelocity());
        telemetry.addData("kF", kF);
    }
    public double computeYeeterVelocity(double distance) {
        return alpha + beta*distance; // Boost constant
    }
    public boolean isReady() {
        return (Math.abs (Yeeter.getVelocity()- yeeterTargetVelocity) <EPSILON * yeeterTargetVelocity) &&
                (Math.abs (Yeeter2.getVelocity()- yeeterTargetVelocity) <EPSILON * yeeterTargetVelocity);
    }
}
