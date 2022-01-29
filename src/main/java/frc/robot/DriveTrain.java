package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup; //SpeedController has been depricated, a new change for 2022. I'll use the new method.
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain {
     final private CANSparkMax lbMotor, lmMotor, lfMotor; //Left Back, Left Middle, Left Front.
     final private CANSparkMax rbMotor, rmMotor, rfMotor; //Right Back, Right Middle, Right Front
     final private MotorControllerGroup leftMotors, rightMotors;
     private double power = 0.7;
     private double exponent = 1.8;   //making these instance variables incase you ever want to change the accelCurve
     private DifferentialDrive dDrive;

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
        dDrive = new DifferentialDrive(leftMotors, rightMotors);
            double rAxis = Robot.driverController.getRightY(); //Hand is depricated, it's all within generic HID now
            double lAxis = Robot.driverController.getLeftY();  
        //Above is just raw input
        //below is the input -> an Acceleration Curve
        if(rAxis < 0){
            double rSpeed = -1 * power*(Math.pow(lAxis,exponent));
            double lSpeed = -1 * power *(Math.pow(rAxis,exponent));
            dDrive.tankDrive(lSpeed, rSpeed);
        }else{
             double rSpeed = power*(Math.pow(lAxis,exponent));
             double lSpeed = power *(Math.pow(rAxis,exponent));           
            dDrive.tankDrive(lSpeed, rSpeed);
        }
    }
    public void swerveDrive(){
        //TODO Liam will write swerveDrive when he has the time
    }

    public void close(){ //vscode tells me there is a resource leak, so this should fix it?
        dDrive.close();
        leftMotors.close();
        rightMotors.close();
        
    }
}
