/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author David
 */
public class Panel {
    
    public static final int FIRE = 12;
    public static final int UP = 7;
    public static final int DN = 11;
    public static final int S = 5;
    public static final int O = 6;
    public static final int CAN = 9;
    public static final int INIT = 8;
    public static final int BP1 = 1;
    public static final int HUP = 4;
    public static final int HDN = 3;
    public static final int BM1 = 2;
    public static final int SPD = 10;
    
    public static final int PANEL_PORT = 3;
    
    
    protected Joystick panel;
    
    public Panel(){
        this(PANEL_PORT);
    }
    
    public Panel(int port){
        panel = new Joystick(port);
    }
    
    public double getRPMAxis(){
        return ((panel.getX()+1.0)/2.0)*3600.0;
    }
    
    public double getHoodAxis(){
        return (panel.getZ()+1.0)/2.0;
    }
    
    public boolean getButton(int button){
        return panel.getRawButton(button);
    }
    
    public Joystick getPanel(){
        return panel;
    }
    
}
