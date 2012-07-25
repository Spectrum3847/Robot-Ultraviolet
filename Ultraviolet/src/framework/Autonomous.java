/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import commands.CommandBase;
import commands.collector.commandgroups.AutonFire;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author David
 */
public class Autonomous {
    
    public static void init(){
        CommandBase.loader.clearBalls();
        CommandBase.loader.addBall();
        CommandBase.loader.addBall();
        (new AutonFire()).start();
    }
    
    public static void periodic(){
        SpectrumDashboard.updateDashboard();
        Scheduler.getInstance().run();
    }
    
}
