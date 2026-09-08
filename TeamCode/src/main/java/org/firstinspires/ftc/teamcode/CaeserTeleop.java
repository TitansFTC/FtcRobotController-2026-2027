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

    private Odometry odometry = null;
    private Intake intake = null;
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
    dashboard = FtcDashboard.getInstance();
    dashboardTelemetry = dashboard.getTelemetry();

    telemetry.addData("Status", "Initialized");
}
@Override
    public void loop()   {
    driveBase.loop(gamepad1, dashboardTelemetry);
    intake.loop(gamepad1, dashboardTelemetry);
    odometry.loop(dashboardTelemetry);
    if (gamepad1.dpad_up){
        count1 =1;
    }
    if (!gamepad1.dpad_up && count1 ==1){
        count1 =0;
        tarY -=5;
    }
    if (gamepad1.dpad_down){
        count2 =1;
    }
    if (!gamepad1.dpad_down && count2 ==1){
        count2 =0;
        tarY +=5;
    }
    if (gamepad1.dpad_right){
        count3 =1;
    }
    if (!gamepad1.dpad_right && count3 ==1){
        count3 =0;
        tarX -=5;
    }
    if (gamepad1.dpad_left){
        count4 =1;
    }
    if (!gamepad1.dpad_left && count4 ==1){
        count4 =0;
        tarX +=5;
    }
    if (gamepad1.y){
        count5 =1;
    }
    if (!gamepad1.y && count5 ==1){
        count5 =0;
        if (tarT <175){
            tarT+=5;
        }
        else {
            tarT = -175;
        }
    }
    telemetry.addData("TarX", tarX);
    telemetry.addData("TarY", tarY);
    telemetry.addData("TarT", tarT);

    if (gamepad1.x){
        driveBase.odoMove(tarX, tarY, tarT, odometry.curPosX(), odometry.curPosY(), odometry.curPosT(), dashboardTelemetry);
    }

    dashboardTelemetry.update();

}



    @Override
    public void stop()  {
    driveBase.stop();
}

}




