/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import framework.OI;

/**
 *
 * @author JAG
 */
public class DeployJacks extends CommandBase {
    
    public DeployJacks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(pneumatics);
    }

    // Called just before this Command runs the first time
    /**
     *  extend the jacks but only has to happen once
     */
    protected void initialize() {
        pneumatics.extendJacks();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Do nothing just wait for cancel to retract
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    /**
     * Retract the Jacks at the end of the command
     */
    protected void end() {
        pneumatics.retractJacks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
