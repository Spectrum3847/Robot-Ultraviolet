/*
 * Class to control drive base with joysticks
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;
import framework.OI;


/**
 *
 * @author David
 */
public class ManualTankDrive extends CommandBase {
    
    
    
    public ManualTankDrive()
    {
     //   setInterruptible(false);
        requires(CommandBase.drivebase);
    }
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
     Init.driveSelector.setDefaultDriveMode(this);      //Always return to this drive mode after we exit for brakeing or something like that
     drivebase.disableTurnController();
     SmartDashboard.putBoolean("Tank", true);           //Tell Smart Dashboard we are in tank mode
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivebase.setLeft(OI.driveStick_left.getY());
        drivebase.setRight(OI.driveStick_right.getY());
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setLeft(0);
        drivebase.setRight(0);
        SmartDashboard.putBoolean("Tank", false);           //Tell Smart Dashboard we not in tank mode
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
