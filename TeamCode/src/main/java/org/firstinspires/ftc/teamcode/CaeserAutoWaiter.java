package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.time.Duration;

@Autonomous(name="CeaserAutoWaiter", group = "Titan's AutoOp")
public class CaeserAutoWaiter extends LinearOpMode {
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

        driveBase.moveForward(driveBase.NO_POWER_FRACTION,10000);
        telemetry.addData("Status", "Moved Forward");
        sleep(10000);
        driveBase.stop();
        driveBase.moveForward(driveBase.SLOW_POWER_FRACTION,2000);
        telemetry.addData("Status", "Moved Forward");
        sleep(2000);
        driveBase.moveRight(driveBase.HALF_POWER_FRACTION,750);
        telemetry.addData("Status", "Moved Forward");
        sleep(750);
        driveBase.moveForward(driveBase.HALF_POWER_FRACTION,1500);
        telemetry.addData("Status", "Moved Forward");
        sleep(1000);
    }
}