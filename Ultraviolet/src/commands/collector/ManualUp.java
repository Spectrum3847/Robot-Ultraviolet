/*
 * Class to control collector with game controller
 */

package commands.collector;

import commands.CommandBase;
import driver.Gamepad;
import framework.OI;

/**
 *
 * @author David
 */
public class ManualUp extends CommandBase {
    
    public ManualUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
            CommandBase.loader.setSpeed(-1);
            CommandBase.magazine.setSpeed(1);
            CommandBase.chamber.setSpeed(1);


    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        loader.setSpeed(0);
        magazine.setSpeed(0);
        chamber.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
