/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import commands.CommandBase;
import commands.DeployJacks;
import commands.RunCompressor;
import commands.StopGyroDrift;
import commands.collector.CancelCollect;
import commands.collector.CheckCollectMode;
import commands.collector.ManualDown;
import commands.collector.ManualUp;
import commands.collector.commandgroups.FirstCollect;
import commands.collector.commandgroups.SecondCollect;
import commands.collector.commandgroups.ShootBall;
import commands.collector.commandgroups.ThirdCollect;
import commands.drive.*;
import commands.shooter.PanelControl;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author David
 */
public class Init {
    
    //public static final Command refresh = new RefreshData();
    
    //Drive Commands
    public static final DriveSelector driveSelector = new DriveSelector();
    public static final ManualHaloDrive haloDrive = new ManualHaloDrive();
    public static final PIDHaloDrive pidHaloDrive = new PIDHaloDrive();
    public static final ManualTankDrive manualTankDrive = new ManualTankDrive();
    public static final LeftElectronicBrake leftBrake = new LeftElectronicBrake(HW.EBRAKE_KP,HW.EBRAKE_KI,HW.DRIVEBASE_KD);
    public static final RightElectronicBrake rightBrake = new RightElectronicBrake(HW.EBRAKE_KP,HW.EBRAKE_KI,HW.DRIVEBASE_KD);
    public static final EBrake eBrake = new EBrake();
    public static final QuickTurn quickLeft90 = new QuickTurn(-90);
    public static final QuickTurn quickRight90 = new QuickTurn (90);
    public static final StopGyroDrift stopGyroDrift = new StopGyroDrift();
    
    //Collect Commands
    public static final Command manualCollect = new ManualUp();
    public static final Command manualEject = new ManualDown();
    public static final Command firstCollect = new FirstCollect();
    public static final Command secondCollect = new SecondCollect();
    public static final Command thirdCollect = new ThirdCollect();
    public static final Command checkCollect = new CheckCollectMode();
    public static final Command cancelCollect = new CancelCollect();
    
    //Shooter Commands
    public static final Command panel_speedControl = new PanelControl();
    public static final Command shootBall = new ShootBall();


    

    //Jacks Commands
    public static final Command runCompressor = new RunCompressor();
    public static final Command deployJacks = new DeployJacks();
    
    public static void robotInit(){
        CommandBase.init();
        
        SpectrumDashboard.intializeDashboard();
        System.out.println("RobotInit is Finished: Good to Go!");
    }
    
    public static void cancelAllCommands(){
        eBrake.cancel();
    }    


    
}
