/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author David
 */
public class Potentiometer extends AnalogChannel {
    

    
    public Potentiometer(int channel){
        super(channel);
    }
    
    public Potentiometer(int moduleNumber, int channel){
        super(moduleNumber,channel);
    }
    
    //PLACEHOLDER: TO BE IMPLEMENTED LATER
    public double getAngle(){
        //return getAverageVoltage() / 0.013746 - 0.08;
        return 45.0 + ((getAverageVoltage() - 2.762)*72.87);
    }
    
}
