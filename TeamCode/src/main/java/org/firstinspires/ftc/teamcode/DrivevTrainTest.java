package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Steve on 10/22/2017.
 */
@TeleOp(name="DriveTrainTest", group="Linear Opmode")
public class DrivevTrainTest extends LinearOpMode {
    double powerLeft;
    double powerRight;
    double strafePower = 0.5;
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;
    public void runOpMode() {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr = hardwareMap.get(DcMotor.class, "fr");
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl = hardwareMap.get(DcMotor.class, "bl");
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br = hardwareMap.get(DcMotor.class, "br");
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            powerLeft = gamepad1.left_stick_y;
            powerRight = gamepad1.right_stick_y;
            fl.setPower(powerLeft);
            bl.setPower(powerLeft);
            fr.setPower(powerRight);
            br.setPower(powerRight);


            while(gamepad1.left_bumper==true) {
                fl.setPower(strafePower);
                bl.setPower(-strafePower);
                fr.setPower(-strafePower);
                br.setPower(strafePower);
            }

            while(gamepad1.right_bumper==true) {
                fl.setPower(-strafePower);
                bl.setPower(strafePower);
                fr.setPower(strafePower);
                br.setPower(-strafePower);
            }


        }
    }
}
