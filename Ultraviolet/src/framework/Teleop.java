
/*
 * Teleop class that is called from RobotTemplate
 */
package framework;

import commands.CommandBase;
import driver.CamData;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Teleop {
    
    //static Relay relay = new Relay(2);

    
    public static void init(){
        CommandBase.magazine.setSpeed(0.0);
        CommandBase.loader.setSpeed(0.0);
        Init.manualTankDrive.start();      
        Init.runCompressor.start();
        Init.stopGyroDrift.start();
        
    }
    
    
    public static void periodic(){
        
        SpectrumDashboard.updateDashboard();
        
        if(CommandBase.loader.getBallCount() < 0)
            CommandBase.loader.clearBalls();
        if(CommandBase.loader.getBallCount() == 4)
            CommandBase.loader.subtractBall();
        
        //System.out.println(CamData.receiveRaw());
        
        
        Scheduler.getInstance().run();
        
        
    }
    
    
    
    
    
    
    
    
    
}
