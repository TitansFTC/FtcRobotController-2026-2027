package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.PtolemyTeleop.DriveBaseJr;

@TeleOp(name= "PtolemyTeleop", group= "Titans TeleOps")
public class PtolemyTeleop extends OpMode {
    private DriveBaseJr driveBasejr = null;
    private FtcDashboard dashboard = null;
    //private Telemetry dashboardTelemetry = null;
    //private Odometry odometry = null;
    //private Intake intake = null;
    public void init() {
        driveBasejr = new DriveBaseJr(hardwareMap);
        //intake = new Intake(hardwareMap);
        //odometry = new Odometry(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        //dashboardTelemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void loop()   {
        driveBasejr.loop(gamepad1);
        //intake.loop(gamepad1, dashboardTelemetry);
        //odometry.loop(dashboardTelemetry);
        //dashboardTelemetry.update();
    }
    @Override
    public void stop()  {
        driveBasejr.stop();
    }
}