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
public class PIDHaloDrive extends CommandBase {

    public PIDHaloDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivebase);
        //drivebase.setPID(HW.SKEW_KP, HW.SKEW_KI, HW.SKEW_KD);       //Set PID values for driving straight
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivebase.reset();
        Init.driveSelector.setDefaultDriveMode(Init.pidHaloDrive); //Always return to this drive mode after we exit for brakeing or something like that
        //System.out.println("PIDHalo Started");
        
        SmartDashboard.putBoolean("HaloPID", true);     //Tell Smart Dashboard that we are in PID Halo Mode
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double straightStick = OI.driveStick_left.getY();
        double turnStick = OI.driveStick_right.getX();
        if (Math.abs(turnStick) > 0.05) {           //If we are turning using the joystick and turn PID off
            drivebase.disableTurnController();  //Disable if enabled we're turning now by hand
            drivebase.setArcade(straightStick, turnStick);
        } else {
            drivebase.enableTurnController();   //Enable if we weren't already
            drivebase.setArcade(straightStick, drivebase.getPIDTurnOutput()); //turn is controlled by the PID controller to keep the robot straight
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivebase.setLeft(0);
        drivebase.setRight(0);
        SmartDashboard.putBoolean("HaloPID", false);     //Tell Smart Dashboard that we are not in PID Halo Mode
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
