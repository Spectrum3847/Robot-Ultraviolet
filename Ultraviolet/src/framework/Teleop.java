/*
 * Teleop class that is called from RobotTemplate
 */
package framework;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Teleop {
    
    //static Relay relay = new Relay(2);

    
    public static void init(){
        CommandBase.elevator.setSpeed(0.0);
        CommandBase.collector.setSpeed(0.0);
        Init.manualTankDrive.start();      
        Init.runCompressor.start();
        Init.stopGyroDrift.start();
        
    }
    
    
    public static void periodic(){
        SpectrumDashboard.updateDashboard();
        Scheduler.getInstance().run();
    }
    
    
    
    
    
    
    
    
    
}
