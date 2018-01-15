package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Steve on 12/3/2017.
 */
@Autonomous(name="RedMid", group="AutonomousExtendableClass")
public class RedMidPlateAutonomous extends AutonomousExtendableClass {
    VuforiaLocalizer vuforia;
    private ElapsedTime runtime = new ElapsedTime();
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

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey="ATy+Mon/////AAAAmdO7ijESrEYksmtbbrhsJ0h8pd0hCk5Vcp0EgfFUzSv7qvMD+pVBK+McXiTjFO/nc1jCMers6XjWglIGEGM+2nQ62R4An/KtVZYx8pcAJvoMNsgmr/IjFu1kZX+X2cWrplyV9eHUgaL+IcWzVzzKUG0vHzffzcLeMzEWs3mafEs9geCmYuyIjEzhiqLdQHL2gYjrMNSbA5K4iFjlU6Gxka0sR4Q2/YtR1RDVGPjo+4Dv+m7/4cthw32VEb8Mw0+xiRWcAKdDZO2Tf7CKFr5T7h5abyv8il+Dw5vN61kkqChalpel/ebyj2sQf3Ag09Z2b/PRVtOmwIYwp4s5k/TkWdbc95RvS10IM8bOVlLUaGim";
        parameters.cameraDirection= VuforiaLocalizer.CameraDirection.BACK;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        waitForStart();
        /*
        extendDisplacerArm();
        if(colorSensor.red()>40) {
            encoderDrive(0.5,-5,5,10);
            encoderDrive(0.5,5,-5,10);
        } else {
            encoderDrive(0.5,5,-5,10);
            encoderDrive(0.5,-5,5,10);
        }
        jewelDisplacer.setPosition(jewelDisplacer.getPortNumber()-0.3);
        jewelDisplacer.setPosition(jewelDisplacer.getPortNumber()-0.3);
        jewelDisplacer.setPosition(jewelDisplacer.getPortNumber()-0.3);
        jewelDisplacer.setPosition(jewelDisplacer.getPortNumber()-0.3);
*/
        relicTrackables.activate();
        runtime.reset();
        while(opModeIsActive()) {
            RelicRecoveryVuMark vuMark =  RelicRecoveryVuMark.from(relicTemplate);
        if(runtime.seconds()<10) {
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();

                switch (vuMark) {
                    case LEFT:
                        encoderDrive(1, 30, 0, 5);
                        break;
                    case CENTER:
                        encoderDrive(1, 30, 30, 5);
                        break;
                    case RIGHT:
                        encoderDrive(1, 0, 30, 5);
                        break;


                }

            }

        } else {
            setGripper1(1);
            setGripper2(1);

            encoderDrive(1, 25, 25, 5);
            encoderDrive(1, -16, 16, 5);
            encoderDrive(1, 11, 11, 5);
            encoderDrive(1,16,-16,5);
            encoderDrive(1,12,12,5);


            setGripper1(gripper1.getPosition() - 0.3);
            setGripper2(gripper2.getPosition() - 0.3);
            setGripper1(gripper1.getPosition() - 0.3);
            setGripper2(gripper2.getPosition() - 0.3);
            setGripper1(gripper1.getPosition() - 0.3);
            setGripper2(gripper2.getPosition() - 0.3);


            encoderDrive(1, -8, -8, 5);

            sleep(30000); // till end of autonomous period
        }



        }

    }
}
//comment