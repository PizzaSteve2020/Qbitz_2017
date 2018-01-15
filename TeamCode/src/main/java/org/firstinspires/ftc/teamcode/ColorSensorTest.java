package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="ColorSensorTest", group="LinearOpMode")
public class ColorSensorTest extends AutonomousExtendableClass {

    ColorSensor colorSensor;

    @Override
    public void runOpMode() {

/*
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

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setDirection(DcMotor.Direction.FORWARD);

        gripper1 = hardwareMap.get(Servo.class, "gripper1");
        gripper2 = hardwareMap.get(Servo.class, "gripper2");
        jewelDisplacer = hardwareMap.get(Servo.class, "jewelDisplacer");
        */
        colorSensor = (LynxI2cColorRangeSensor) hardwareMap.get("colorSensor");

        waitForStart();

        if(colorSensor.red()>40) {
            encoderDrive(0.5,-5,5,10);
            encoderDrive(0.5,5,-5,10);
        } else {
            encoderDrive(0.5,5,-5,10);
            encoderDrive(0.5,-5,5,10);
        }
        while(opModeIsActive()) {
            telemetry.addData("Red", colorSensor.red());
            telemetry.update();
        }
    }
}