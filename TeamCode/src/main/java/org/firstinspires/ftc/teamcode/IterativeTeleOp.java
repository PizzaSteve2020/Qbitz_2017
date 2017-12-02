package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Rawley on 12/1/2017.
 */

public class IterativeTeleOp extends OpMode {
    private DcMotor servo = null;
    @Override
    public void init() {
        servo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    @Override
    public void init_loop() {

    }
    @Override
    public void loop() {

        servo.setTargetPosition(610);
        servo.setPower(0.5);

    }

    @Override
    public void start() {

    }
    @Override
    public void stop() {

    }
}
