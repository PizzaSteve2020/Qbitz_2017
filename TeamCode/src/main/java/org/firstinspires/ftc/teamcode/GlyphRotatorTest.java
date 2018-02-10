package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by olson on 2/7/2018.
 */
@Autonomous(name="Phone Test")
public class GlyphRotatorTest extends LinearOpMode {
    private int oneEighty = 1120/2;


    private DcMotor glyphRotator = null;
    private Servo phone = null;
    @Override
    public void runOpMode() throws InterruptedException {
        glyphRotator = hardwareMap.get(DcMotor.class, "glyphRotator");
        phone = hardwareMap.get(Servo.class, "phone");

        waitForStart();
            phone.setPosition(0.1);
            sleep(250);
            phone.setPosition(0.2);
            sleep(250);
            phone.setPosition(0.3);
            sleep(250);
            phone.setPosition(0.4);
            sleep(250);
            phone.setPosition(0.5);
            sleep(250);
            phone.setPosition(0.6);
            sleep(250);
            phone.setPosition(0.7);
            sleep(250);
            phone.setPosition(0.8);
            sleep(250);
            phone.setPosition(0.9);
            sleep(250);
            phone.setPosition(1);


    }
    }

