
package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

/**
 *
 * @author Hunter
**/
public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Jaguar elevator_motor;
    private IRSensor lower_ir_sensor;
    private IRSensor upper_ir_sensor;
    
    public static int BOTTOM_SENSOR = 0;
    public static int TOP_SENSOR = 1;
    
    public Elevator(){
        elevator_motor= new Jaguar(HW.ELEVATOR_MOTOR);
        lower_ir_sensor = new IRSensor(HW.BOT_ELEVATOR_IRSENSOR);
        upper_ir_sensor = new IRSensor(HW.TOP_ELEVATOR_IRSENSOR);
    }
    public void initDefaultCommand() {
    }
    public void setSpeed(double speed){
            elevator_motor.set(speed);
    }

    public Jaguar getMotor(){
        return elevator_motor;
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
            return upper_ir_sensor.getDistance() <= 10;
        else return false;
    }
}
