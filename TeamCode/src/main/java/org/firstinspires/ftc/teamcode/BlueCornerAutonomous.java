package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Steve on 12/3/2017.
 */
@Autonomous(name="BlueCorner", group="AutonomousExtendableClass")
public class BlueCornerAutonomous extends AutonomousExtendableClass {

    private VuforiaLocalizer vuforia;


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
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey="ATy+Mon/////AAAAmdO7ijESrEYksmtbbrhsJ0h8pd0hCk5Vcp0EgfFUzSv7qvMD+pVBK+McXiTjFO/nc1jCMers6XjWglIGEGM+2nQ62R4An/KtVZYx8pcAJvoMNsgmr/IjFu1kZX+X2cWrplyV9eHUgaL+IcWzVzzKUG0vHzffzcLeMzEWs3mafEs9geCmYuyIjEzhiqLdQHL2gYjrMNSbA5K4iFjlU6Gxka0sR4Q2/YtR1RDVGPjo+4Dv+m7/4cthw32VEb8Mw0+xiRWcAKdDZO2Tf7CKFr5T7h5abyv8il+Dw5vN61kkqChalpel/ebyj2sQf3Ag09Z2b/PRVtOmwIYwp4s5k/TkWdbc95RvS10IM8bOVlLUaGim";
        parameters.cameraDirection= VuforiaLocalizer.CameraDirection.BACK;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        waitForStart();

        setGripper1(1);
        setGripper2(1);

        encoderDrive(1, 31, 31, 5);
        encoderDrive(1, -16, 16, 5);
        encoderDrive(1, 8, 8, 5);

        setGripper1(gripper1.getPosition() - 0.3);
        setGripper2(gripper2.getPosition() - 0.3);
        setGripper1(gripper1.getPosition() - 0.3);
        setGripper2(gripper2.getPosition() - 0.3);
        setGripper1(gripper1.getPosition() - 0.3);
        setGripper2(gripper2.getPosition() - 0.3);


        encoderDrive(1, -10, -10, 5);

        sleep(1000);
/*
        while(opModeIsActive()) {
            RelicRecoveryVuMark vuMark =  RelicRecoveryVuMark.from(relicTemplate);

            if(vuMark!=RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();

                switch(vuMark) {
                    case LEFT:
                        encoderDrive(1,30,0,5);
                        break;
                    case CENTER:


                        break;
                    case RIGHT:
                        encoderDrive(1,0,30,5);
                        break;


                }

            }



        }

*/

            //This is on Github now :)
        }
    }

