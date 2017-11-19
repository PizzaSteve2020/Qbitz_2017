package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.teamcode.MechDriveDirections.North;

public class Robot {
    private final MechDrive mechDrive;
    private final Percepts percepts;

    VuforiaLocalizer vuforia;


    private final double wheelCircumference = 10.16*Math.PI;

    private final int encoderTicks = 1220;

    public Robot(HardwareMap hardwareMap) {
        mechDrive = new MechDrive(hardwareMap);
        percepts = Percepts.getInstance();
        percepts.initialize(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        // parameters.vuforiaLicenseKey();
    }

    public void drive(MechDriveDirections direction, float centimeters) {
       mechDrive.drive(direction,centimeters);
    }


       public void moveToPoint(Position pointToMoveTo) {
        //Step one get -- current Vector and target Vector
        VectorF currentVector = getCurrentVector();
        VectorF targetVector = getTargetVector( percepts.getPosition(), pointToMoveTo );

        //Step two -- get angle Theta and rotate to angle theta
        double angleTheta = getAngle(currentVector, targetVector);
        mechDrive.rotate(angleTheta);

        //Step three -- Call drive method to move to point
        drive(North,(int)targetVector.magnitude());
    }

    public VectorF getWheelForce(double parallelForceMagnitude){
        VectorF wheelForce = new VectorF(0, (float) (parallelForceMagnitude/Math.sin(45)));
        return wheelForce;
    }

    private VectorF getCurrentVector(){
        Quaternion orientation = percepts.getOrientation();
        VectorF currentVector = new VectorF(orientation.x,orientation.y);
        return currentVector;
    }

    private VectorF getTargetVector(Position currentPosition, Position targetPosition) {
        VectorF targetVector = new VectorF( (float)(targetPosition.x-currentPosition.x),(float)(targetPosition.y-currentPosition.y) );
        return targetVector;
    }

    private double getAngle(VectorF currentVector, VectorF targetVector) {
        return Math.acos( currentVector.dotProduct(targetVector) / currentVector.magnitude()*targetVector.magnitude() );
    }

    private RelicRecoveryVuMark getObject() {
        return RelicRecoveryVuMark.CENTER;
    }
}