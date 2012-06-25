/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.drive;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;

/**
 *
 * @author JAG
 */
public class EBrake extends CommandGroup {
    
    public EBrake(){
        super();
        requires(CommandBase.drivebase);
        addParallel(Init.leftBrake);
        addParallel(Init.rightBrake);
    }
    
    /*
     * Enable eBrakes and tell smartdashboard we are enabled
     */
    protected void initialize(){
        SmartDashboard.putBoolean("eBrake", true);
    }
    
    /**
     * End of Ebrake, tell smartDashboard we are disabled
     */
    protected void end(){
        Init.leftBrake.getController().reset();
        Init.rightBrake.getController().reset();
        SmartDashboard.putBoolean("eBrake", false);
    }
    
    /**
     * Just calls end
     */
    protected void interrupted(){
        end();
    }
}
