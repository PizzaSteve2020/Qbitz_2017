package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;

public class MechDrive {
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;



    public MechDrive(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(MechDriveDirections direction, float centimeters) {
        VectorF vehicleForceVector;
        VectorF F1;
        VectorF F2;
        VectorF F3;
        VectorF F4;

        switch (direction) {
            case North:
                vehicleForceVector = new VectorF(0,1).multiplied(centimeters);
                F1 = getWheelForce(0.25*vehicleForceVector.magnitude());
                F2 = getWheelForce(0.25*vehicleForceVector.magnitude());
                F3 = getWheelForce(0.25*vehicleForceVector.magnitude());
                F4 = getWheelForce(0.25*vehicleForceVector.magnitude());
                break;
            case NorthEast:

                break;
            case East:

                break;
            case SouthEast:

                break;
            case South:
                vehicleForceVector = new VectorF(0,-1).multiplied(centimeters);
                F1 = getWheelForce(0.25*vehicleForceVector.magnitude()).multiplied(-1);
                F2 = getWheelForce(0.25*vehicleForceVector.magnitude()).multiplied(-1);
                F3 = getWheelForce(0.25*vehicleForceVector.magnitude()).multiplied(-1);
                F4 = getWheelForce(0.25*vehicleForceVector.magnitude()).multiplied(-1);
                break;
            case SouthWest:

                break;
            case West:

                break;
            case NorthWest:

                break;
        }
    }

    public void rotate(double angle) {

    }

    //Gets the wheel force vector from the magnitude of the parallel force vector.
    //The magnitude of the parallel force vector is equal to 1/n of the magnitude of
    //the vehicle force vector where n is the number of wheels the load will be spilt amongst.
    public VectorF getWheelForce(double parallelForceMagnitude){
        VectorF wheelForce = new VectorF(0, (float) (parallelForceMagnitude/Math.sin(45)));
        return wheelForce;
    }

}
