/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import framework.Init;

/**
 *
 * @author JAG
 */
public class DriveSelector extends CommandBase {
    
    private Command defaultDriveMode = Init.manualTankDrive;
    
    public DriveSelector() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivebase);
    }

    //Use this to set which command runs when ever no other command is requiring the drivebase
    public void setDefaultDriveMode(Command command){
        defaultDriveMode = command;
    }
    
    public Command getDefaultDriveMode(){
        return defaultDriveMode;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * if we are here we need to leave to a useful drive mode
     * Do this by starting defaultDriveMode if it's not null and it requires drivebase
     */
    protected void execute() {
        if (defaultDriveMode != null && defaultDriveMode.doesRequire(drivebase)){
            defaultDriveMode.start();
        } else {
            Init.manualTankDrive.start();        //This is the standard drive mode in case defaultDriveMode gets corrupt or unset
            defaultDriveMode = Init.manualTankDrive;
        }
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
    }
}
