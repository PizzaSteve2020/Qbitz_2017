package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Steve on 11/3/2017.
 */
@TeleOp(name="DriveTrainTest", group="LinearOpMode")
public class DriveTrainTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


    waitForStart();
    while(opModeIsActive()) {
        driveVertical();
        strafe();
    }
    }

    private void driveVertical() {
        double powerLeft = gamepad1.left_stick_y;
        double powerRight = gamepad1.right_stick_y;
        frontLeft.setPower(powerLeft);
        frontRight.setPower(powerRight);
        backLeft.setPower(powerLeft);
        backRight.setPower(powerRight);
    }

    private void strafe() {
        if(gamepad1.right_bumper==true) {
            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(-1);
        }
        if(gamepad1.left_bumper==true) {
            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(1);
        }
    }
}
