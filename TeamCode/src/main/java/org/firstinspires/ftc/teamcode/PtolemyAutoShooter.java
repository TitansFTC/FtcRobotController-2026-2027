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
        driveBase = new DriveBaseJr(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        telemetry.addData("Status", "Starting");

        driveBase.moveForward(driveBase.SLOW_POWER_FRACTION, 500);
        telemetry.addData("Status", "Moved Forward");
        sleep(500);
        driveBase.stop();
    }
}