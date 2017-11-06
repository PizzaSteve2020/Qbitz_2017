package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


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

        while (opModeIsActive()) {
            fullCircleStrafe(gamepad1.left_stick_x,gamepad1.left_stick_y);
        }
    }
    private void fullCircleStrafe(double gamepadX, double gamepadY) {

        double angleOffOfYaxisDegrees = (Math.acos(gamepadX))*(180/Math.PI);
        double angleOffOfYaxisPlusFourtyFive = angleOffOfYaxisDegrees + 45;
        double gamepadXValuePlusFourtyFive = Math.cos(angleOffOfYaxisPlusFourtyFive);
        double gamepadYValuePlusFourtyFive = Math.sin(angleOffOfYaxisPlusFourtyFive);

        double powerA; // frontLeft and backRight motors
        double powerB; // frontRight and backLeft motors
        //Quadrant 1
        if((gamepadX > 0) && (gamepadY > 0)) {
            powerA = gamepadXValuePlusFourtyFive;
            powerB = gamepadYValuePlusFourtyFive;

            frontLeft.setPower(powerA);
            backRight.setPower(powerA);
            frontRight.setPower(powerB);
            backLeft.setPower(powerB);
        }
        //Quadrant 2
        if((gamepadX > 0) && (gamepadY < 0)) {
            powerA = gamepadXValuePlusFourtyFive;
            powerB = -gamepadYValuePlusFourtyFive;

            frontLeft.setPower(powerA);
            backRight.setPower(powerA);
            frontRight.setPower(powerB);
            backLeft.setPower(powerB);
        }
        //Quadrant 3
        if((gamepadX < 0) && (gamepadY > 0)) {
            powerA = gamepadXValuePlusFourtyFive;
            powerB = gamepadYValuePlusFourtyFive;

            frontLeft.setPower(powerA);
            backRight.setPower(powerA);
            frontRight.setPower(powerB);
            backLeft.setPower(powerB);
        }
        //Quadrant 4
        if((gamepadX < 0) && (gamepadY < 0)) {
            powerA = gamepadXValuePlusFourtyFive;
            powerB = gamepadYValuePlusFourtyFive;

            frontLeft.setPower(powerA);
            backRight.setPower(powerA);
            frontRight.setPower(powerB);
            backLeft.setPower(powerB);
        }
    }
}
    /*
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

*/