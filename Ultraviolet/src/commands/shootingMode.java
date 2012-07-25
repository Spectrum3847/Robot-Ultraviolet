/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import edu.wpi.first.wpilibj.Timer;
import framework.Init;

/**
 *
 * @author JAG
 */
public class shootingMode extends CommandBase {
    private static final double delay = 0.25;
    private static double oldTime = 0;
    
    public shootingMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Init.connectCam.start();
        oldTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (CommandBase.cam.isConnected()){
            if (Timer.getFPGATimestamp() - oldTime > delay){
                Init.printCam.start();
                oldTime = Timer.getFPGATimestamp();
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Init.cancelCollect.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
