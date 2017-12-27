package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Steve on 12/1/2017.
 */
public abstract class AutonomousExtendableClass extends LinearOpMode {
    protected DcMotor frontLeft = null;
    protected DcMotor frontRight = null;
    protected DcMotor backLeft = null;
    protected DcMotor backRight = null;

    protected DcMotor glyphRotator = null;
    protected DcMotor linearSlide = null;
    protected Servo gripper1 = null;
    protected Servo gripper2 = null;
    protected Servo jewelDisplacer = null;

    protected ColorSensor colorSensor = null;
    protected ElapsedTime runtime = new ElapsedTime();

    protected static final double COUNTS_PER_MOTOR_REV = 1120;
    protected static final double DRIVE_GEAR_REDUCTION = 1.5;     // This is < 1.0 if geared UP
    protected static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    protected static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    protected static final double DRIVE_SPEED = 0.5;
    protected static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {



    }
    protected void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() + (int) (0.5*leftInches*(10/7) * COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int) (0.5*rightInches*(10/7) * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int) (0.5*leftInches*(10/7) * COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() + (int) (0.5*rightInches*(10/7) * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFrontLeftTarget);
            frontRight.setTargetPosition(newFrontRightTarget);
            backLeft.setTargetPosition(newBackLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            frontLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));
            // kneep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) i the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    protected void encoderStrafe(double speed, double inchesRight, double timeoutS) {

        resetEncoders();

        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() + (int) (0.5*inchesRight* COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int) (0.5*inchesRight * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int) (0.5*inchesRight* COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() + (int) (0.5*inchesRight* COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFrontLeftTarget);
            frontRight.setTargetPosition(-newFrontRightTarget);
            backLeft.setTargetPosition(-newBackLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            frontLeft.setPower(Math.abs(1.15 * speed));
            frontRight.setPower(Math.abs(1.15 *speed));
            backLeft.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));
            // kneep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) i the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    protected void strafeNWandSE(double speed, double inchesDiagonalRight, double timeoutS) {
        int newFrontRightTarget;
        int newBackLeftTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newFrontRightTarget = frontRight.getCurrentPosition() + (int) (0.5*inchesDiagonalRight*(10/3) * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int) (0.5*inchesDiagonalRight*(10/3) * COUNTS_PER_INCH);

            frontRight.setTargetPosition(newFrontRightTarget);
            backLeft.setTargetPosition(newBackLeftTarget);

            // Turn On RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();

            frontRight.setPower(Math.abs(-speed));
            backLeft.setPower(Math.abs(speed));
            // kneep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) i the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newBackLeftTarget, newFrontRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontRight.setPower(0);
            backLeft.setPower(0);


            // Turn off RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }

    }

    protected void strafeNEandSW(double speed , double inchesDiagonalLeft , double timeoutS) {
        int newFrontLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newFrontLeftTarget = frontRight.getCurrentPosition() + (int) (0.5*inchesDiagonalLeft*(10/3) * COUNTS_PER_INCH);
            newBackRightTarget = backLeft.getCurrentPosition() + (int) (0.5*inchesDiagonalLeft*(10/3) * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFrontLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();

            frontLeft.setPower(Math.abs(-speed));
            backRight.setPower(Math.abs(speed));
            // kneep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) i the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newBackRightTarget, newFrontLeftTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        frontLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeft.setPower(0);
            backRight.setPower(0);


            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    protected void extendDisplacerArm() {
        jewelDisplacer.setPosition(0.5);

    }

    protected void getColorAndDisplace() {
        extendDisplacerArm();
        sleep(500);
        int color = colorSensor.argb();
        if(color>=350 || color<=5) {
            encoderDrive(0.4, 3, -3, 1);
            encoderDrive(0.4, -3, 3, 1);
        }
        if(color>=180 && color <=210) {
            encoderDrive(0.4,-3, 3,1);
            encoderDrive(0.4, 3, -3, 1);
        }
    }

    protected void setGripper1(double position) {
        gripper1.setPosition(position);

    }
    protected void setGripper2(double position) {
        gripper2.setPosition(position);
    }



}

