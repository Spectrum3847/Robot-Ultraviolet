/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.CommandBase;
import commands.collector.MagazineSpacer;
import commands.collector.MoveToLoader;
import commands.collector.MoveToLowerMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class SecondCollect extends CommandGroup {
    
    public SecondCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.loader);
        requires(CommandBase.magazine);
        requires(CommandBase.chamber);
        
        addSequential(new MoveToLoader());
        addSequential(new MoveToLowerMagazine());
        addSequential(new MagazineSpacer());
    }
    
    
    public void end(){
        CommandBase.loader.addBall();
    }
    
    public void interrupted(){
        end();
    }

}
