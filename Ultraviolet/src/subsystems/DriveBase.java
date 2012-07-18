/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import commands.CommandBase;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;
import framework.HW;
import framework.Init;

/**
 *
 * @author David
 */
public class DriveBase extends PIDSubsystem {
    
     //Drive Motor Controller
     public static double kP = 0.0;
     private Jaguar jag_1,jag_2,jag_3,jag_4;
     private Jaguar[] jag_arr;
     private final RobotDrive robotDrive;
     
     //Drive Encoders
     private Encoder left_encoder;
     private Encoder right_encoder;
     
     //Drive X Gyro
     private AnalogChannel x_gyro_raw;
     private SendableGyro x_gyro;
     private double turnControllerOut = 0;
     private double tolerance = 1; //Percentage of error that the turn controller can be off and still be onTarget()

     //Used for leftVelocity
    public static double leftVelocity = 0;
    private static double leftOldDistance = 0;
    private static double leftOldTime = 0;
    
     public DriveBase(){
         super(kP,HW.SKEW_KI,HW.SKEW_KD);
         setJaguars();
         robotDrive = new RobotDrive(jag_1, jag_2, jag_3, jag_4);
         left_encoder = new Encoder(HW.LDRIVE_ENCODER_A,HW.LDRIVE_ENCODER_B,true,CounterBase.EncodingType.k1X);
         left_encoder.setDistancePerPulse(HW.DRIVEBASE_PULSE);
         right_encoder = new Encoder(HW.RDRIVE_ENCODER_A,HW.RDRIVE_ENCODER_B,true,CounterBase.EncodingType.k1X);
         right_encoder.setDistancePerPulse(HW.DRIVEBASE_PULSE);
         x_gyro_raw = new AnalogChannel(HW.X_GYRO_CHANNEL);
         x_gyro_raw.setAverageBits(2); //Get 4 samples of gyro data and average them for the raw output
         x_gyro = new SendableGyro(x_gyro_raw);
         this.getController().setOutputRange(-1, 1);
         this.getController().setInputRange(-360, 360);
         this.getController().setTolerance(tolerance);
    }
    
    
     /**
      * Set the Default Command
      * Sets it to driveSelector so that we always have the correct drive mode 
      * renabled after breaking or turning.
      */
    public void initDefaultCommand(){
        setDefaultCommand(Init.driveSelector);   // set default command
    }
    
    /**
     * This defines the input to the PID controller, in this case the gryo
     * angle for the turn controller PID loop
     */
    public double returnPIDInput(){
        return getAngle();
    }
    
    /**
     * Code that takes the output from the PID controller when it's running
     */
    public void usePIDOutput(double output){
        turnControllerOut = output;
    }
    
    /*
     * Get PID Output
     */
    public double getPIDTurnOutput(){
        return turnControllerOut;
    }
    
    //Return the PID Controller
    public PIDController getController(){
        return this.getPIDController();
    }
    
    public void setPID(double p, double i, double d){
        getController().setPID(p, i, d);
    }
    
    //Is the turn controller enabled?
    public boolean isControllerEnanbled(){
        return getController().isEnable();
    }
       /*
     * Turn on the controller
     */
    public void enableTurnController(){
        if (!isControllerEnanbled()) {
            x_gyro.reset();
            getController().setSetpoint(0.0);
            getController().enable();
        }
    }
    
    /**
     * Disable Turn Controller
     */
    public void disableTurnController(){
        if (isControllerEnanbled()){
            getController().disable();
        }
    }
    
    /**
     * Get the setpoint for the turn controller
     * @return the setpoint from the turn controller
     */
    public double getSetpoint(){
        return getController().getSetpoint();
    }
    
    //Reset the gyro angle and it's PID controller for the drivebase
    public void reset(){
        x_gyro.reset();
        getController().reset();
    }
    
    //Return the Gyro object
    public Gyro getGyro(){
        return x_gyro;
    }
    
    //Return the rawGyro AnalogChannel
    public AnalogChannel getRawGyro(){
        return x_gyro_raw;
    }
    
    //Output gyro average voltage based on 4 samples
    public double getGyroRate(){
        return x_gyro_raw.getAverageVoltage();
    }
    
    //Get the current gyro angle
    public double getAngle(){
        return x_gyro.getAngle();
    }
    
    public Encoder getRightEncoder(){
        return right_encoder;
    }
    
    public Encoder getLeftEncoder(){
        return left_encoder;
    }
    
    public void setLeft(double left_speed){
        robotDrive.tankDrive(-left_speed, jag_3.get());
            //jag_1.set(left_speed);
            //jag_2.set(left_speed);
    }
    
    public void setRight(double right_speed){
        robotDrive.tankDrive(jag_1.get(), right_speed);
           // jag_3.set(-1*right_speed);
            //jag_4.set(-1*right_speed);
    }
    
    public double getLeft(){
        return -1 * jag_1.get();
    }
    
    public double getRight(){
        return jag_3.get();
    }
    
    public double getLeftVelocity(){
        if (leftOldTime > 0){
            double newTime = Timer.getFPGATimestamp();
            double newDistance = getLeftEncoder().getDistance();
            leftVelocity =  (newDistance - leftOldDistance)/(newTime - leftOldTime);
            leftOldDistance = newDistance;
            leftOldTime = newTime;
            return leftVelocity;
        } else{
            leftOldDistance = getLeftEncoder().getDistance();
            leftOldTime = Timer.getFPGATimestamp();
            return 0;
        }
    }
    
    public void setArcade(double straight_speed, double turn_speed){
        if (getController().isEnable()){
            robotDrive.arcadeDrive(straight_speed, turnControllerOut,false);
        }
        else {
            robotDrive.arcadeDrive(straight_speed, turn_speed, true);
        }   
    }
    
    public void turnLeft(double speed){
        setLeft(speed);
        setRight(-1*speed);
    }
    
    public void turnRight(double speed){
        setLeft(-1*speed);
        setRight(speed);
    }

    /**
     * START INIT COMMANDS
     */
    
    private void setJaguars(){
        jag_arr = new Jaguar[4];
        jag_1 = new Jaguar(7); //Front Left
        jag_arr[0] = jag_1;
        jag_2 = new Jaguar(8); //Rear Left
        jag_arr[1] = jag_2;
        jag_3 = new Jaguar(6); //Front Right
        jag_arr[2] = jag_3;
        jag_4 = new Jaguar(5); //Rear Right
        jag_arr[3] = jag_4;
    }
    

    /**
     * END INIT COMMANDS
     */
    
    public Jaguar getJaguar(int id){
        return jag_arr[id-1];
    }
    
    public Jaguar[] getJagArr(){
        return jag_arr;
    }
}
