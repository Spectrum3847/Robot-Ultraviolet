package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

public class Collector extends Subsystem {
    //the new official ball count class XD
    private Jaguar collector_motor;
    protected IRSensor ir_sensor;
    
    private int count;
    
    public Collector(){
        ir_sensor = new IRSensor(HW.COLLECTOR_IRSENSOR);
        collector_motor=new Jaguar(HW.COLLECTOR_MOTOR); 
        count = 0;
    }
    
    public void initDefaultCommand() {
    }
    
    public Jaguar getJaguar(){
        return collector_motor;
    }
    
    public void setSpeed(double speed){   
            collector_motor.set(speed);
    }
    
    public IRSensor getIRSensor(){
        return ir_sensor;
    }
    public double getDistanceFromBall(){
        return ir_sensor.getDistance();
    }


    public void setCollectorMotor(double speed){
        collector_motor.set(speed);
    }
    
    public int getBallCount(){
        return count;
    }
    
    public void setBallCount(int balls){
        if(balls < 0) count = 0;
        else count = balls;
    }
    
    public boolean isOccupied(){
        return ir_sensor.getDistance() <= 13;
    }
  
}