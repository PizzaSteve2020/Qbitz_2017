package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Steve on 1/4/2018.
 */
@TeleOp(name="ColorSensorPleaseWork",group="colorSensor")
public class ColorSensorPleaseWork extends AutonomousExtendableClass {
    ColorSensor colorSensor = null;
    Percepts percepts = Percepts.getInstance();

    @Override
    public void runOpMode() {

        waitForStart();
        while(opModeIsActive()) {
            /*
            telemetry.addData("Hue", percepts.getHue());
            telemetry.addData("alpha", percepts.getAlpha());
            telemetry.addData("Color", percepts.getColor());
*/
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue",colorSensor.blue());
            telemetry.update();
        }
    }
}
