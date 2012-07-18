/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import subsystems.Magazine;

/**
 *
 * @author root
 */
public class MoveToMagazine extends CommandBase {
    
    public MoveToMagazine() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.loader.setSpeed(-1);
        CommandBase.magazine.setSpeed(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!CommandBase.loader.isOccupied())
            CommandBase.loader.setSpeed(0);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return CommandBase.magazine.isOccupied(Magazine.TOP_SENSOR);
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.magazine.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
