/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;
import framework.OI;

/**
 *
 * @author JAG
 */
public class ManualHaloDrive extends CommandBase {
    
    public ManualHaloDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Init.driveSelector.setDefaultDriveMode(this); //Always return to this drive mode after we exit for brakeing or something like that
        drivebase.disableTurnController();
        
        SmartDashboard.putBoolean("Halo", true); //Tell SmartDashboard we are in Halo Mode
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setArcade(OI.driveStick_left.getY(), OI.driveStick_right.getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setLeft(0);
        drivebase.setRight(0);
        SmartDashboard.putBoolean("Halo", false); //Tell SmartDashboard we are out of Halo Mode
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
