/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;

/**
 *
 * @author JAG
 */
public class RunCompressor extends CommandBase {
    
    public RunCompressor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     * Start Compressor once it's started
     */
    protected void initialize() {
        CommandBase.pneumatics.runCompressor();
        SmartDashboard.putBoolean("Compressor Running",Init.runCompressor.isRunning());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.pneumatics.stopCompressor();
        SmartDashboard.putBoolean("Compressor Running",Init.runCompressor.isRunning());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
