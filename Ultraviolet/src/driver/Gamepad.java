/*
 * Easy access class
 */
package driver;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author David
 */
public class Gamepad {
    
    public static final int X_BUTTON = 1;
    public static final int A_BUTTON = 2;
    public static final int B_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int LEFT_TRIGGER = 7;
    public static final int RIGHT_TRIGGER = 8;
    public static final int BACK_BUTTON = 9;
    public static final int START_BUTTON = 10;
    public static final int LEFT_CLICK = 11;
    public static final int RIGHT_CLICK = 12;
    
    
    private static final int DEFAULT_USB_PORT = 1;
    
    protected Joystick gamepad;
    
    public Gamepad(){
        this(DEFAULT_USB_PORT);
    }
    
    public Gamepad(int port){
        gamepad = new Joystick(port);
    }
    
    public double getLeftY(){
        return -1*gamepad.getY();
    }
    
    public double getLeftX(){
        return gamepad.getX();
    }
    
    public double getRightX(){
        return gamepad.getZ();
    }
    
    public boolean getButton(int button){
        return gamepad.getRawButton(button);
    }
    
    public Joystick getGamepad(){
        return gamepad;
    }
    
}
