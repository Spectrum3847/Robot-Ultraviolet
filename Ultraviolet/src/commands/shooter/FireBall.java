/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shooter;

import commands.CommandBase;

/**
 *
 * @author root
 */
public class FireBall extends CommandBase {
    
    public FireBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.chamber.setSpeed(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !CommandBase.chamber.isOccupied();
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.chamber.setSpeed(0);
        CommandBase.loader.subtractBall();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
