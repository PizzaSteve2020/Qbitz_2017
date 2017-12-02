package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.teamcode.Servo_test.MID_SERVO;


@TeleOp(name="DriveTrainTest", group="LinearOpMode")
public class DriveTrainTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor glyphRotator;

    private double clawOffset = 0;
    private final double CLAW_SPEED = 0.02;
    private final double MID_SERVO = 0.5;

    private Servo gripper1 = null;
    private Servo gripper2 = null;
    private Servo jewelDisplacer = null;

    private boolean gripper1isBottom = true;


    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backRight.setDirection(DcMotor.Direction.REVERSE);
        glyphRotator = hardwareMap.get(DcMotor.class, "glyphRotator");

        gripper1 = hardwareMap.get(Servo.class, "gripper1");
        gripper2 = hardwareMap.get(Servo.class, "gripper2");
        jewelDisplacer = hardwareMap.get(Servo.class, "jewelDisplacer");

        waitForStart();

        while (opModeIsActive()) {
            fullCircleStrafe();
            glyphGripper();
        }
    }

    private void fullCircleStrafe() {
        double radius = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y) - Math.PI / 4;
        double rotation = gamepad1.right_stick_x;

        final double frontLeftPower = radius * Math.cos(robotAngle) + rotation;
        final double frontRightPower = radius * Math.sin(robotAngle) - rotation;
        final double backLeftPower = radius * Math.sin(robotAngle) + rotation;
        final double backRightPower = radius * Math.cos(robotAngle) - rotation;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);


    }

    private void glyphGripper() {
        if (gripper1isBottom == true) {
            if (gamepad1.right_bumper)
                clawOffset += CLAW_SPEED;
            else if (gamepad1.left_bumper) {
                clawOffset -= CLAW_SPEED;
            }

            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            gripper1.setPosition(MID_SERVO + clawOffset);
            // Move both servos to new position.  Assume servos are mirror image of each other.


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