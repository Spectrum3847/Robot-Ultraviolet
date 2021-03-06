/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems;

import driver.SpectrumDrive;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import framework.HW;
import framework.Init;

/**
 *
 * @author David
 */
public class DriveBase extends PIDSubsystem {
    
     //Drive Motor Controller
     private Jaguar jag_1,jag_2,jag_3,jag_4;
     private Jaguar[] jag_arr;
     private SpectrumDrive spectrumDrive;
     
     //Drive Encoders
     private Encoder left_encoder;
     private Encoder right_encoder;
     private double leftOldTime = 0;
     private double rightOldTime = 0;
     private double leftOldDistance = 0;
     private double rightOldDistance = 0;
     
     //Drive X Gyro
     private AnalogChannel x_gyro_raw;
     private Gyro x_gyro;
     private double turnControllerOut = 0;
     private double tolerance = 1; //Percentage of error that the turn controller can be off and still be onTarget()
     
     

    
     public DriveBase(){
         super(HW.SKEW_KP,HW.SKEW_KI,HW.SKEW_KD);
         setJaguars();
         spectrumDrive = new SpectrumDrive(jag_1, jag_2, jag_3, jag_4);
         spectrumDrive.setMaxOutput(1.0);
         left_encoder = new Encoder(HW.LDRIVE_ENCODER_A,HW.LDRIVE_ENCODER_B,true,CounterBase.EncodingType.k1X);
         left_encoder.setDistancePerPulse(HW.DRIVEBASE_PULSE);
         right_encoder = new Encoder(HW.RDRIVE_ENCODER_A,HW.RDRIVE_ENCODER_B,true,CounterBase.EncodingType.k1X);
         right_encoder.setDistancePerPulse(HW.DRIVEBASE_PULSE);
         x_gyro_raw = new AnalogChannel(HW.X_GYRO_CHANNEL);
         x_gyro_raw.setAverageBits(2); //Get 4 samples of gyro data and average them for the raw output
         x_gyro = new Gyro(x_gyro_raw);
         this.getController().setOutputRange(-1, 1);
         this.getController().setInputRange(-360, 360);
         this.getController().setAbsoluteTolerance(tolerance);
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
    
    public double getLeftVelocity(){
        left_encoder.start();
        if (leftOldTime > 0){
            double newTime = Timer.getFPGATimestamp();
            double newDistance = getLeftEncoder().getDistance();
            double leftVelocity =  (newDistance - leftOldDistance)/(newTime - leftOldTime);
            leftOldDistance = newDistance;
            leftOldTime = newTime;
            return leftVelocity;
        } else{
            leftOldDistance = getLeftEncoder().getDistance();
            leftOldTime = Timer.getFPGATimestamp();
            return 0;
        }
    }
    
    public double getRightVelocity(){
        right_encoder.start();
        if (rightOldTime > 0){
            double newTime = Timer.getFPGATimestamp();
            double newDistance = getRightEncoder().getDistance();
            double leftVelocity =  (newDistance - rightOldDistance)/(newTime - rightOldTime);
            rightOldDistance = newDistance;
            rightOldTime = newTime;
            return leftVelocity;
        } else{
            rightOldDistance = getRightEncoder().getDistance();
            rightOldTime = Timer.getFPGATimestamp();
            return 0;
        }
    }
    
    
    public void setLeft(double left_speed){
        spectrumDrive.tankDrive(left_speed, jag_3.get());
    }
    
    public void setRight(double right_speed){
        spectrumDrive.tankDrive(jag_1.get(), right_speed);
    }
    
    public void setArcade(double straight_speed, double turn_speed){
            spectrumDrive.arcadeDrive(straight_speed, turn_speed, true);
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
    
/*    private void setCANJaguars(){
        try{
        jag_arr = new CANJaguar[4];
        jag_1 = HW.defJaguar(3);//new CANJaguar(3); //Front Left 7
        jag_arr[0] = jag_1;
        jag_2 = HW.defJaguar(2);//new CANJaguar(2); //Rear Left 8
        jag_arr[1] = jag_2;
        jag_3 = HW.defJaguar(1); //new CANJaguar(1); //Front Right 6
        jag_arr[2] = jag_3;
        jag_4 = HW.defJaguar(6); //new CANJaguar(6); //Rear Right 5
        jag_arr[3] = jag_4;
        }catch(Exception e){System.out.println("Failed to initialize CAN");}
    }*/
    
    private void setJaguars(){
        jag_arr = new Jaguar[4];
        jag_1 = new Jaguar(HW.FRONT_LDRIVE_MOTOR);//new CANJaguar(3); //Front Left 7
        jag_arr[0] = jag_1;
        jag_2 = new Jaguar(HW.REAR_LDRIVE_MOTOR);//new CANJaguar(2); //Rear Left 8
        jag_arr[1] = jag_2;
        jag_3 = new Jaguar(HW.FRONT_RDRIVE_MOTOR); //new CANJaguar(1); //Front Right 6
        jag_arr[2] = jag_3;
        jag_4 = new Jaguar(HW.REAR_RDRIVE_MOTOR); //new CANJaguar(6); //Rear Right 5
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
