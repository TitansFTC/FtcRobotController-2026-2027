package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Autonomous(name="PtolemyAutoShooter", group = "Titan's AutoOp")
public class PtolemyAutoShooter extends LinearOpMode {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public DriveBaseJr driveBase = null;
    private void initHardware() {
        telemetry.addData("Status", "Initialized");
        driveBase = new DriveBaseJr(hardwareMap);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();

        driveBase.moveForward(driveBase.FAST_POWER_FRACTION, 1000);
        //driveBase.sleep(1000);
        /*driveBase.moveRight(driveBase.HALF_POWER_FRACTION, 500);
        driveBase.sleep(250);
        driveBase.moveForward(driveBase.HALF_POWER_FRACTION, 500);
        driveBase.sleep(250);
        driveBase.moveRight(driveBase.HALF_POWER_FRACTION, 500);
        driveBase.sleep(250);
        driveBase.moveForward(driveBase.HALF_POWER_FRACTION, 500);
        driveBase.sleep(250);
        driveBase.moveRight(driveBase.HALF_POWER_FRACTION, 500);
        driveBase.sleep(250);*/
    }
}