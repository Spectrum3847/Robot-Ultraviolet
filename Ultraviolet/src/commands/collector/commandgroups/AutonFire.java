/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import framework.Init;
import framework.OI;

/**
 *
 * @author root
 */
public class AutonFire extends CommandGroup {
    
    public AutonFire() {
            // Use requires() here to declare subsystem dependencies
            // eg. requires(chassis);
        addSequential(new WaitCommand(SmartDashboard.getNumber("First Delay")));
        addSequential(new ShootSecond());
        addSequential(new WaitCommand(SmartDashboard.getNumber("Second Delay")-SmartDashboard.getNumber("First Delay")));
        addSequential(new ShootFirst());
        addSequential(new FirstCollect());
        addSequential(new WaitCommand(0.5));
        addSequential(new ShootFirst());
        addSequential(new FirstCollect());
        addSequential(new WaitCommand(0.5));
        addSequential(new ShootFirst());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Init.panel_speedControl.start();
        CommandBase.shooter.setRPM(OI.panel.getRPMAxis());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    
    public void end(){
        Init.panel_speedControl.cancel();
    }
    
    public void interrupted(){
        end();
    }

}
