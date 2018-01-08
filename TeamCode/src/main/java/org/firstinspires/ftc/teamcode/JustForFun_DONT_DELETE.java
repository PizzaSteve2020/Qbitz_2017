package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Steve on 1/8/2018.
 */
@Autonomous(name="Demo / Mess around :)")
public class JustForFun_DONT_DELETE extends AutonomousExtendableClass {
    @Override
    public void runOpMode() {

        waitForStart();

        encoderDrive(1, -50, 50, 20);
        sleep(250);
        encoderDrive(1,50,-50, 20);
        for(int i = 0; i<10;i++) {
            encoderDrive(0.5, 5, -5, 5);
            encoderDrive(0.5, -5, 5, 5);
        }

    }
}
