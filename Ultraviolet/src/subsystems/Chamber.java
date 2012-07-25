/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author root
 */
public class Chamber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static Jaguar chamber_motor;
    private static IRSensor chamber_sensor;
    
    public Chamber(){
        chamber_motor = new Jaguar(HW.CHAMBER_MOTOR);
        chamber_sensor = new IRSensor(HW.CHAMBER_IRSENSOR);
    }
    
    public Jaguar getMotor(){
        return chamber_motor;
    }
    
    public IRSensor getSensor(){
        return chamber_sensor;
    }
    
    public void setSpeed(double speed){
        chamber_motor.set(speed);
    }
    
    public boolean isOccupied(){
        return getSensor().getDistance() <= 5;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
