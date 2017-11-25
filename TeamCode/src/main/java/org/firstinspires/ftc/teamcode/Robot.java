package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.teamcode.MechDriveDirections.North;

public class Robot {
    private final MechDrive mechDrive;
    private final Percepts percepts;

    VuforiaLocalizer vuforia;


    private final double wheelCircumference = 10.16 * Math.PI;

    private final int encoderTicks = 1220;

    public Robot(HardwareMap hardwareMap) {
        mechDrive = new MechDrive(hardwareMap);
        percepts = Percepts.getInstance();
        percepts.initialize(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        // parameters.vuforiaLicenseKey();
    }

    public void drive(MechDriveDirections direction, double time) {
        mechDrive.drive(direction, time);
    }


    public void moveToPoint(Position pointToMoveTo) {
        double threshold = 3;
        double deltaT = 0.1;
        while ((percepts.getPosition().x > pointToMoveTo.x + threshold ||
                percepts.getPosition().x < pointToMoveTo.x - threshold) &&
                (percepts.getPosition().y > pointToMoveTo.y + threshold ||
                        percepts.getPosition().y < pointToMoveTo.y - threshold)) {
            VectorF trajectory =
                    new VectorF(
                            (float) (pointToMoveTo.x - percepts.getPosition().x),
                            (float) (pointToMoveTo.y - percepts.getPosition().y));
            Velocity targetVelocity = getTargetVelocity(trajectory.magnitude());
            Velocity deltaV =
                    new Velocity(DistanceUnit.CM,
                            targetVelocity.xVeloc - percepts.getVelocity().xVeloc,
                            targetVelocity.yVeloc - percepts.getVelocity().yVeloc,
                            (double) 0, (long) 0);

            if (deltaV.yVeloc < 0)
                drive(MechDriveDirections.South, deltaT);
            else
                drive(MechDriveDirections.North, deltaT);

            if (deltaV.xVeloc < 0)
                drive(MechDriveDirections.West, deltaT);
            else
                drive(MechDriveDirections.East, deltaT);
        }
    }

    private Velocity getTargetVelocity(float magnitutde) {
        return null;
    }
}