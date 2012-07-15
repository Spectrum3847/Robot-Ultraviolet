/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import commands.collector.first.MoveToChamber;
import commands.collector.first.MoveToLoader;
import commands.collector.first.MoveToMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class FirstCollect extends CommandGroup {
    
    public FirstCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new MoveToLoader());
        addSequential(new MoveToMagazine());
        addSequential(new MoveToChamber());
    }
    
    public void end(){
        CommandBase.loader.addBall();
    }
    
    public void interrupted(){
        end();
    }

}
