package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Steve on 12/28/2017.
 */
@Autonomous(name="Vuforia", group ="Vuforia")
public class Vuforia extends AutonomousExtendableClass {
    VuforiaLocalizer vuforia;
    private boolean scanningTemplate = true;

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

            parameters.vuforiaLicenseKey="ATy+Mon/////AAAAmdO7ijESrEYksmtbbrhsJ0h8pd0hCk5Vcp0EgfFUzSv7qvMD+pVBK+McXiTjFO/nc1jCMers6XjWglIGEGM+2nQ62R4An/KtVZYx8pcAJvoMNsgmr/IjFu1kZX+X2cWrplyV9eHUgaL+IcWzVzzKUG0vHzffzcLeMzEWs3mafEs9geCmYuyIjEzhiqLdQHL2gYjrMNSbA5K4iFjlU6Gxka0sR4Q2/YtR1RDVGPjo+4Dv+m7/4cthw32VEb8Mw0+xiRWcAKdDZO2Tf7CKFr5T7h5abyv8il+Dw5vN61kkqChalpel/ebyj2sQf3Ag09Z2b/PRVtOmwIYwp4s5k/TkWdbc95RvS10IM8bOVlLUaGim";
        parameters.cameraDirection= VuforiaLocalizer.CameraDirection.BACK;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        waitForStart();

        relicTrackables.activate();

        getColorAndDisplace();
        encoderDrive(TURN_SPEED, 16,-16,5);

        while(scanningTemplate==true) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if(vuMark!=RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();

                switch(vuMark) {
                    case LEFT:

                        setGripper1(1);
                        setGripper2(1);
                        scanningTemplate = false;
                        break;

                    case CENTER:

                        setGripper1(1);
                        setGripper2(1);
                        scanningTemplate = false;
                        break;

                    case RIGHT:

                        setGripper1(1);
                        setGripper2(1);
                        scanningTemplate = false;
                        break;

                }


            }

        }
    }
}
