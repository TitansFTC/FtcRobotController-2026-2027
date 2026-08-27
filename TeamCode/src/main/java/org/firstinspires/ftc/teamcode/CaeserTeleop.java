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
public void init() {
    driveBase = new DriveBase(hardwareMap);


    dashboard = FtcDashboard.getInstance();
    dashboardTelemetry = dashboard.getTelemetry();

    telemetry.addData("Status", "Initialized");
}
@Override
    public void loop()   {
    driveBase.loop(gamepad1);
}
@Override
    public void stop()  {
    driveBase.stop();
}

}




