/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package framework;


import edu.wpi.first.wpilibj.IterativeRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Init.robotInit();
    }
    
    public void disabledInit(){
        Disabled.init();
    }
    
    public void disabledPeriodic(){
        Disabled.periodic();
    }
    
    public void disabledContinuous(){
        Disabled.continuous();
    }
    
    public void autonomousInit() {
       Autonomous.init();
    }

    public void autonomousPeriodic() {
        Autonomous.periodic();
    }

    public void teleopInit() {
        Teleop.init();
    }

    public void teleopPeriodic() {
        Teleop.periodic();
    }
}
