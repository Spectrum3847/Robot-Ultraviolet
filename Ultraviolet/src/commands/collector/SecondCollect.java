/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import commands.collector.second.MoveToLoader;
import commands.collector.second.MoveToLowerMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class SecondCollect extends CommandGroup {
    
    public SecondCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new MoveToLoader());
        addSequential(new MoveToLowerMagazine());
    }
    
    public void end(){
        CommandBase.loader.addBall();
    }
    
    public void interrupted(){
        end();
    }

}
