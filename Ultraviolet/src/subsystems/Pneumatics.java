/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import commands.CommandBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.HW;

/**
 *
 * @author David
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Compressor compressor;
    
    private Relay relay;
    
    public static Solenoid jacks;
    
    public Pneumatics(){
        compressor = new Compressor(HW.PRESSURE_CHANNEl,HW.COMPRESSOR_RELAY);
        jacks = new Solenoid(1);
    }
    
    public boolean isMaxPSI(){
        return compressor.getPressureSwitchValue();
    }
    
    public void runCompressor(){
        compressor.start();
    }
    
    public void stopCompressor(){
        compressor.stop();
    }

    protected void initDefaultCommand() {
    }
    
    public void extendJacks(){
        jacks.set(true);
        
        SmartDashboard.putBoolean("Jacks Down", true); //Jacks are extended
    }
    
    public void retractJacks(){
        jacks.set(false);
        SmartDashboard.putBoolean("Jacks Down", false); //Jacks are retracted
    }
    /**
     * 
     * @return state of the jacks solenoide
     */
    public boolean isJacks(){
        return jacks.get();
    }
    
}
