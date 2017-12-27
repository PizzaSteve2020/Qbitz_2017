package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class Percepts {
    private BNO055IMU imu;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;

    private static Percepts instance;

    public static Percepts getInstance(){
        if(instance == null){
            instance = new Percepts();
        }
        return instance;
    }

    private Percepts(){ }

    public void initialize(HardwareMap hardwareMap){
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "colorSensor");
    }
//get color
    public Colors getColor(){
        int hue = colorSensor.argb();
        if(hue >= 350 || hue <= 5)
            return Colors.Red;
        if(hue >= 180 && hue <= 210)
            return Colors.Blue;
        return Colors.Unknown;
    }

    public int getHue(){
        return colorSensor.argb();
    }

    public double getDistance(){
        double distance = distanceSensor.getDistance(DistanceUnit.CM);
        return distance;
    }

    public Acceleration getAcceleration(){
        Acceleration acceleration = imu.getAcceleration();
        return acceleration;
    }

    public Velocity getVelocity(){
        Velocity velocity = imu.getVelocity();
        return velocity;
    }

    public Position getPosition(){
        Position position = imu.getPosition();
        return position;
    }

    public Quaternion getOrientation() {
        Quaternion orientation = imu.getQuaternionOrientation();
        return orientation;
    }
}