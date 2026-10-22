package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous(name="PtolemyAutoShooter", group = "Titan's AutoOp")
public class PtolemyAutoShooter extends LinearOpMode {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public void initHardware() {
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
    }

DriveBaseJr.moveBackward(DriveBaseJr.HALF_POWER_FRACTION);
        DriveBaseJr.stop();
    sleep(250);
}