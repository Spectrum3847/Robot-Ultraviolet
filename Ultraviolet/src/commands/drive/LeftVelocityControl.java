/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 * @author JAG
 */
public class LeftVelocityControl extends PIDCommand {

    public LeftVelocityControl(double Kp, double Ki, double Kd) {     
        super(Kp, Ki, Kd);
        requires(CommandBase.drivebase);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    }
    
    public double returnPIDInput(){
        return CommandBase.drivebase.getLeftVelocity();
    }
    
    public void usePIDOutput(double output){
        //if (!this.getController().onTarget()){
            CommandBase.drivebase.setLeft(CommandBase.drivebase.getLeft() + output);   
        //} else{
        //    CommandBase.drivebase.setLeft(CommandBase.drivebase.getLeft());
        //}
    }
    
    public double getVelocity(){
        return CommandBase.drivebase.getLeftVelocity();
    }
    /**
     * Reset the Encoder
     */
    public void resetEncoder(){
        CommandBase.drivebase.getLeftEncoder().reset(); //Reset the encoder to 0
    }
    
    /**
     * Start the Encoder
     */
    public void startEncoder(){
        CommandBase.drivebase.getLeftEncoder().start();
    }
    public PIDController getController(){
        return this.getPIDController();
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        startEncoder();
        this.getController().setTolerance(10);
        this.getController().setInputRange(-150, 150);
        this.getController().setOutputRange(-1, 1);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
