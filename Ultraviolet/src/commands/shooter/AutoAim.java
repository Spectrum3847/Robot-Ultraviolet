/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands.shooter;

/**
 *
 * @author Zachary
 */
public class AutoAim {
    
    //known values
    public static final double xPixels = 320.0;
    public static final double yPixels = 240.0;
    public static final double xFieldRadians = Math.toRadians(47.0);
    public static final double yFieldRadians = Math.toRadians(35.25);
    public static final double targetY = 1.5;
    public static final double cameraTilt = Math.toRadians(20.0);
    
    public static double x0,y0,x1,y1,x2,y2,x3,y3;
    
    //need to find
    public static double elevationAngle1, elevationAngle2;   // = yField * y3/2 / yPixels
    public static double sectionAngle1, sectionAngle2; // = yField * (y0/1 - y3/2) / yPixels
    public static double alpha1, alpha2; // = 90 -(cameraTilt + elevationAngle + sectionAngle)
    public static double q1,q2; // = 1.5 * sin(alpha) / sin(sectionAngle)
    public static double beta1,beta2; // = 180 - sectionAngle - alpha
    public static double r1,r2; // = 1.5 * sin(beta)/ sin(sectionAngle)
    public static double s1,s2; // r * sin(alpha) / sin(90)
    
    
    //0 pairs with 3, 1 pairs with 2;
    public static double getDistance(int[] inputArray){ //input top left then clockwise
        if(inputArray.length != 8) //if array missing elements or too long
            return 0;
         
        x0 = (double)inputArray[0]; //initializes doubles
        y0 = yPixels - (double)inputArray[1];
        x1 = (double)inputArray[2];
        y1 = yPixels - (double)inputArray[3];
        x2 = (double)inputArray[4];
        y2 = yPixels- (double)inputArray[5];
        x3 = (double)inputArray[6];
        y3 = yPixels - (double)inputArray[7];
    
        if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
            return 0;
        
        elevationAngle1 = yFieldRadians * y3 / yPixels;
        elevationAngle2 = yFieldRadians * y2 / yPixels;
        
        sectionAngle1 = yFieldRadians* (y0 - y3) / yPixels;
        sectionAngle2 = yFieldRadians* (y1 - y2) / yPixels;
        
        alpha1 = Math.toRadians(90.0)-(cameraTilt+elevationAngle1+sectionAngle1);
        alpha2 = Math.toRadians(90.0)-(cameraTilt+elevationAngle2+sectionAngle2);
        
        q1 = targetY * Math.sin(alpha1) / Math.sin(sectionAngle1);
        q2 = targetY* Math.sin(alpha2) / Math.sin(sectionAngle2);
        
        beta1 = Math.toRadians(180.0) - sectionAngle1 - alpha1;
        beta2 = Math.toRadians(180.0) - sectionAngle2 - alpha2;
        
        r1 = targetY * Math.sin(beta1) / Math.sin(sectionAngle1);
        r2 = targetY * Math.sin(beta2) / Math.sin(sectionAngle2);
        
        s1 = r1*Math.sin(alpha1);
        s2 = r2*Math.sin(alpha2);
        
        return (s1+s2)/2;
    }
    
    public static double getOffsetAngle(int[] inputArray){
        if(inputArray.length != 8) //if array missing elements or too long
            return 0;
        
        x0 = (double)inputArray[0]; //initializes doubles
        y0 = yPixels - (double)inputArray[1];
        x1 = (double)inputArray[2];
        y1 = yPixels - (double)inputArray[3];
        x2 = (double)inputArray[4];
        y2 = yPixels - (double)inputArray[5];
        x3 = (double)inputArray[6];
        y3 = yPixels - (double)inputArray[7];
        
        if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
            return 0;
        
        double xCenter = xPixels / 2;
        double total = x0 + x1 + x2 + x3;
        if(total/4 > xCenter){//too far right
            return -1 * 47.0 * (xCenter-total/4)/xPixels;
        }
        else if(total/4 < xCenter){//too far left
            return -1 * 47.0 * (xCenter - total/4) / xPixels;
        }
        else
            return 0;
    }

}