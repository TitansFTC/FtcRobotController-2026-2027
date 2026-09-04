package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;

public class DriveBase {
    public static final double FAST_POWER_FRACTION = 1.0;
        public static final double SLOW_POWER_FRACTION = 0.2;
        public static final double HALF_POWER_FRACTION = 0.5;
    public static final double[] TARGET_REACHED = {0, 0, 0, 0, 0};

        private DcMotorEx leftFront = null;
        private DcMotorEx leftBack = null;
        private DcMotorEx rightFront = null;
        private DcMotorEx rightBack = null;
    double rel_tar_X;
    double rel_tar_Y;
    double rel_X;
    double rel_Y;
    double A;
    double C;
    double rel_T;

        public DriveBase(HardwareMap hardwareMap) {
            leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
            leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
            rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
            rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        }

   public void loop(Gamepad gamepad, Telemetry telemetry) {
       double leftFrontPower = (gamepad.left_stick_y - gamepad.right_stick_x) - gamepad.left_stick_x;
       double leftBackPower = (gamepad.left_stick_y - gamepad.right_stick_x) + gamepad.left_stick_x;
       double rightFrontPower = (-gamepad.left_stick_y - gamepad.right_stick_x) - gamepad.left_stick_x;
       double rightBackPower = (-gamepad.left_stick_y - gamepad.right_stick_x) + gamepad.left_stick_x;

       double powerFraction = FAST_POWER_FRACTION;
       if (gamepad.right_trigger > 0.8) {
           powerFraction = SLOW_POWER_FRACTION;
       }
       leftFront.setPower(leftFrontPower * powerFraction);
       leftBack.setPower(leftBackPower * powerFraction);
       rightFront.setPower(rightFrontPower * powerFraction);
       rightBack.setPower(rightBackPower * powerFraction);


       telemetry.addData("rightFront", rightFrontPower);

       }


