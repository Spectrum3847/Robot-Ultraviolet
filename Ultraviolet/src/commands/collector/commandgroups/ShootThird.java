/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.CommandBase;
import commands.collector.*;
import commands.shooter.FireBall;
import edu.wpi.first.wpilibj.command.CommandGroup;
import framework.Init;

/**
 *
 * @author root
 */
public class ShootThird extends CommandGroup {
    
    public ShootThird() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new FireBall());
        addSequential(new ClearChamber());
        addSequential(new MoveToChamber());
        addSequential(new MoveDownToMagazine());
        addSequential(new DownMagazineSpacer());
    }
 
    public boolean isFinished(){
        return super.isFinished() || !Init.panel_speedControl.isRunning();
    }

    public void end(){
        if(!Init.panel_speedControl.isRunning())
            CommandBase.loader.addBall();
    }
    
    public void interrupted(){
        end();
    }
}
