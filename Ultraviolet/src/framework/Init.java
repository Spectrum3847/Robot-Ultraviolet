/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import commands.*;
import commands.collector.CancelCollect;
import commands.collector.CheckCollectMode;
import commands.collector.ManualDown;
import commands.collector.ManualUp;
import commands.collector.commandgroups.*;
import commands.drive.*;
import commands.shooter.CheckFireMode;
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
    public static final Command addBall = new AddBall();
    public static final Command subtractBall = new SubtractBall();
    
    //Shooter Commands
    public static final Command panel_speedControl = new PanelControl();
    public static final Command shootBall = new CheckFireMode();
    public static final Command shootThird = new ShootThird();
    public static final Command shootSecond = new ShootSecond();
    public static final Command shootFirst = new ShootFirst();
    public static final Command connectCam = new ConnectToCamera();
    public static final Command disconnectCam = new DisconnectCamera();
    public static final Command printCam = new PrintCamData();
    
    
    //Auton Commands
    public static final Command auton_fire = new AutonCollect();


    

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
