package commands;

import edu.wpi.first.wpilibj.command.Command;
import framework.OI;
import subsystems.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author 
 * #git test
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    public static DriveBase drivebase = new DriveBase();
    public static Elevator elevator = new Elevator();
    public static Shooter shooter = new Shooter();
    public static Collector collector = new Collector();
    public static Pneumatics pneumatics = new Pneumatics();
   //public static Camera cam = new Camera("10.38.47.33","8882");
    // Create a single static instance of all of your subsystems


    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        
        //SmartDashboard.putData(null);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
