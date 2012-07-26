/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

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
   public static final double yFieldTotal = Math.toRadians(40.8); //40.8 default
   public static final double xFieldTotal = Math.toRadians(54.4); //54.4 default
   
   public static double x0,y0,x1,y1,x2,y2,x3,y3;
   
   //intermediates
   public static double elevationAngle;
   public static double targetAngle;
   public static double s;
   public static double alpha;
   public static double beta;
   public static double gamma;
   
   
   //experimental
   public static double yDistance;
   //
   
   //results
   public static double r;
   
   public static double getDistanceLeft(int[] inputArray){
       
       double belowHorizontal = yFieldTotal/2.0 - cameraTilt;
       double yView = yFieldTotal - belowHorizontal;
       double yCutoff = belowHorizontal * yPixelsTotal / yFieldTotal;
       double yPixelsView = yPixelsTotal - yCutoff;
       
       if(inputArray.length!=8)
           return 0;
       
       x0 = (double)inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = (double)inputArray[6];
       y1 = yPixelsTotal - (double)inputArray[7];
       x2 = (double)inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = (double)inputArray[2];
       y3 = yPixelsTotal - (double)inputArray[3];
       
       yDistance = y0 - y3;
       //experimental
       double xTemp = Math.abs(x0 - x3);
       double yHyp = Math.sqrt(xTemp*xTemp + yDistance*yDistance);
       yDistance = yHyp;
       //
       
       if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
           return 0;
            
       targetAngle = yView * (yDistance) / yPixelsView;
       elevationAngle = yView * (y3 - yCutoff) / yPixelsView;
       alpha = Math.PI/2.0 - elevationAngle;
       gamma = Math.PI/2.0 + elevationAngle;
       beta = Math.PI - targetAngle - (Math.PI/2.0 + elevationAngle);
       s = 1.5 * Math.sin(beta) / Math.sin(targetAngle);
       r = s * Math.sin(alpha);
       
       return r;
   }
   
   public static double getDistanceRight(int[] inputArray){
       
       double belowHorizontal = yFieldTotal/2.0 - cameraTilt;
       double yView = yFieldTotal - belowHorizontal;
       double yCutoff = belowHorizontal * yPixelsTotal / yFieldTotal;
       double yPixelsView = yPixelsTotal - yCutoff;
       
        if(inputArray.length!=8)
           return 0;
       
       x0 = (double)inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = (double)inputArray[6];
       y1 = yPixelsTotal - (double)inputArray[7];
       x2 = (double)inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = (double)inputArray[2];
       y3 = yPixelsTotal - (double)inputArray[3];
       
       yDistance = y1 - y2;
        //experimental
       double xTemp = Math.abs(x0 - x3);
       double yHyp = Math.sqrt(xTemp*xTemp + yDistance*yDistance);
       yDistance = yHyp;
       //
       
       if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
           return 0;
       
       targetAngle = yView * (yDistance) / yPixelsView;
       elevationAngle = yView * (y2 - yCutoff) / yPixelsView;
       alpha = Math.PI/2.0 - elevationAngle;
       gamma = Math.PI/2.0 + elevationAngle;
       beta = Math.PI - targetAngle - (Math.PI/2.0 + elevationAngle);
       s = 1.5 * Math.sin(beta) / Math.sin(targetAngle);
       r = s * Math.sin(alpha);
       
       return r;
   }
   
   public static double getDistance(int[] inputArray){
      double left = getDistanceLeft(inputArray);
      double right = getDistanceRight(inputArray);
      if((left > 20 || left < 0) && (right < 20 && right > 0)){
          return right;
      }
      else if((right>20 || right < 0) && (left < 20 & left > 0)){
          return left;
      }
      else if(right > 20 && left > 20)
          return 10000;
      else if(right < 0 && left < 0)
          return -10000;
      else
          return (getDistanceRight(inputArray) + getDistanceLeft(inputArray)) / 2.0;
   }
   
   public static double getOffsetAngle(int[] inputArray){
       if(inputArray.length!=8)
           return 0;
       
       x0 = (double)inputArray[0];
       y0 = yPixelsTotal - (double)inputArray[1];
       x1 = (double)inputArray[6];
       y1 = yPixelsTotal - (double)inputArray[7];
       x2 = (double)inputArray[4];
       y2 = yPixelsTotal - (double)inputArray[5];
       x3 = (double)inputArray[2];
       y3 = yPixelsTotal - (double)inputArray[3];
       
       double center = xPixelsTotal / 2.0;
       double avg = (x0 + x1 + x2 + x3) / 4.0;
       
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
   
   public static double getRangeLow(int[] inputArray){
       double distance = getDistance(inputArray);
       if(distance<0){
           return 0;
       }
       else if(distance > 20){
           return 3600;
       }
       else if(distance<8){
           return 0;
       }
       else if(distance < 9){
           return 0;
       }
       else if(distance < 10){
           return 0;
       }
       else if(distance < 11){
           return 0;
       }
       else if(distance < 12){
           return 0;
       }
       else if(distance < 13){
           return 0;
       }
       else if(distance < 14){
           return 0;
       }
       else if(distance < 15){
           return 0;
       }
       else
           return 0;
   }
   
   public static double getRangeHigh(int[] inputArray){
       double distance = getDistance(inputArray);
       if(distance<0){
           return 0;
       }
       else if(distance > 20){
           return 3600;
       }
       else if(distance<8){
           return 0;
       }
       else if(distance < 9){
           return 0;
       }
       else if(distance < 10){
           return 0;
       }
       else if(distance < 11){
           return 0;
       }
       else if(distance < 12){
           return 0;
       }
       else if(distance < 13){
           return 0;
       }
       else if(distance < 14){
           return 0;
       }
       else if(distance < 15){
           return 0;
       }
       else
           return 3600;
   }
}