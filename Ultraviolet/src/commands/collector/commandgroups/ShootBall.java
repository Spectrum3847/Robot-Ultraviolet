/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.collector.commandgroups;

import commands.collector.FireBall;
import commands.collector.MoveToChamber;
import commands.collector.SpaceMagazine;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author root
 */
public class ShootBall extends CommandGroup {
    
    public ShootBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new FireBall());
        addSequential(new MoveToChamber());
        addSequential(new SpaceMagazine());
    }

}
