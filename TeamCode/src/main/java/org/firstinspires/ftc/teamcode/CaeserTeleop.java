package org.firstinspires.ftc.teamcode;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "CaeserTeleop", group= "Titans TeleOps")

public class  CaeserTeleop extends OpMode {
    private DriveBase driveBase = null;

public void init() {
    driveBase = new DriveBase(hardwareMap);
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




