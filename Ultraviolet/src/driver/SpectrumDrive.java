/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author JAG
 */
public class SpectrumDrive extends RobotDrive{
    
    public static final double DEADBAND_VALUE = .13;
    
    public SpectrumDrive(Jaguar jag1, Jaguar jag2, Jaguar jag3, Jaguar jag4){
        super(jag1, jag2, jag3, jag4);
    }
    
        /**
     * Provide tank steering using the stored robot configuration.
     * This function lets you directly provide joystick values from any source.
     * @param leftValue The value of the left stick.
     * @param rightValue The value of the right stick.
     */
    public void tankDrive(double leftValue, double rightValue) {
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);

        leftValue = deadBand(leftValue, DEADBAND_VALUE);
        rightValue = deadBand (rightValue, DEADBAND_VALUE);
        setLeftRightMotorOutputs(leftValue, rightValue);
    }
    

    /**
     * Arcade drive implements single stick driving.
     * This function lets you directly provide joystick values from any source.
     * @param moveValue The value to use for forwards/backwards
     * @param rotateValue The value to use for the rotate right/left
     * @param squaredInputs If set, increases the sensitivity at low speeds
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);

        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        leftMotorSpeed = deadBand(leftMotorSpeed, DEADBAND_VALUE);
        rightMotorSpeed = deadBand (rightMotorSpeed, DEADBAND_VALUE);
        setLeftRightMotorOutputs(leftMotorSpeed, rightMotorSpeed);
    }
    
    private double deadBand(double input, double dead){
        double output = 0;
        if (input < 0 && input > -1 * dead){    //Check if were in negative deadband
        } else if (input > 0 && input < dead){  //Check if were in positive deadband
        } else {
            output = input;
        }
        return output;
    }

    
}
