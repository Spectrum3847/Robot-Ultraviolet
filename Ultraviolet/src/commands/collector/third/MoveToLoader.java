/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.third;

import commands.CommandBase;

/**
 *
 * @author root
 */
public class MoveToLoader extends CommandBase {
    
    
    public MoveToLoader() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.loader.setSpeed(-1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return CommandBase.loader.isOccupied();
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.loader.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
