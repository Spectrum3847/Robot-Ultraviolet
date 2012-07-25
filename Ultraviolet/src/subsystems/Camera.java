/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import driver.AutoAim;
import driver.CamData;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author root
 */
public class Camera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CamData data;
    private static final boolean DEBUG = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void connect(){
        data = new CamData("10.38.47.133","8882");
        SmartDashboard.putBoolean("Camera Running",true);
    }
    
    public void disconnect(){
        try{
        CamData.disconnect();
        }catch(NullPointerException e){
            System.out.println("No instance running");
        }
        SmartDashboard.putBoolean("Camera Running",false);
    }
    
    public String getRaw(){
        return CamData.receiveRaw();
    }
    
    public int[] getData(){
        return CamData.parseCamData(CamData.receiveRaw());
    }
    
    public String toString(){
        String str = "";
        int[] data = getData();
        
        for(int i = 0;i < data.length;i++){
            str+=data[i] + "|";
        }
        
       return str;
    }
    
    public double getDistance(){
        if (isConnected()){
            return AutoAim.getDistance(getData());
        } else {
            return 9999;
        }
    }
    
    public double getOffset(){
        if (isConnected()){
            return AutoAim.getOffsetAngle(getData());
        } else {
            return 9999;
        }
    }
   
    public boolean isConnected(){
        return CamData.isConnected();
    }
}
