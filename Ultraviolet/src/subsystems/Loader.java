package subsystems;

import driver.IRSensor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import framework.HW;

public class Loader extends Subsystem {
    //the new official ball count class XD
    private Jaguar loader_motor;
    protected IRSensor ir_sensor;
    
    private int count;
    
    public Loader(){
        ir_sensor = new IRSensor(HW.LOADER_IRSENSOR);
        loader_motor=new Jaguar(HW.LOADER_MOTOR); 
        count = 0;
    }
    
    public void initDefaultCommand() {
    }
    
    public Jaguar getJaguar(){
        return loader_motor;
    }
    
    public void setSpeed(double speed){   
            loader_motor.set(speed);
    }
    
    public IRSensor getIRSensor(){
        return ir_sensor;
    }
    public double getDistanceFromBall(){
        return ir_sensor.getDistance();
    }


    public void setCollectorMotor(double speed){
        loader_motor.set(speed);
    }
    
    public int getBallCount(){
        return count;
    }
    
    public void addBall(){
        count+= 1;
    }
    
    public void subtractBall(){
        count-=1;
    }
    
    public void clearBalls(){
        count = 0;
    }
    
    public boolean isOccupied(){
        return ir_sensor.getDistance() <= 11;
    }
  
}