package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.DriveBaseJr;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name= "PtolemyTeleop", group= "Titans TeleOps")
public class PtolemyTeleop extends OpMode {
    private DriveBaseJr driveBase = null;
    private FtcDashboard dashboard = null;
    private Telemetry dashboardTelemetry = null;

    public void init() {
        driveBase = new DriveBaseJr(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop()   {
        driveBase.loop(gamepad1, telemetry);
        dashboardTelemetry.update();
    }
    @Override
    public void stop()  {
        driveBase.stop();
    }
}