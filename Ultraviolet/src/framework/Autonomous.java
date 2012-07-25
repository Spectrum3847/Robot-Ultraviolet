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
public class Autonomous {
    
    public static void init(){
        CommandBase.pneumatics.runCompressor();
        Init.auton_fire.start();
    }
    
    public static void periodic(){
        SpectrumDashboard.updateDashboard();
        Scheduler.getInstance().run();
    }
    
}
