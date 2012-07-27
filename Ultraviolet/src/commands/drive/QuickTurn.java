/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import framework.Init;

/**
 * uses to quickly turn a certain amount
 * Use negative for left and positive for right
 * Has a 3 sec timeout so make sure you have it finish before than
 * @author JAG
 */
public class QuickTurn extends CommandBase {
    
    private double degrees = 0;
    
    public QuickTurn(double deg) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        degrees = deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.enableTurnController();   
        double setpoint = drivebase.getSetpoint();
        drivebase.getController().setSetpoint(setpoint + degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!Init.turnControlDrive.isRunning()){
            Init.turnControlDrive.start();
        }
        //Add time to the turn controller to allow it to hit's it mark.
        Init.turnControlDrive.addTime(.5);
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
        end();
    }
}
