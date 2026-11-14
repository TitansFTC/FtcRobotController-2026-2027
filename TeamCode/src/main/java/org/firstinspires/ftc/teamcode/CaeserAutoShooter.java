package org.firstinspires.ftc.teamcode;
import
        com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.time.Duration;

@Autonomous(name="CaeserAutoShooter", group = "Titan's AutoOp")
public class CaeserAutoShooter extends LinearOpMode {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double NO_POWER_FRACTION = 0;


    public Duration duration = null;
    public DriveBase driveBase = null;

    private Odometry odometry = null;

    public Yeeters yeeter = null;
    public Feeders feeder = null;
    private static final double kP = 100;
    private static final double kI = 10;
    private static double kF = 13;

    private void initHardware() {
        driveBase = new DriveBase(hardwareMap);
        yeeter = new Yeeters(hardwareMap);
        feeder = new Feeders(hardwareMap);
        odometry = new Odometry(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();

        telemetry.addData("Status", "Starting");

        // Move forward a small amount
        odometry.loop(telemetry);

        driveBase.odoMoveLoop(0, -5, 0, telemetry, odometry);

        // Warm up Yeeters
        yeeter.startYeeters();
        sleep(3000);

        // Shoot
        feeder.forwards();
        sleep(4000);
        feeder.backwards();

        /*
        driveBase.moveLeft(driveBase.HALF_POWER_FRACTION, 280);
        telemetry.addData("Status", "Moved Forward");
        sleep(280);
        driveBase.stop();
        driveBase.moveLeft(driveBase.NO_POWER_FRACTION, 300);
        telemetry.addData("Status", "Moved Forward");
        sleep(300);
        driveBase.stop();
        driveBase.moveForward(driveBase.HALF_POWER_FRACTION, 2400);
        telemetry.addData("Status", "Moved Forward");
        sleep(2400);
        driveBase.stop();
         */
    }
}