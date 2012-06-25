/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 * @author David
 */
public class RightElectronicBrake extends PIDCommand {
    
    public RightElectronicBrake(double Kp, double Ki,double Kd) {
        super(Kp,Ki,Kd);
        this.getPIDController().setSetpoint(0.0);
    }
    
    public double returnPIDInput(){
        return CommandBase.drivebase.getRightEncoder().getDistance();
    }
    
    public void usePIDOutput(double output){
        CommandBase.drivebase.setRight(output);
    }
    
    public void incrementSetpoint(double increment){
        getPIDController().setSetpoint(increment + getPIDController().getSetpoint());
    }
    
    public PIDController getController(){
        return this.getPIDController();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.drivebase.getRightEncoder().reset();
        CommandBase.drivebase.getRightEncoder().reset();
        this.getPIDController().reset();
        this.getPIDController().enable();
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
        this.getPIDController().reset();                    //Disable and Reset PIDController
        CommandBase.drivebase.getRightEncoder().reset();
        CommandBase.drivebase.getRightEncoder().stop();
        CommandBase.drivebase.setRight(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
