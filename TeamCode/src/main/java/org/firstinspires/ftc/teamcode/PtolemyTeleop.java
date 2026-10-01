package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.DriveBaseJr;
@TeleOp(name= "PtolemyTeleop", group= "Titans TeleOps")
public class PtolemyTeleop extends OpMode {
    private DriveBase DriveBaseJr = null;
    private FtcDashboard dashboard = null;
    private telemetry dashboardTelemetry = null;

    public void init() {
        DriveBaseJr = new DriveBaseJr (hardwareMap);
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop()   {
        DriveBaseJr.loop(gamepad1);
        dashboardTelemetry.update();
    }
    @Override
    public void stop()  {
        DriveBaseJr.stop();
    }
}