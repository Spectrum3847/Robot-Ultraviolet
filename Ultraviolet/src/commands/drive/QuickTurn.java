/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import framework.HW;

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
        requires(drivebase);
        degrees = deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //drivebase.setPID(HW.TURN_KP, HW.TURN_KI, HW.TURN_KD);       //Change
        drivebase.enableTurnController();   
        double setpoint = drivebase.getSetpoint();
        drivebase.getController().setSetpoint(setpoint + degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        new TurnControlDrive().start();
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
        end();
    }
}
