package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup; //SpeedController has been depricated, a new change for 2022. I'll use the new method.
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain {
    private CANSparkMax lbMotor, lmMotor, lfMotor, rbMotor, rmMotor, rfMotor; // Left Back, Left Middle, Left Front.
    private MotorControllerGroup leftMotors, rightMotors;
    private DifferentialDrive dDrive;

    public DriveTrain() {

        // left motors
        lbMotor = new CANSparkMax(Constants.LEFT_MOTOR_PORTS[0], MotorType.kBrushless);
        lmMotor = new CANSparkMax(Constants.LEFT_MOTOR_PORTS[1], MotorType.kBrushless);
        lfMotor = new CANSparkMax(Constants.LEFT_MOTOR_PORTS[2], MotorType.kBrushless);
        // right motors
        rbMotor = new CANSparkMax(Constants.RIGHT_MOTOR_PORTS[0], MotorType.kBrushless);
        rmMotor = new CANSparkMax(Constants.RIGHT_MOTOR_PORTS[1], MotorType.kBrushless);
        rfMotor = new CANSparkMax(Constants.RIGHT_MOTOR_PORTS[2], MotorType.kBrushless);

        // passing arguments to the control groups.
        CANSparkMax[] motors = { lbMotor, lmMotor, lfMotor, rbMotor, rmMotor, rfMotor };
        for (CANSparkMax index : motors) {
            index.setSmartCurrentLimit(Constants.CAN_SPARK_MAX_CURRENT);
            index.setSecondaryCurrentLimit(Constants.CAN_SPARK_MAX_CURRENT);
            index.setIdleMode(IdleMode.kBrake);
        }
        leftMotors = new MotorControllerGroup(lbMotor, lmMotor, lfMotor);
        rightMotors = new MotorControllerGroup(rbMotor, rmMotor, rfMotor);
        dDrive = new DifferentialDrive(leftMotors, rightMotors);

    }

    public void run() {

        double rAxis = Robot.driverController.getRightY(); // Hand is depricated, it's all within generic HID now
        double lAxis = Robot.driverController.getLeftY();
        // Above is just raw input
        // below is the input -> an Acceleration Curve
        if (rAxis < 0) {
            double rSpeed = -1 * Constants.DRIVETRAIN_NORMAL_SPEED
                    * (Math.pow(lAxis, Constants.DRIVETRAIN_NORMAL_CURVE));
            double lSpeed = -1 * Constants.DRIVETRAIN_NORMAL_SPEED
                    * (Math.pow(rAxis, Constants.DRIVETRAIN_NORMAL_CURVE));
            dDrive.tankDrive(lSpeed, rSpeed);
        } else {
            double rSpeed = Constants.DRIVETRAIN_NORMAL_SPEED * (Math.pow(lAxis, Constants.DRIVETRAIN_NORMAL_CURVE));
            double lSpeed = Constants.DRIVETRAIN_NORMAL_SPEED * (Math.pow(rAxis, Constants.DRIVETRAIN_NORMAL_CURVE));
            dDrive.tankDrive(lSpeed, rSpeed);
        }
    }

    public void close() {
        dDrive.close();
        leftMotors.close();
        rightMotors.close();

    }
}
