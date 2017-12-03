package org.firstinspires.ftc.teamcode;

/**
 * Created by Steve on 12/3/2017.
 */

public class RedMidPlateAutonomous extends AutonomousExtendableClass {
    @Override
    public void runOpMode() {

        waitForStart();

        encoderDrive(DRIVE_SPEED,40,40,5);
        encoderDrive(DRIVE_SPEED,12,-12,3);
        setGripper1(0.7);
    }
}
