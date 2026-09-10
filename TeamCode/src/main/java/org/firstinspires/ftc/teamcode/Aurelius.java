package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Aurelius", group = "Titan's AutoOp")
public class Aurelius extends LinearOpMode {
    private DriveBase driveBase = null;
    private FtcDashboard dashboard = null;
    private Telemetry dashboardTelemetry = null;

    private Odometry odometry = null;
    private Intake intake = null;

    public void initHardware() {
        driveBase = new DriveBase(hardwareMap);
        intake = new Intake(hardwareMap);
        odometry = new Odometry(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        ElapsedTime timer = new ElapsedTime();
        driveBase.loop(gamepad1, dashboardTelemetry);
        intake.loop(gamepad1, dashboardTelemetry);
        odometry.loop(dashboardTelemetry);

        while (opModeIsActive()){
            odoLoop(0, 60, 179, telemetry);
            sleep(1000);
            odoLoop(0, 0, 0, telemetry);
            sleep(1000);
        }

    }
    public void odoLoop(double tX, double tY, double tT, Telemetry telemetry){
        boolean Arrived = false;
        while (!Arrived){
            odometry.loop(telemetry);
            Arrived = driveBase.odoMove(tX, tY, tT, odometry.curPosX(), odometry.curPosY(), odometry.curPosT(), telemetry);
        }

    }

}
