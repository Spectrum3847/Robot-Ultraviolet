/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import framework.Init;

/**
 *
 * @author JAG
 */
public class MoveEBrake extends CommandBase {
    
    double increment = 0;
    public MoveEBrake(double inc) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        increment = inc;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
         if (Init.eBrake.isRunning()){
            Init.leftBrake.incrementSetpoint(increment);
            //Init.rightBrake.incrementSetpoint(increment);
        } else{
             System.out.println("eBrake not enabled, can't increment");
        }
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}