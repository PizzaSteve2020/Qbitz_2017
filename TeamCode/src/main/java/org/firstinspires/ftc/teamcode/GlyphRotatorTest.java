package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by olson on 2/7/2018.
 */
@TeleOp(name="Rotator Test")
public class GlyphRotatorTest extends LinearOpMode {
    private int oneEighty = 1120/2;


    private DcMotor glyphRotator = null;
    @Override
    public void runOpMode() throws InterruptedException {
        glyphRotator = hardwareMap.get(DcMotor.class, "glyphRotator");
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a && !glyphRotator.isBusy()) {


                glyphRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                glyphRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                glyphRotator.setTargetPosition(oneEighty);
                glyphRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                glyphRotator.setPower(.5);

                glyphRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
               glyphRotator.setPower(0);

            }
            telemetry.addData("GlyphPosition", glyphRotator.getCurrentPosition());
            telemetry.update();
        }

    }
    }

