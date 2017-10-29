package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Steve on 10/24/2017.
 */

public class Robot {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private double wheelCircumference = 32;
    public Robot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void Drive(MechDriveDirections direction, int centimeters) {
        double centimetersDividedByWheelCircumference = centimeters/wheelCircumference;
        switch(direction) {
            case North:
                //John insert code here
                break;
            case NorthEast:
                //Steve insert code here
                //One encoder tick in setTargetPosition is equal to about 0.026 cm using our 4 inch diameter wheels
                //Our wheel circumference is about 32 centimeters
                //Resets Encoders
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //Sets mode to run using encoders
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //Sets the position we need to reach
                frontLeft.setTargetPosition( (int) Math.round( centimetersDividedByWheelCircumference*1220 ) );
                backRight.setTargetPosition( (int) Math.round( centimetersDividedByWheelCircumference*1220 ) );
                //Sets power to apply to motors UNTIL it reaches target position
                frontLeft.setPower(0.5);
                backRight.setPower(0.5);



                break;
            case East:
                //Mark insert code here
                break;
            case SouthEast:
                //Caroline insert code here
                break;
            case South:
            //Jborn insert code here
                break;
            case SouthWest:
                //Steve insert code here
                //Same code as NorthEast but the opposite
                //Resets Encoders
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //Sets mode to run using encoders
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //Sets the position we need to reach
                frontLeft.setTargetPosition( -(int) Math.round( centimetersDividedByWheelCircumference*1220 ) );
                backRight.setTargetPosition( -(int) Math.round( centimetersDividedByWheelCircumference*1220 ) );
                //Sets power to apply to motors UNTIL it reaches target position
                frontLeft.setPower(-0.5);
                backRight.setPower(-0.5);

                break;
            case West:
                //Mark insert code here
                break;
            case NorthWest:
                //Caroline insert code here
                break;
        }
    }

    public void Rotate(double angle) {
        //Grace put stuff here

    }
}
