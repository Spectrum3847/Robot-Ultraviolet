/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.CommandBase;
import commands.collector.MoveToLoader;
import commands.collector.MoveToMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class ThirdCollect extends CommandGroup {
    
    public ThirdCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.loader);
        requires(CommandBase.magazine);
        requires(CommandBase.chamber);
        setInterruptible(false);
        
        
        addSequential(new MoveToLoader());
        addSequential(new MoveToMagazine());
    }
    
    public void end(){
        CommandBase.loader.addBall();
    }
    
    public void interrupted(){
        end();
    }

}
