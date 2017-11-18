package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;

import java.util.Vector;

import static org.firstinspires.ftc.teamcode.MechDriveDirections.North;

public class Robot {
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    BNO055IMU imu;

    private final double wheelCircumference = 10.16*Math.PI;

    private final int encoderTicks = 1220;

    public Robot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }

    public void drive(MechDriveDirections direction, int centimeters) {
        switch (direction) {
            case North:
                //John insert code here

                break;
            case NorthEast:
                driveDiagonal(centimeters, frontLeft, backRight);
                break;
            case East:
                //Mark insert code here
                break;
            case SouthEast:
                driveDiagonal(-centimeters, frontRight, backLeft);
                break;
            case South:
                //John insert code here
                break;
            case SouthWest:
                driveDiagonal(-centimeters, frontLeft, backRight);
                break;
            case West:
                //Mark insert code here
                break;
            case NorthWest:
                driveDiagonal(centimeters, frontRight, backLeft);
                break;
        }
    }



    private void driveVertical (int centimeters){

    }

    private void driveHorizontal (int centimeters){

    }

    private void driveDiagonal(double centimeters, DcMotor motor1, DcMotor motor2){
        //One encoder tick in setTargetPosition is equal to about 0.026 cm using our 4 inch diameter wheels
        //Our wheel circumference is about 32 centimeters
        double centimetersDividedByWheelCircumference = centimeters/wheelCircumference;

        //Resets Encoders
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Sets mode to run using encoders
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Sets the position we need to reach
        motor1.setTargetPosition( (int) Math.round( centimetersDividedByWheelCircumference*encoderTicks) );
        motor2.setTargetPosition( (int) Math.round( centimetersDividedByWheelCircumference*encoderTicks) );

        //Sets power to apply to motors UNTIL it reaches target position
        double power = centimeters > 0 ? 0.5 : -0.5;
        motor1.setPower(power);
        motor2.setPower(power);
    }

    public void rotate(double angle) {
        frontLeft.setPower(angle);
        backLeft.setPower(angle);
        frontRight.setPower(-angle);
        backRight.setPower(-angle);
    }

    public void moveToPoint(Position pointToMoveTo) {
        //Step one get -- current Vector and target Vector
        VectorF currentVector = getCurrentVector();
        VectorF targetVector = getTargetVector( imu.getPosition(), pointToMoveTo );

        //Step two -- get angle Theta and rotate to angle theta
        double angleTheta = getAngle(currentVector, targetVector);
        rotate(angleTheta);

        //Step three -- Call drive method to move to point
        drive(North,(int)targetVector.magnitude());
    }

    private VectorF getDriveDiagonalFrontFightWheelVector(VectorF currentVector) {
        VectorF frontRightWheelVector;

        if(currentVector.magnitude()>0) {
            return frontRightWheelVector = new VectorF(currentVector.multiplied( Math.cos(45)*Math.sin(45) * Math.pow(-1,2) *1 ) );
        } else {
            return frontRightWheelVector = new VectorF(currentVector.multiplied(Math.cos(45)*Math.sin(45)*Math.pow(-1,4) * -1));
        }
    }
    private VectorF getDriveDiagonalBackLeftWheelVector(VectorF currentVector) {
        VectorF backLeftWheelVector;
        if(currentVector.magnitude()>0) {
            return backLeftWheelVector = new VectorF(currentVector.multiplied((Math.cos(45)*Math.sin(45)*Math.pow(-1,4) * 1));
        } else {
            return backLeftWheelVector = new VectorF(currentVector.multiplied(Math.cos(45)*Math.sin(45)*Math.pow(-1,4) * -1));
        }
    }


    private VectorF getCurrentVector(){
        Quaternion orientation = imu.getQuaternionOrientation();
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
}