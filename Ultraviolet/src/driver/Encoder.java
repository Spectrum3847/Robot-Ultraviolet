
package driver;

import edu.wpi.first.wpilibj.Counter;

/**
 *
 * @author David
 * 
 * This class implements the Optical Encoder,
 * which is defined as a Counter. It counts the number of
 * ticks, or color change in the case of our wheel, which
 * is used to get rate.
 */
public class Encoder extends Counter {
    
    public Encoder(int channel){
        super(channel);
    }
    
    /**
     * 
     * gets the ticks of the optical encoder.
     * A tick is defined as a change in false to true recording
     * of the encoder.
     */
    public int getTicks(){
        return get();
    }
    
    /**
     * Each revolution records exactly (1) tick.
     * When divided by the period between ticks, or time per revolution, of the encoder,
     * an output of ticks per second is given. Multiply by 60,
     * and you get ticks per minute. Since (1) tick is equivalent to
     * (1) revolution, the output corresponds to RPM.
     */
    public double getRate(){
        return 1.0 / getPeriod() * 60.0;
    }
    
}
