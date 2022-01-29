package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup; //SpeedController has been depricated, a new change for 2022. I'll use the new method.
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID;

public class DriveTrain {
     final private CANSparkMax lbMotor, lmMotor, lfMotor; //Left Back, Left Middle, Left Front.
     final private CANSparkMax rbMotor, rmMotor, rfMotor; //Right Back, Right Middle, Right Front
     final private MotorControllerGroup leftMotors, rightMotors;
     final private DifferentialDrive dDrive = new DifferentialDrive(leftMotors, rightMotors);

     //TODO make a constants class, i'm not willing to do that now tho.


    public DriveTrain(){
        //left motors + motorcontrol group.
        lbMotor = new CANSparkMax(3,MotorType.kBrushless);
        lmMotor = new CANSparkMax(4,MotorType.kBrushless); 
        lfMotor = new CANSparkMax(5,MotorType.kBrushless);
            leftMotors  = new MotorControllerGroup(lbMotor,lmMotor,lfMotor); 
        //right motors + motor countrol group
        rbMotor = new CANSparkMax(6,MotorType.kBrushless);
        rmMotor = new CANSparkMax(9,MotorType.kBrushless);
        rfMotor = new CANSparkMax(10,MotorType.kBrushless);
            rightMotors = new MotorControllerGroup(rbMotor,rmMotor,rfMotor);
        //passing arguments to the control groups.
        CANSparkMax[] motors = {lbMotor,lmMotor,lfMotor,rbMotor,rmMotor,rfMotor};
            for (CANSparkMax index : motors) {
                index.setSmartCurrentLimit(45);
                index.setSecondaryCurrentLimit(45);
                index.setIdleMode(IdleMode.kBrake);
            }
        
    }

    public void run(){ 
        double rAxis = Robot.driverController.getY(Hand.kRight);
        double lAxis = Robot.driverController.getY(Hand.kLeft);
        dDrive.tankDrive(lSpeed, rSpeed);
        
    }
    public void swerveDrive(){
        //TODO Liam will write swerveDrive when he has the time
    }

    public void closeMotors(){ //vscode tells me there is a resource leak, so this should fix it?
        leftMotors.close();
        rightMotors.close();
    }
}
