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
public class LeftElectronicBrake extends PIDCommand {
    
    public LeftElectronicBrake(double Kp, double Ki, double Kd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(Kp,Ki,Kd);
        this.getPIDController().setSetpoint(0.0);
        
        //this.getPIDController().setOutputRange(-0.3, 0.3);
        
    }
    
    public double returnPIDInput(){
        return CommandBase.drivebase.getLeftEncoder().getDistance();
    }
    
    public void usePIDOutput(double output){
        CommandBase.drivebase.setLeft(-1*output);
        
    }
    
    /**
     * Increment the Setpoint of the eBrake
     * Allows you to move forward and backwards while brake is enabled
     * @param increment The amount you want to move the setpoint in inches (+ fwd, - rev)
     */
    public void incrementSetpoint(double increment){
        getPIDController().setSetpoint(increment + getPIDController().getSetpoint());
    }
    
    public PIDController getController(){
        return this.getPIDController();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.drivebase.getLeftEncoder().reset();
        CommandBase.drivebase.getLeftEncoder().start();
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
        this.getPIDController().reset();                //Disable and Reset PIDController
        CommandBase.drivebase.getLeftEncoder().reset();
        CommandBase.drivebase.getLeftEncoder().stop();
        CommandBase.drivebase.setLeft(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
