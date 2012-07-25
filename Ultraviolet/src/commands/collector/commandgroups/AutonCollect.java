/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;
import framework.OI;

/**
 *
 * @author root
 */
public class AutonCollect extends CommandGroup {
    
    public AutonCollect() {
            // Use requires() here to declare subsystem dependencies
            // eg. requires(chassis);
            requires(CommandBase.loader);
            requires(CommandBase.magazine);
            requires(CommandBase.chamber);
            requires(CommandBase.shooter);
            try {
            addSequential(new WaitCommand(SmartDashboard.getInt("First Delay")));
            addSequential(Init.shootSecond);
            addSequential(new WaitCommand(SmartDashboard.getInt("Second Delay")));
            addSequential(Init.shootFirst);
        } catch (NetworkTableKeyNotDefined ex) {
            System.out.println("Unable to Initialize AutonCollect");
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.shooter.setRPM(OI.panel.getRPMAxis());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

}