    public void odoMove(double tar_pos_X, double tar_pos_Y, double tar_T, double cur_Pos_X, double cur_Pos_Y, double cur_Heading, Telemetry telemetry){
            /*

        rel_tar_X = tar_pos_X - cur_Pos_X;
        rel_tar_Y = tar_pos_Y - cur_Pos_Y;
        if ((rel_tar_X != 0) || (rel_tar_Y != 0) || (tar_T != cur_Heading)) {
            double beta = 90;
            if (rel_tar_Y < 0) {
                beta = -90;
            }
            if (rel_tar_X != 0 ){
                beta = Math.toDegrees(Math.atan(rel_tar_Y/rel_tar_X));
            }
            if (rel_tar_X < 0){
                beta = beta - 180;
            }
            A = 90 - beta;

            rel_X = Math.sin(Math.toRadians(A));
            rel_Y = Math.cos(Math.toRadians(A));
            if (Math.abs(cur_Heading - tar_T) >= 30) {
                if (tar_T > cur_Heading){
                    rel_T = .5;
                }
                else {
                    rel_T = -.5;
                }
            }
            else {
                rel_T = ((tar_T - cur_Heading )/30) * .5;
            }

            double slow = 1;
            if (Math.sqrt(Math.pow((tar_pos_X - cur_Pos_X), 2) + (Math.pow((tar_pos_Y - cur_Pos_Y), 2))) < 7.87){
                slow = Math.sqrt(Math.pow((tar_pos_X - cur_Pos_X), 2) + (Math.pow((tar_pos_Y - cur_Pos_Y), 2)))/5080;
            }
            slow=1;

            double lfp = ((rel_Y - rel_X )  * slow + rel_T);
            double rfp = ((-rel_Y + -rel_X )  * slow - rel_T);
            double lbp = ((rel_Y + rel_X )  * slow + rel_T);
            double rbp = ((-rel_Y + rel_X )  * slow - rel_T);
            if ((Math.abs(lfp) >= 1) || (Math.abs(rfp) >= 1) || (Math.abs(lbp) >= 1) || (Math.abs(rbp) >= 1)){
                double k = Math.max(Math.max(Math.abs(lfp), Math.abs(rfp)), Math.max(Math.abs(rbp), Math.abs(lbp)));
                lfp = lfp/k;
                rfp = rfp/k;
                rbp = rbp/k;
                lbp = lbp/k;
            }
            leftFront.setPower(lfp*.3);
            rightFront.setPower(rfp*.3);
            leftBack.setPower(lbp*.3);
            rightBack.setPower(rbp*.3);


        }
        telemetry.addData("rel_X", rel_X);
        telemetry.addData("rel_Y", rel_Y);
        telemetry.addData("rel_T", rel_T);
        telemetry.addData("curT", cur_Heading);

             */


        double rel_tar_x = tar_pos_X - cur_Pos_X;
        double rel_tar_y = tar_pos_Y - cur_Pos_Y;
        telemetry.addData("rel_tar_x", rel_tar_x);
        telemetry.addData("rel_tar_y", rel_tar_y);

        double rel_Heading = tar_T-cur_Heading;
        telemetry.addData("rel_tar_T", rel_Heading);


        // Skip if we have reached target
        if ((Math.abs(rel_tar_x) <= 7) && (Math.abs(rel_tar_y) <= 7) && (Math.abs(rel_Heading) <= 10)) {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
            rightFront.setPower(0);
            return;
        }

        double beta = 90;
        if (rel_tar_y < 0) {
            beta = -90;
        }
        if (rel_tar_x != 0) {
            beta = Math.toDegrees(Math.atan(rel_tar_y / rel_tar_x));
        }
        if (rel_tar_x < 0) {
            beta = beta - 180;
        }

        double pos_angle = 90 + cur_Heading - beta;
        double rel_pos_x = Math.sin(Math.toRadians(pos_angle));
        double rel_pos_y = Math.cos(Math.toRadians(pos_angle));
        double rel_angle = 0;

        if (Math.abs(cur_Heading - tar_T) >= 30) {
            if (tar_T > cur_Heading) {
                rel_angle =  0.5;
            } else {
                rel_angle = -0.5;
            }
        } else {
            rel_angle = ((tar_T - cur_Heading) / 30) * 0.5;
        }

        double powerFraction = FAST_POWER_FRACTION;
        double distanceAway = Math.sqrt(rel_pos_x*rel_pos_x + rel_pos_y*rel_pos_y);
        if (distanceAway < 6.0) {
            powerFraction = Math.max(SLOW_POWER_FRACTION, distanceAway / 10);
        }


        double lfp = ((rel_pos_y + -rel_pos_x) + rel_angle);
        double lbp = ((rel_pos_y +  rel_pos_x) + rel_angle);
        double rfp = ((-rel_pos_y +  rel_pos_x) - rel_angle);
        double rbp = ((-rel_pos_y + -rel_pos_x) - rel_angle);

        if ((Math.abs(lfp) >= 1) || (Math.abs(rfp) >= 1) || (Math.abs(lbp) >= 1) || (Math.abs(rbp) >= 1)) {
            double highestPower = Math.max(Math.max(Math.abs(lfp), Math.abs(rfp)), Math.max(Math.abs(rbp), Math.abs(lbp)));
            lfp = lfp / highestPower;
            lbp = lbp / highestPower;
            rfp = rfp / highestPower;
            rbp = rbp / highestPower;
        }


        leftFront.setPower(lfp);
        rightFront.setPower(rfp);
        leftBack.setPower(lbp);
        rightBack.setPower(rbp);


        telemetry.addData("rel_X", rel_pos_x);
        telemetry.addData("rel_Y", rel_pos_y);
        telemetry.addData("rel_T", rel_angle);
        telemetry.addData("curT", cur_Heading);
        telemetry.addData("Beta", beta);
        telemetry.addData("lfp", lfp);
        telemetry.addData("rfp", rfp);
        telemetry.addData("lbp", lbp);
        telemetry.addData("rbp", rbp);


        /*
        double dx = tar_pos_X-cur_Pos_X;
        double dy = tar_pos_Y-cur_Pos_Y;
        double dAngle = tar_T - cur_Heading;
        while (dAngle > Math.PI) dAngle -= 2*Math.PI;
        while (dAngle < -Math.PI) dAngle += 2*Math.PI;
        double forward = dx*Math.cos(cur_Heading)+dy*Math.sin(cur_Heading);
        double strafe = -dx*Math.sin(cur_Heading)+dy*Math.cos(cur_Heading);
        double lfp=forward-strafe-dAngle;
        double lbp=forward+strafe-dAngle;
        double rfp=-forward+strafe+dAngle;
        double rbp=-forward-strafe+dAngle;
        double maxPower=Math.max(Math.max(Math.abs(lfp), Math.abs(rfp)), Math.max( Math.abs(lbp), Math.abs(rbp)));
        if (maxPower >1){
            lfp /= maxPower;
            lbp /= maxPower;
            rfp /= maxPower;
            rbp /= maxPower;
        }
        leftFront.setPower(lfp);
        rightFront.setPower(rfp);
        leftBack.setPower(lbp);
        rightBack.setPower(rbp);



        telemetry.addData("curT", cur_Heading);

        telemetry.addData("lfp", lfp);
        telemetry.addData("rfp", rfp);
        telemetry.addData("lbp", lbp);
        telemetry.addData("rbp", rbp);

         */




    }



    

    public void stop () {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        }

}






