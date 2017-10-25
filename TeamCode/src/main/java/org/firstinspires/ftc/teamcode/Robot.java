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
    public Robot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
    }

    public void Drive(MechDriveDirections direction, double centimeters) {
        switch(direction) {
            case MechDriveDirections.North:
                //John insert code here
                break;
            case MechDriveDirections.NorthEast:
                //Steve insert code here
                break;
            case MechDriveDirections.East:
                //Mark insert code here
                break;
            case MechDriveDirections.SouthEast:
                //Caroline insert code here
                break;
            case MechDriveDirections.South:
                //Jborn insert code here
                break;
            case MechDriveDirections.SouthWest:
                //Steve insert code here
                break;
            case MechDriveDirections.West:
                //Mark insert code here
                break;
            case MechDriveDirections.NorthWest:
                //Caroline insert code here
                break;
        }
    }

    public void Rotate(double angle) {
        //Grace put stuff here

    }
}
