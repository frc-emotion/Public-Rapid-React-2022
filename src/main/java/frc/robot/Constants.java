package frc.robot;

public class Constants {
    // USB Ports for the Xbox Controller
    public static int DRIVER_PORT = 0;
    public static int OPERATOR_PORT = 1;
    // these ports are for the motors
    public static int[] LEFT_MOTOR_PORTS = { 3, 4, 5 };
    public static int[] RIGHT_MOTOR_PORTS = { 6, 9, 10 };

    // Max Currents
    public static int CAN_SPARK_MAX_CURRENT = 45;

    // DriveTrain Constants
    public static double DRIVETRAIN_NORMAL_SPEED = 0.7;
    public static double DRIVETRAIN_NORMAL_CURVE = 1.8;
    // I feel like I remember us having different speed modes for the robot before,
    // so maybe put those here

}
