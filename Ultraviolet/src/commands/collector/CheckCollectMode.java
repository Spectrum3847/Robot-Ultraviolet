/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import framework.Init;

/**
 *
 * @author root
 */
public class CheckCollectMode extends CommandBase {
    
    public CheckCollectMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.loader);
        requires(CommandBase.magazine);
        requires(CommandBase.chamber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        switch(CommandBase.loader.getBallCount()){
            case 0: {
                Init.firstCollect.start();
                break;
            }
            case 1:{
                Init.secondCollect.start();
                break;   
            }
            case 2:{
                Init.thirdCollect.start();
                break;   
            }
            default: break;
             
        
        }
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
        end();
    }
}
