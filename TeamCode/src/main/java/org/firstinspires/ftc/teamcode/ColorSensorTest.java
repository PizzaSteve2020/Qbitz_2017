package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="ColorSensorTest", group="LinearOpMode")
public class ColorSensorTest extends AutonomousExtendableClass {

    ColorSensor colorSensor;

    @Override
    public void runOpMode() {

        colorSensor = (LynxI2cColorRangeSensor) hardwareMap.get("colorSensor");

        waitForStart();

        if(colorSensor.red() >= 100) {
            encoderDrive(0.5,5,5,10);
        }

    }
}