/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author root
 */
public class PrintCamData extends CommandBase {
    
    
    
    public PrintCamData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        double distance = cam.getDistance();
        double offset = cam.getOffset();
        System.out.println("Distance: " + distance + " : Offset: " + offset);
        SmartDashboard.putDouble("Distance", distance);
        SmartDashboard.putDouble("Offset", offset);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
