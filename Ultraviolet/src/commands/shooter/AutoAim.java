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
   public static final double yPixelsTotal = 240.0;
   public static final double xPixelsTotal = 320.0;
   public static final double cameraTilt = Math.toRadians(20.0);
   public static final double targetDimension = 1.5;
   public static final double yFieldTotal = Math.toRadians(51.0); //find 
   public static final double xFieldTotal = Math.toRadians(68.0); //find
   
   public static final double belowHorizontal = yFieldTotal/2.0 - cameraTilt;
   public static final double yView = yFieldTotal - belowHorizontal;
   public static final double yCutoff = belowHorizontal * yPixelsTotal / yFieldTotal;
   public static final double yPixelsView = yPixelsTotal - yCutoff;
   
   public static double x0,y0,x1,y1,x2,y2,x3,y3;
   
   //intermediates
   public static double elevationAngle;
   public static double targetAngle;
   public static double s;
   public static double alpha;
   public static double beta;
   public static double gamma;
   
   //results
   public static double r;
   
   public static double getDistanceLeft(int[] inputArray){
       if(inputArray.length!=8)
           return 0;
       
       x0 = inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = inputArray[2];
       y1 = yPixelsTotal - (double)inputArray[3];
       x2 = inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = inputArray[6];
       y3 = yPixelsTotal - (double)inputArray[7];
       
       if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
           return 0;
            
       targetAngle = yView * (y0 - y3) / yPixelsView;
       elevationAngle = yView * (y3 - yCutoff) / yPixelsView;
       alpha = Math.PI/2.0 - elevationAngle;
       gamma = Math.PI/2.0 + elevationAngle;
       beta = Math.PI - targetAngle - (Math.PI/2.0 + elevationAngle);
       s = 1.5 * Math.sin(beta) / Math.sin(targetAngle);
       r = s * Math.sin(alpha);
       
       return r;
   }
   
   public static double getDistanceRight(int[] inputArray){
        if(inputArray.length!=8)
           return 0;
       
       x0 = inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = inputArray[2];
       y1 = yPixelsTotal - (double)inputArray[3];
       x2 = inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = inputArray[6];
       y3 = yPixelsTotal - (double)inputArray[7];
       
       if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
           return 0;
       
       targetAngle = yView * (y1 - y2) / yPixelsView;
       elevationAngle = yView * (y2 - yCutoff) / yPixelsView;
       alpha = Math.PI/2.0 - elevationAngle;
       gamma = Math.PI/2.0 + elevationAngle;
       beta = Math.PI - targetAngle - (Math.PI/2.0 + elevationAngle);
       s = 1.5 * Math.sin(beta) / Math.sin(targetAngle);
       r = s * Math.sin(alpha);
       
       return r;
   }
   
   public static double getDistance(int[] inputArray){
       return (getDistanceRight(inputArray) + getDistanceLeft(inputArray)) / 2;
   }
   
   public static double getOffsetAngle(int[] inputArray){
       if(inputArray.length!=8)
           return 0;
       
       x0 = inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = inputArray[2];
       y1 = yPixelsTotal - (double)inputArray[3];
       x2 = inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = inputArray[6];
       y3 = yPixelsTotal - (double)inputArray[7];
       
       double center = xFieldTotal / 2;
       double avg = (x0 + x1 + x2 + x3) / 4;
       
       if(avg==center)
           return 0;
       else if(avg > center){
           return Math.toDegrees(xFieldTotal * (avg - center) / xPixelsTotal);
       }
       else if(avg < center){
           return Math.toDegrees(xFieldTotal * (avg - center) / xPixelsTotal);
       }
       else
           return 0;
   }
}