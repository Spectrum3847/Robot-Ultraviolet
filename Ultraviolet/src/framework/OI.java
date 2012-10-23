package framework;

import commands.drive.MoveEBrake;
import commands.drive.QuickTurn;
import driver.Gamepad;
import driver.Panel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public static final Joystick driveStick_left = new Joystick(HW.usbPort_two);
    public static final Joystick driveStick_right = new Joystick(HW.usbPort_one);
    public static final Gamepad gamepad = new Gamepad(4);
    public static final Panel panel = new Panel();
    
    //Buttons
    
    //Left Joystick
    public static final Button manualCollect_but = new JoystickButton(driveStick_left, 1);               //While Held
    //public static final Button eBrakeFWD_but = new JoystickButton(driveStick_left, 6); //When Pressed
    //public static final Button eBrakeREV_but = new JoystickButton(driveStick_left, 7); //When Pressed
    public static final Button autoCollect_but = new JoystickButton(driveStick_left,3);
    public static final Button cancelCollect_but = new JoystickButton(driveStick_left,2);
    
    //Right Joysctick
    public static final Button manualEject_but = new JoystickButton(driveStick_right, 1);               //While Held
    public static final Button eBrake_but = new JoystickButton(driveStick_right, 2);      //Toggle
    //public static final Button quickLeftTurn_but = new JoystickButton(driveStick_right, 4); //when pressed
    //public static final Button quickRightTurn_but = new JoystickButton(driveStick_right, 5); //when pressed
    public static final Button tankDrive_but = new JoystickButton(driveStick_right, 6);  //When Pressed
    public static final Button haloDrive_but = new JoystickButton(driveStick_right, 7);   //When Pressed
    //public static final Button pidHaloDrive_but = new JoystickButton(driveStick_right, 8);  //When Pressed
    public static final Button jacks_but = new JoystickButton(driveStick_right, 10);    //Toggle
    public static final Button compressor_but = new JoystickButton(driveStick_right, 11); //Toggle

    //Panel
    public static final Button add_but = new JoystickButton(panel.getPanel(),Panel.BP1);
    public static final Button subtract_but = new JoystickButton(panel.getPanel(),Panel.BM1);
    public static final Button fire_but = new JoystickButton(panel.getPanel(),Panel.FIRE);
    public static final Button manualEject_panel_but = new JoystickButton(panel.getPanel(), Panel.DN);  //While Held
    public static final Button manualCollect_panel_but = new JoystickButton(panel.getPanel(), Panel.UP); //While Held 
    public static final Button speedControl_panel_but = new JoystickButton(panel.getPanel(),Panel.SPD);  //While Held
    
    //Panel Camera Controls
    public static final Button cam_init_but = new JoystickButton(panel.getPanel(),Panel.INIT);
    public static final Button cam_disc_but = new JoystickButton(panel.getPanel(),Panel.CAN);
    public static final Button cam_data_but = new JoystickButton(panel.getPanel(),Panel.HUP);
    public static final Button shooting_mode_but = new JoystickButton(panel.getPanel(),Panel.HDN);
    
    //Use this constructor to setup up button schedulers for commands
    public OI() {

        //Manual Reject and Collect
        manualCollect_but.whileHeld(Init.manualCollect);
        manualEject_but.whileHeld(Init.manualEject);
        manualEject_panel_but.whileHeld(Init.manualEject);
        manualCollect_panel_but.whileHeld(Init.manualCollect);
        
        //Auto Collect
        autoCollect_but.whenPressed(Init.checkCollect);
        cancelCollect_but.cancelWhenPressed(Init.firstCollect);
        cancelCollect_but.cancelWhenPressed(Init.secondCollect);
        cancelCollect_but.cancelWhenPressed(Init.thirdCollect);
        cancelCollect_but.whenPressed(Init.subtractBall);
        add_but.whenReleased(Init.addBall);
        subtract_but.whenReleased(Init.subtractBall);

        //Quick Turns
        //quickLeftTurn_but.whenPressed(new QuickTurn(-90));
        //quickRightTurn_but.whenPressed(new QuickTurn(90));

        //Button for Drive Modes
        tankDrive_but.whenPressed(Init.manualTankDrive);
        haloDrive_but.whenPressed(Init.haloDrive);
        //pidHaloDrive_but.whenPressed(Init.pidHaloDrive);
    
        //eBrake Adjustments
        //eBrakeFWD_but.whenPressed(new MoveEBrake(HW.EBRAKE_INCREMENT));
        //eBrakeREV_but.whenPressed(new MoveEBrake(-HW.EBRAKE_INCREMENT));
        
        //Shooter Control
        speedControl_panel_but.toggleWhenPressed(Init.panel_speedControl);
        fire_but.whenPressed(Init.shootBall);
        cam_data_but.whenPressed(Init.printCam);
        cam_init_but.whenPressed(Init.connectCam);
        cam_disc_but.whenPressed(Init.disconnectCam);
        shooting_mode_but.toggleWhenPressed(Init.shootingMode);
        
        /*
         * These next functions need the modified wpilibj Button.java
         *
         * Add the following methods after the whilePressed method
         *
         */
        /**
         * // * Toggles a command when the button is pressed //
         *
         * @param command the command to start //
         */
        //    public void toggleWhenPressed(final Command command) {
        //         new ButtonScheduler() {
        //
        //            boolean pressedLast = grab();
        //
        //            public void execute() {
        //                if (grab()) {
        //                    if (!pressedLast) {
        //                        pressedLast = true;
        //                        if (command.isRunning()){
        //                            command.cancel();
        //                        } else{
        //                            command.start();
        //                        }
        //                    }
        //                } else {
        //                    pressedLast = false;
        //                }
        //            }
        //        }.start();
        //    }
        //    
        //         /**
        //     * Cancels a command when the button is pressed
        //     * @param command the command to start
        //     */
        //    public void cancelWhenPressed(final Command command) {
        //         new ButtonScheduler() {
        //
        //            boolean pressedLast = grab();
        //
        //            public void execute() {
        //                if (grab()) {
        //                    if (!pressedLast) {
        //                        pressedLast = true;
        //                        command.cancel();
        //                    }
        //                } else {
        //                    pressedLast = false;
        //                }
        //            }
        //        }.start();
        //    }
        
        //EBrake Toggle
        eBrake_but.toggleWhenPressed(Init.eBrake); //Toggle eBrake_but on button press
        //Toggle for Jacks/Rainbow
        jacks_but.toggleWhenPressed(Init.deployJacks);
        //Toggle Compressor
        compressor_but.toggleWhenPressed(Init.runCompressor);

    }
}
