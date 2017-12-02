package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Rawley on 11/30/2017.
 */
@TeleOp(name="TeleOpMode", group="LinearOpMode")
public class TeleOpMode extends LinearOpMode {
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor glyphRotator = null;

    private Servo gripper1 = null;
    private Servo gripper2 = null;
    //private Servo jewelDisplacer = null;

    private double clawOffset = 0;
    private final double CLAW_SPEED = 0.02;
    private final double MID_SERVO = 0.5;



    private boolean gripper1IsBottom = true;


    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backRight.setDirection(DcMotor.Direction.FORWARD);

        glyphRotator = hardwareMap.get(DcMotor.class, "glyphRotator");
        glyphRotator.setDirection(DcMotor.Direction.FORWARD);

        gripper1 = hardwareMap.get(Servo.class, "gripper1");
        gripper2 = hardwareMap.get(Servo.class, "gripper2");
       // jewelDisplacer = hardwareMap.get(Servo.class, "jewelDisplacer");


        waitForStart();

        while (opModeIsActive()) {
            fullCircleStrafe();
            glyphGripper();
        }
    }
        private void fullCircleStrafe() {
        double radius = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rotation = gamepad1.right_stick_x;

        final double frontLeftPower = radius * Math.cos(robotAngle) - rotation;
        final double frontRightPower = radius * Math.sin(robotAngle) + rotation;
        final double backLeftPower = radius * Math.sin(robotAngle) - rotation;
        final double backRightPower = radius * Math.cos(robotAngle) + rotation;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

        private void glyphGripper() {

            if (gripper1IsBottom == true) {
                if (gamepad1.right_bumper)
                    clawOffset += CLAW_SPEED;
                else if (gamepad1.left_bumper) {
                    clawOffset -= CLAW_SPEED;
                }
                clawOffset = Range.clip(clawOffset, -0.5, 0.5);
                gripper1.setPosition(MID_SERVO + clawOffset);
            }

            else {
                if (gamepad1.right_bumper)
                    clawOffset += CLAW_SPEED;
                else if (gamepad1.left_bumper) {
                    clawOffset -= CLAW_SPEED;
                }
                clawOffset = Range.clip(clawOffset, -0.5, 0.5);
                gripper2.setPosition(MID_SERVO + clawOffset);
            }

            if(gamepad1.left_trigger>0.3) {
                rotateGripper180();

            }
        }
        private void rotateGripper180() {
            if (gripper1IsBottom == true) {
                glyphRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                glyphRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                glyphRotator.setTargetPosition(610);

                glyphRotator.setPower(0.5);

                gripper1IsBottom = !gripper1IsBottom;
            }
            if (gripper1IsBottom == false) {
                glyphRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                glyphRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                glyphRotator.setTargetPosition(610);

                glyphRotator.setPower(0.5);

                gripper1IsBottom = !gripper1IsBottom;
            }
        }
}

