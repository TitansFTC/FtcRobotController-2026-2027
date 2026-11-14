package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import
        com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    private FtcDashboard dashboard = null;
    private Telemetry dashboardTelemetry = null;

    private Intake intake = null;
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
        intake = new Intake(hardwareMap);

        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();

        telemetry.addData("Status", "Starting");

        // Warm up Yeeters
        yeeter.startYeeters();
        intake.holdPosition();

        // Move forward a small amount
        driveBase.odoMoveLoop(0, -5, 0, dashboardTelemetry, odometry);
        intake.holdPosition();

        // Warm up Yeeters
        sleep(3000);

        // Shoot
        feeder.forwards();
        sleep(4000);
        feeder.backwards();

        // Move diagonally
        driveBase.odoMoveLoop(-35, -60, 0, dashboardTelemetry, odometry);
        intake.holdPosition();

        // Move forward to parking
        driveBase.odoMoveLoop(-35, -90, 0, dashboardTelemetry, odometry);
        intake.holdPosition();

        driveBase.stop();
    }
}