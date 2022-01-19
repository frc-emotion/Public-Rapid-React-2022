package com.team2568.lib;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * Collection of static factory functions to streamline creation and
 * configuration of motor controller objects
 * 
 * @author Ryan Chaiyakul
 */
public class MotorFactory {

    /**
     * Returns a WPI_TalonFX object configured with defaults (current limits etc.)
     * 
     * @param id the CAN ID of the motor
     * @return WPI_TalonFX object assoicated with the CAN ID
     */
    public static WPI_TalonFX getTalonFX(int id) {
        WPI_TalonFX ret = new WPI_TalonFX(id);
        ret.configFactoryDefault();
        return ret;
    }

    /**
     * Returns a WPI_TalonFX object configured with defaults (current limits etc.)
     * 
     * @param id     the CAN ID of the motor
     * @param invert true is inverted; false is not inverted
     * @return WPI_TalonFX object assoicated with the CAN ID
     */
    public static WPI_TalonFX getTalonFX(int id, boolean invert) {
        WPI_TalonFX ret = getTalonFX(id);
        ret.setInverted(invert);
        return ret;
    }

    /**
     * Returns a WPI_TalonFX object configured with defaults (current limits etc.)
     * that will mimic the passed motor controller
     * 
     * @param master the WPI_TalonFX object that the motor will follow
     * @param id     the CAN ID of the motor
     * @return WPI_TalonFX object assoicated with the CAN ID
     */
    public static WPI_TalonFX getTalonFXFollower(IMotorController master, int id) {
        WPI_TalonFX ret = getTalonFX(id);
        ret.follow(master);
        ret.setInverted(InvertType.FollowMaster); // defaults to follow
        return ret;
    }

    /**
     * Returns a WPI_TalonFX object configured with defaults (current limits etc.)
     * that will mimic the passed motor controller
     * 
     * @param master the WPI_TalonFX object that the motor will follow
     * @param id     the CAN ID of the motor
     * @param invert true is inverted; false is not inverted (relative to the master
     *               motor)
     * @return WPI_TalonFX object assoicated with the CAN ID
     */
    public static WPI_TalonFX getTalonFXFollower(IMotorController master, int id, boolean invert) {
        WPI_TalonFX ret = getTalonFXFollower(master, id);

        if (invert) {
            ret.setInverted(InvertType.OpposeMaster); // inversion is relative
        } else {
            ret.setInverted(InvertType.FollowMaster);
        }

        return ret;
    }

}
