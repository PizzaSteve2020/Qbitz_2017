package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Rawley on 11/28/2017.
 */
@TeleOp(name="Servo_test", group="Pushbot")
public class Servo_test extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 1220;

    double clawOffset = 0.0;                  // Servo mid position
    final double CLAW_SPEED = 0.02;                 // sets rate to move servo
    public static final double MID_SERVO = 0.5;

    private DcMotor servo = null;

    private final double COUNTS_PER_INCH = 1220 / (4 * 3.1415);

    private boolean counter = true;


    @Override
    public void runOpMode() {
        servo = hardwareMap.get(DcMotor.class, "servo");
        servo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while (opModeIsActive()) {

                rotate180();
                counterFlip();
            }

        }

    private void rotate180() {

            if (gamepad1.left_bumper && counter == true) {
                servo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                servo.setTargetPosition(560);

                servo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                servo.setPower(0.5);

                while(servo.isBusy()) {

                }

            }

    }

    private void counterFlip() {
        if (counter == false) {
            sleep(5000);
            counter = true;
        }

    }
}
