
package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author Hunter
**/
public class Magazine extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Jaguar magazine_motor;
    private IRSensor lower_ir_sensor;
    private IRSensor upper_ir_sensor;
    
    public static int BOTTOM_SENSOR = 0;
    public static int TOP_SENSOR = 1;
    
    public Magazine(){
        magazine_motor= new Jaguar(HW.MAGAZINE_MOTOR);
        lower_ir_sensor = new IRSensor(HW.LOWER_MAGAZINE_IRSENSOR);
        upper_ir_sensor = new IRSensor(HW.UPPER_MAGAZINE_IRSENSOR);
    }
    public void initDefaultCommand() {
    }
    public void setSpeed(double speed){
            magazine_motor.set(speed);
    }

    public Jaguar getMotor(){
        return magazine_motor;
    }
    public IRSensor getTopIRSensor(){
        return upper_ir_sensor;
    }
    public IRSensor getBottomIRSensor(){
        return lower_ir_sensor;
    }

    public boolean isOccupied(int sensor){
        if(sensor == 0)
            return lower_ir_sensor.getDistance() <= 15;
        else if(sensor == 1)
            return upper_ir_sensor.getDistance() <= 5;
        else return false;
    }
}
