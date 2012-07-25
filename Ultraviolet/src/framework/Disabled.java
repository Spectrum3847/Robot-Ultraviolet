/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author David
 */
public class Disabled {
    
    public static void init(){
        Init.cancelAllCommands();
        Init.stopGyroDrift.start();
        CommandBase.pneumatics.stopCompressor();        
        CommandBase.drivebase.reset(); 
    }
    

    
    public static void periodic(){
        Scheduler.getInstance().run();
        SpectrumDashboard.updateDashboard();
    }
}
