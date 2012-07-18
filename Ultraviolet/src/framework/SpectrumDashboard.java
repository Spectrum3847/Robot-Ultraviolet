/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import commands.CommandBase;
import driver.Panel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author David
 */
public class SpectrumDashboard {

    static final boolean ENABLE_SPECTRUM_DASHBOARD = true;
    static final double SHORT_DELAY = .5;
    static double shortOldTime = 0.0;
    static final double LONG_DELAY = 2;
    static double longOldTime = 0.0;

    public static void intializeDashboard() {
        if (ENABLE_SPECTRUM_DASHBOARD) {
            //Put things here that are updated by other commands or only need to be intialized
            SmartDashboard.putBoolean("eBrake", Init.eBrake.isRunning());
            SmartDashboard.putBoolean("Tank", Init.manualTankDrive.isRunning());
            SmartDashboard.putBoolean("Halo", Init.haloDrive.isRunning());
            SmartDashboard.putBoolean("HaloPID", Init.pidHaloDrive.isRunning());
            SmartDashboard.putBoolean("Jacks Down", CommandBase.pneumatics.isJacks());
        
            //Intialize Single calls here, useful for PID data
            SmartDashboard.putData(CommandBase.drivebase);
        }
    }

    public static void updateDashboard() {

        if (ENABLE_SPECTRUM_DASHBOARD) {

            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                //All Dashboard commands that should be updated after the quick SHORT_DELAY
                SmartDashboard.putDouble("Ball Count",CommandBase.loader.getBallCount());
                SmartDashboard.putDouble("RPM DIAL", OI.panel.getRPMAxis());
                SmartDashboard.putDouble("Turn Controller Angle", CommandBase.drivebase.getAngle());
                SmartDashboard.putBoolean("TurnControllerEnable", CommandBase.drivebase.isControllerEnanbled());
                SmartDashboard.putDouble("Turn PID Output", CommandBase.drivebase.getPIDTurnOutput());
                SmartDashboard.putDouble("LeftDrive", CommandBase.drivebase.getJaguar(1).get());
                SmartDashboard.putDouble("RightDrive", CommandBase.drivebase.getJaguar(3).get());
                SmartDashboard.putBoolean("FULL PRESSURE", CommandBase.pneumatics.isMaxPSI());
                SmartDashboard.putBoolean("Dial Sp. Ctrl.",Init.panel_speedControl.isRunning());
                
                SmartDashboard.putBoolean("FirstCollect Running",Init.firstCollect.isRunning());
                SmartDashboard.putBoolean("SecondCollect Running",Init.secondCollect.isRunning());
                SmartDashboard.putBoolean("ThirdCollect Running",Init.thirdCollect.isRunning());
                
                shortOldTime = Timer.getFPGATimestamp();
            }

            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                SmartDashboard.putData(Scheduler.getInstance());
                longOldTime = Timer.getFPGATimestamp();
            }
        }
    }
}
