/*
 * Class to control ball lift in sync with collector with game controller
 */
package commands.collector;

import commands.CommandBase;

/**
 *
 * @author David
 */
public class ManualDown extends CommandBase {
    
    public ManualDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(loader);
        requires(magazine);
        requires(chamber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.loader.clearBalls();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.loader.setSpeed(1);
        CommandBase.magazine.setSpeed(-1);
        CommandBase.chamber.setSpeed(-1);

        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        magazine.setSpeed(0);
        loader.setSpeed(0);
        chamber.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
