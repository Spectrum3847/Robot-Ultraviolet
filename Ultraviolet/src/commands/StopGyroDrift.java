/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author JAG
 */
public class StopGyroDrift extends CommandBase {
    
    public StopGyroDrift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Attempt to remove some gyro dirft by restarting when ever we are at a really small angle
        Gyro gyro = CommandBase.drivebase.getGyro();
        if (Math.abs(gyro.getAngle()) < 0.1) {
            gyro.reset();
        }  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
