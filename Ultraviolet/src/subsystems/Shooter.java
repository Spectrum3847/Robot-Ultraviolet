
package subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author David
 * Subsystem that controls the shooter, consisting of two motors.
 * Contains the Optical Encoder, Bang-Bang Speed Controller, and raw
 * PWM control of the two Jaguars.
 */
public class Shooter extends Subsystem {

    //This is the lowest dial setting at which bang bang will operate at.
    private final double bang_threshold = 1200;
    
    private Jaguar front_motor;
    private Jaguar back_motor;
    
    //optical encoder
    private driver.Encoder shooter_encoder;
   
    // Initialize your subsystem here
    public Shooter() {
        super("Shooter");
        
        //assign optical encoder to Digital IO port 14
        shooter_encoder = new driver.Encoder(HW.SHOOTER_ENCODER);
        
        front_motor = new Jaguar(HW.FRONT_SHOOTER_MOTOR);
        back_motor =  new Jaguar(HW.REAR_SHOOTER_MOTOR);
        
        //tells the encoder to start recording ticks
        shooter_encoder.start();
    }
    
    
    public void initDefaultCommand() {
    }
    
    //sets shooter motors to PWM value (-1.0->1.0)
    protected void setShooter(double speed){
        front_motor.set(speed);
        back_motor.set(speed); 
    }
    
    //gets the output rate of the encoder, in RPM.
    public double getRate(){
        return shooter_encoder.getRate();
    }
    
    /**
     * runs the Bang-Bang speed controller.
     * When the rate is bigger than the setpoint,
     * or when the rate is less than the threshold,
     * then do not operate the motors (corrects for overshoot).
     * Otherwise, when the setpoint is not reached, set motors to
     * max output.
     */
    public void setRPM(double setpoint){
        if(getRate() > setpoint || setpoint < bang_threshold)
            setShooter(0.0);
        else setShooter(-1.0);
            
    }
    
    public void resetEncoder(){
        shooter_encoder.reset();
    }
}
