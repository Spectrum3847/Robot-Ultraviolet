
package framework;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * The HW is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {

    /**DRIVEBASE MOTOR ASSIGNMENTS**/
    public static final int REAR_LDRIVE_MOTOR = 7;
    public static final int REAR_RDRIVE_MOTOR = 8;
    public static final int FRONT_LDRIVE_MOTOR = 6;
    public static final int FRONT_RDRIVE_MOTOR = 5;
    
    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (PWM)**/
    public static final int FRONT_SHOOTER_MOTOR = 1;
    public static final int REAR_SHOOTER_MOTOR = 2;
    public static final int LOADER_MOTOR = 3;
    public static final int MAGAZINE_MOTOR = 4;
    public static final int CHAMBER_MOTOR = 9;
    
    //Digital Input
    public static final int PRESSURE_CHANNEl = 7;
    
    /**
     * SOLENOID CHANNELS
     */
    public static final int RAINBOW = 1;
    
 
    /**PID CONSTANTS**/
    
    /**DRIVEBASE PID**/
    public static final double EBRAKE_KP = 10;
    public static final double EBRAKE_KI = 0.0;
    public static final double EBRAKE_KD = 3;
    public static final double EBRAKE_MOVE_KP = 5;
    public static final double EBRAKE_MOVE_KI = 0.0;
    public static final double EBRAKE_MOVE_KD = 1;    
    public static final double DRIVEBASE_PULSE = (1.0 / 1080.0);    //36:12 GEAR RATIO, measured in REV PER SEC.
    public static final double EBRAKE_SETPOINT = 0.0;
    public static final double EBRAKE_INCREMENT = 2;
    public static final double EBRAKE_TOLERANCE = 1;


    /**SHOOTER PID**/
    public static final double SHOOTER_KP = 5.055E-6;//5.225E-6;
    public static final double SHOOTER_KI = 3.0e-11;
    public static final double SHOOTER_KD = 1.5e-5;
    public static final double SHOOTER_PULSE = 0.00277777777777777777777777777778; //IN REV PER SEC.

    
    /**
     * Turn Controller PID - Straight
     */
    public static final double SKEW_KP = 0.0169;
    public static final double SKEW_KI = 0.0;
    public static final double SKEW_KD = 0.0;
    
    /**
     * Turn Controller PID - QuickTurn
     */
    public static final double TURN_KP = 0.0;
    public static final double TURN_KI = 0.0;
    public static final double TURN_KD = 0.0;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    public static final int RDRIVE_ENCODER_A = 1;
    public static final int RDRIVE_ENCODER_B = 2;
    public static final int LDRIVE_ENCODER_A = 3;
    public static final int LDRIVE_ENCODER_B = 4;
    
    /**DIGITAL SENSOR ALLOCATIONS - SHOOTER SYSTEM **/
    public static final int SHOOTER_ENCODER = 14;
    
    /**DIGITAL SENSOR ALLOCATIONS - HOOD SYSTEM **/
    public static final int UPPER_LIMIT_SWITCH = 8;
    public static final int LOWER_LIMIT_SWITCH = 9;
    
    /**ANALOG SENSOR ALLOCATIONS - MISC. **/
    public static final int LOADER_IRSENSOR = 2;
    public static final int LOWER_MAGAZINE_IRSENSOR =3;
    public static final int CHAMBER_IRSENSOR = 4;
    public static final int UPPER_MAGAZINE_IRSENSOR = 5;
    public static final int X_GYRO_CHANNEL = 1;
    
    /**RELAY ALLOCATIONS**/
    public static final int COMPRESSOR_RELAY = 1;
    public static final int BRIDGE_RELAY = 2;
    public static final int RELAY_ON = 3;
    public static final int RELAY_OFF = 4;
    

    
    
    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int usbPort_one = 1;
    public static final int usbPort_two = 2;
    public static final int usbPort_three = 3;
    
    
    /**SPEED/VELOCITY/DISTANCE DEFINITIONS**/
    public static final double MAX_SPEED = 1.0;
    public static final double MIN_SPEED = -1.0;
    public static final int IR_SENSOR_DISTANCE_FROM_BALL=13;
    public static final double SHOOTER_VELOCITY_SETPOINT = 0;

    
    /**@DEPRECATED SPI ALLOCATION**/
    public static final int spiSCK  = 1;
    public static final int spiMOSI = 2;
    public static final int spiMISO = 3;
    public static final int spiCS = 4;
    
    /**DIGITAL MODULE SLOTS**/
    public static final int DM_ONE = 1;
    public static final int DM_TWO = 2;
    
    
    /**INNER-CODE MISCELANEOUS**/
    public static final int NUM_COMMANDS = 5;
    public static final int SAMPLING_SIZE = 40;
    public static final double POT_CALIBRATION = 238.75347126436785+81.374;
    public static final double IRSENSOR_DELAY = 0.05;
    public static final double IRSENSOR_DETECT_THRESHOLD = 19;
    public static final int POTENTIOMETER_ERROR_RANGE = 5;
    public static final double POT_MIN_OUTPUT = -0.4;
    public static final double POT_MAX_OUTPUT = 0.6;
    public static final String IMU_IP = "10.38.47.33";
    public static final String IMU_PORT = "8881";
    public static final String CAMERA_IP = "10.38.47.33";
    public static final String CAMERA_PORT = "8882";
    public static final int BUFFER_SIZE = 50;
    
    
    
    /**JAGUAR DEFINE METHOD (CAN BUS)**/
    public static CANJaguar defJaguar(int id){

    CANJaguar jag = null;
    try{
    jag = new CANJaguar(id);
    }catch(Exception e){System.err.println(e.getMessage());}
    System.out.println("Connected to CANJaguar "+id);
    return jag;
    }
    
}
