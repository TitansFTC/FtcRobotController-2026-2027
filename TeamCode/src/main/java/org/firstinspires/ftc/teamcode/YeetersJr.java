package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class YeetersJr {
    public static final double FAST_POWER_FRACTION = 1.0;
    public static final double SLOW_POWER_FRACTION = 0.2;
    public static final double HALF_POWER_FRACTION = 0.5;
    public static final double NO_POWER_FRACTION = 0;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};
    private DcMotorEx launcher = null;
    private CRServoImpl spinner = null;
    public YeetersJr(HardwareMap hardwareMap) {
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        spinner = hardwareMap.get(CRServoImpl.class, "spinner");
    }
    public void loop(Gamepad gamepad, Telemetry telemetry) {
       double launcherPower = NO_POWER_FRACTION;
       double spinnerPower = NO_POWER_FRACTION;
        if (gamepad.b){
           launcherPower = FAST_POWER_FRACTION;
           spinnerPower = HALF_POWER_FRACTION;
        }
    }
    public void stop() {
        launcher.setPower(NO_POWER_FRACTION);
        spinner.setPower(NO_POWER_FRACTION);
    }
}