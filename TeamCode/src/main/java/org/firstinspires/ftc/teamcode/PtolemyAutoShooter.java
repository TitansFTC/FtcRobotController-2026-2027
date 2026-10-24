package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.time.Duration;

@Autonomous(name="PtolemyAutoShooter", group = "Titan's AutoOp")
public class PtolemyAutoShooter extends LinearOpMode {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double NO_POWER_FRACTION = 0;


    public Duration duration = null;
    public DriveBaseJr driveBase = null;
    private void initHardware() {
        driveBase = new DriveBaseJr(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        telemetry.addData("Status", "Starting");

        driveBase.moveForward(driveBase.SLOW_POWER_FRACTION, 1000);
        telemetry.addData("Status", "Moved Forward");
        sleep(1000);
        driveBase.stop();
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
    }
}