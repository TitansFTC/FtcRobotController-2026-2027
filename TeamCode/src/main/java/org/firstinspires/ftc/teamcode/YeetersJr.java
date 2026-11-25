package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class YeetersJr {
    double count = 0;
    double count2 =0;
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double SEMI_FAST_POWER_FRACTION = 0.6;

    public static final double kP = 100;

    public static final double kI = 10;

    public static final double kD = 0;

    public static final double kF = 13;

    private double yeeterTargetVelocity = 1480;
    private boolean toggle = false;
    private boolean yeeterActive = false;
    private DcMotorEx YeeterJr = null;
    public static final double NO_POWER_FRACTION = 0;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};
    private DcMotorEx launcher = null;
    private CRServoImpl spinnerLeft = null;
    private CRServoImpl spinnerRight = null;
    private boolean launcherActive = true;
    public static void sleep(int i) {
    }

    public YeetersJr(HardwareMap hardwareMap) {
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        spinnerLeft = hardwareMap.get(CRServoImpl.class, "spinnerLeft");
        spinnerRight = hardwareMap.get(CRServoImpl.class, "spinnerRight");


        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, kI, 0, kF);
        launcher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        launcher.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }
        public void yeeterActive(){
        YeeterJr.setVelocity(HALF_POWER_FRACTION);
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
        if (gamepad.dpadDownWasPressed()){
            yeeterTargetVelocity -= 10;
        }
        if (gamepad.dpadUpWasPressed()){
            yeeterTargetVelocity += 10;
        }
        double launcherPower = 0;
        double spinnerLeftPower = 0;
        double spinnerRightPower = 0;
        if (gamepad.b) {
            spinnerLeftPower = FAST_POWER_FRACTION;
            spinnerRightPower = -FAST_POWER_FRACTION;

        }
        else {
            spinnerLeftPower = 0;
            spinnerRightPower = 0;
        }
        if (gamepad.leftTriggerWasPressed()) {
            launcherActive=!launcherActive;
        }
        if (launcherActive){
            launcherPower=yeeterTargetVelocity;
        }else{
            launcherPower=0;
        }
        launcher.setVelocity(launcherPower);
        spinnerLeft.setPower(spinnerLeftPower);
        spinnerRight.setPower(spinnerRightPower);

        telemetry.addData("yeeterTargetVelocity", yeeterTargetVelocity);
    }
        public void fire ( double power, int duration){
            launcher.setVelocity(yeeterTargetVelocity);
            spinnerLeft.setPower(power);
            spinnerRight.setPower(-power);
            sleep(duration);
        }
        public void stop () {
            launcher.setVelocity(NO_POWER_FRACTION);
            spinnerLeft.setPower(NO_POWER_FRACTION);
            spinnerRight.setPower(NO_POWER_FRACTION);
        }
}