package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Steve on 12/3/2017.
 */
@Autonomous(name="RedCorner", group="AutonomousExtendableClass")
public class RedCornerAutonomous extends AutonomousExtendableClass {
    @Override
    public void runOpMode() {
        frontLeft= hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backRight.setDirection(DcMotor.Direction.FORWARD);

        glyphRotator = hardwareMap.get(DcMotor.class, "glyphRotator");
        glyphRotator.setDirection(DcMotor.Direction.FORWARD);

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setDirection(DcMotor.Direction.FORWARD);

        gripper1 = hardwareMap.get(Servo.class, "gripper1");
        gripper2 = hardwareMap.get(Servo.class, "gripper2");
        jewelDisplacer = hardwareMap.get(Servo.class, "jewelDisplacer");

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        setGripper1(1);
        setGripper2(1);
        encoderDrive(DRIVE_SPEED, 31, 31 , 5 );
        encoderDrive(TURN_SPEED, 16 , -16 , 5 );
        encoderDrive(DRIVE_SPEED, 8, 8, 5);

        setGripper1(gripper1.getPosition()-0.3);
        setGripper2(gripper2.getPosition()-0.3);
        setGripper1(gripper1.getPosition()-0.3);
        setGripper2(gripper2.getPosition()-0.3);
        setGripper1(gripper1.getPosition()-0.3);
        setGripper2(gripper2.getPosition()-0.3);

        encoderDrive(DRIVE_SPEED, -10, -10, 5);

        sleep(1000);
    }
}
