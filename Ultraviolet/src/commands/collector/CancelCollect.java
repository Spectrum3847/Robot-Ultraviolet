/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;

/**
 *
 * @author root
 */
public class CancelCollect extends CommandBase {
    
    public CancelCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.loader);
        requires(CommandBase.magazine);
        requires(CommandBase.chamber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.loader.subtractBall();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
