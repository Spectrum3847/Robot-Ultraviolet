/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author root
 */
public class ClearChamber extends CommandBase {
    
    double last_time;
    
    public ClearChamber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        last_time = Timer.getFPGATimestamp();
        chamber.setSpeed(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - last_time >= 0.3;
    }

    // Called once after isFinished returns true
    protected void end() {
        chamber.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
