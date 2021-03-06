/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

/**
 *
 * @author root
 */
public class DisconnectCamera extends CommandBase {
    
    public DisconnectCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cam.disconnect();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        //System.out.println("Disconnected From Camera");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
