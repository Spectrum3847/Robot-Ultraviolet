/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import framework.HW;

/**
 *
 * @author JAG
 */
public class TurnControlDrive extends CommandBase {

    private double timeOut = 3; //Number of second before command exits automatically 

    public TurnControlDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (drivebase.getSetpoint() < 0) {                           //negative is left
            drivebase.setPID(HW.TURN_LEFT_KP, HW.TURN_LEFT_KI, HW.TURN_LEFT_KD);
        } else if (drivebase.getSetpoint() > 0) {                                        //Right is positive
            drivebase.setPID(HW.TURN_RIGHT_KP, HW.TURN_RIGHT_KI, HW.TURN_RIGHT_KD);
        } 
        drivebase.setArcade(0, drivebase.getPIDTurnOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivebase.getController().onTarget() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setPID(HW.SKEW_KP, HW.SKEW_KI, HW.SKEW_KD);       //Reset to drive strait values
        CommandBase.drivebase.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
