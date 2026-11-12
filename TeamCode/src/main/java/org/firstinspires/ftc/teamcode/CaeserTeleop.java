package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name= "CaeserTeleop", group= "Titans TeleOps")
public class  CaeserTeleop extends OpMode {
    private DriveBase driveBase = null;
    private FtcDashboard dashboard = null;
    private Telemetry dashboardTelemetry = null;
    private Yeeters yeeters = null;
    private Odometry odometry = null;
    private Feeders feeders = null;
    private Intake intake = null;
    private Webcam webcam = null;
    int count1 =0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int tarX = 0;
    int tarY=0;
    int tarT=0;


public void init() {
    driveBase = new DriveBase(hardwareMap);
    intake = new Intake(hardwareMap);
    odometry = new Odometry(hardwareMap);
    yeeters = new Yeeters(hardwareMap);
    feeders = new Feeders(hardwareMap);
    //webcam = new Webcam(hardwareMap, dashboardTelemetry);

    dashboard = FtcDashboard.getInstance();
    dashboardTelemetry = dashboard.getTelemetry();

    telemetry.addData("Status", "Initialized");
}
@Override
    public void loop()   {
    //webcam.loop(dashboardTelemetry);
    driveBase.loop(gamepad1, dashboardTelemetry);
    intake.loop(gamepad2, dashboardTelemetry);
    odometry.loop(dashboardTelemetry);
    yeeters.loop(gamepad2, dashboardTelemetry);
    feeders.loop(gamepad2, dashboardTelemetry);


    dashboardTelemetry.update();
}
    @Override
    public void stop()  {
        driveBase.stop();
        intake.stop();
        yeeters.stop();
        feeders.stop();
    }
}