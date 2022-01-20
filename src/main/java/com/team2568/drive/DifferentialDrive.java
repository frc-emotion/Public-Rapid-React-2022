package com.team2568.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DifferentialDrive implements DriveTrain {

    public enum MotorType {

    }

    public DifferentialDrive(int[] leftMotors, int[] rightMotors) {
        for (int id : leftMotors) {

        }
    }

    public MotorController getLeftMasterMotor() {
        return new WPI_TalonFX(0);
    }

}
