
/*
 * Class for all IR Sensors to extend
 */
package driver;

/**
 * @author hunter
 */
import edu.wpi.first.wpilibj.AnalogChannel;


public class IRSensor extends AnalogChannel {
    
    
    public IRSensor(int channel){
       super(channel);
    }

    public double getRawVoltage(){
        return getAverageVoltage();
    }
    public double getDistance(){
        
        double voltage = getRawVoltage();
        double distance = 0;
        
        /**
         * THIS EQUATION WAS APPROXIMATED BASED ON A SERIES OF ROUGH SAMPLED DATA.
         * THE IR SENSOR OPERATES ON A LOGARITHMIC SCALE, THEREFORE
         * 
         * IT IS ACCURATE TO THE TENTH OF A CENTIMETER WITHIN THE RANGE OF 4-20 CM
         * 20+ CM INCREASES AT AN EXPONENTIAL RATE, AND IS OUT OF THE RANGE OF 
         * SENSED BALLS
         */
        distance = 0.61613 * (Math.sqrt(54.8962-voltage)+7.40636);
        distance  = distance / (voltage-0.042091);
        
        if(Math.abs(distance) > 50)
            return 3847;

        return distance;
    }
}
