/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector;

import commands.CommandBase;
import commands.collector.third.MoveToLoader;
import commands.collector.third.MoveToMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class ThirdCollect extends CommandGroup {
    
    public ThirdCollect() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
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
