package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Mech Drive Motioln UNit Test", group = "Unit Test")
public class MechDriveMotionUnitTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        MechDrive mechDrive = new MechDrive(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.dpad_up && gamepad1.dpad_right)
                mechDrive.drive(MechDriveDirections.NorthEast,1);
            else if(gamepad1.dpad_right && gamepad1.dpad_down)
                mechDrive.drive(MechDriveDirections.SouthEast,1);
            else if(gamepad1.dpad_down && gamepad1.dpad_left)
                mechDrive.drive(MechDriveDirections.SouthWest,1);
            else if(gamepad1.dpad_left && gamepad1.dpad_up)
                mechDrive.drive(MechDriveDirections.NorthEast,1);
            else if(gamepad1.dpad_up)
                mechDrive.drive(MechDriveDirections.North,1);
            else if(gamepad1.dpad_right)
                mechDrive.drive(MechDriveDirections.East,1);
            else if(gamepad1.dpad_down)
                mechDrive.drive(MechDriveDirections.South,1);
            else if(gamepad1.dpad_left)
                mechDrive.drive(MechDriveDirections.West,1);
        }
    }
}
