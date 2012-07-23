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
    
     //order of pixel data input: topLeft, topRight, bottomRight, bottomLeft
    public static final double xTotalPixels = 320;
    public static final double yTotalPixels = 240;
    
    
    public static double x0,y0;
      
    public static double x1,y1;
    
    public static double x2,y2;
    
    public static double x3,y3;
    
    public static final double cameraTiltAngle = 20.0;
    public static final double cameraTiltAngleRadians = Math.toRadians(20.0);
    
    public static final double basketDimensionY = .4572;
    
    public static double getDistance(int[] inputArray){ //inputArray must be 8 long : order [x0,y0,x1,y1,x2,y2,x3,y3]
         if(inputArray.length != 8) //if array missing elements or too long
            return 0;
         
        x0 = (double)inputArray[0]; //initializes doubles
        y0 = yTotalPixels - (double)inputArray[1];
        x1 = (double)inputArray[2];
        y1 = yTotalPixels - (double)inputArray[3];
        x2 = (double)inputArray[4];
        y2 = yTotalPixels- (double)inputArray[5];
        x3 = (double)inputArray[6];
        y3 = yTotalPixels - (double)inputArray[7];
    
        if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
            return 0;
        
        double yPixelDistanceLeft = y0 - y3;
        double yPixelDistanceRight = y1 - y2;
        
        double yAngleLeftRadians = .5694 * yPixelDistanceLeft / 444.255;
        double yAngleRightRadians = .5694 * yPixelDistanceRight / 444.255;

        double elevationAngleLeftRadians = .5694 * (y3 - 35.745) / 444.255;
        double elevationAngleRightRadians = .5694 * (y2 - 35.745) / 444.255;
        
        //solving for unknown distances
        double alphaLeft = Math.sin(Math.PI/2 - (yAngleLeftRadians + elevationAngleLeftRadians)) * .4572 / Math.sin(yAngleLeftRadians);
        double alphaRight = Math.sin(Math.PI/2 - (yAngleRightRadians + elevationAngleRightRadians)) * .4572 / Math.sin(yAngleRightRadians);
        
        //solving for distances to goal left and right , horizontal distance
        double distanceLeft = alphaLeft * Math.sin(Math.PI/2 - elevationAngleLeftRadians) / Math.sin(Math.PI/2);
        double distanceRight = alphaRight * Math.sin(Math.PI/2 - elevationAngleRightRadians) / Math.sin(Math.PI/2);
        
        return (distanceLeft + distanceRight) / 2.0 /.3048; //converts back to feet
    }
    
    //returns the angle to the center of the goal: positive for angle-correction needed to right, negative for correction needed to left
    public static double getAngleToGoal(int[] inputArray){
        if(inputArray.length != 8) //if array missing elements or too long
            return 0;
        
        x0 = (double)inputArray[0]; //initializes doubles
        y0 = yTotalPixels - (double)inputArray[1];
        x1 = (double)inputArray[2];
        y1 = yTotalPixels - (double)inputArray[3];
        x2 = (double)inputArray[4];
        y2 = yTotalPixels - (double)inputArray[5];
        x3 = (double)inputArray[6];
        y3 = yTotalPixels - (double)inputArray[7];
        
        if(x0 == 0 || y0 == 0 || x1 == 0 || y1 == 0 || x2 == 0 || y2 == 0 || x3 == 0 || y3 == 0)
            return 0;
        
        //from getDistance method
        double yPixelDistanceLeft = y0 - y3;
        double yPixelDistanceRight = y1 - y2;
        
        double yAngleLeftRadians = .5694 * yPixelDistanceLeft / 444.255;
        double yAngleRightRadians = .5694 * yPixelDistanceRight / 444.255;
        
        double elevationAngleLeftRadians = .5694 * (y3 - 35.745) / 444.255;
        double elevationAngleRightRadians = .5694 * (y2 - 35.745) / 444.255;
        
        //solving for unknown distances
        double alphaLeft = Math.sin(Math.PI/2 - (yAngleLeftRadians + elevationAngleLeftRadians)) * .4572 / Math.sin(yAngleLeftRadians);
        double alphaRight = Math.sin(Math.PI/2 - (yAngleRightRadians + elevationAngleRightRadians)) * .4572 / Math.sin(yAngleRightRadians);
        
        //solving for distances to goal left and right , horizontal distance
        double distanceLeft = alphaLeft * Math.sin(Math.PI/2 - elevationAngleLeftRadians) / Math.sin(Math.PI/2);
        double distanceRight = alphaRight * Math.sin(Math.PI/2 - elevationAngleRightRadians) / Math.sin(Math.PI/2);
        //end getDistanceMethod
        
        double distanceToCorrectPixels;
        double angleToCorrect;
        
        if((x0+x1+x2+x3)/4 > xTotalPixels/2){
            //need to go to right
            distanceToCorrectPixels = (x1+x2)/2 - xTotalPixels/2;
            angleToCorrect = 47.0 * distanceToCorrectPixels / xTotalPixels;
            return angleToCorrect;
        }
        else if((x0 + x1 + x2 + x3)/4 < xTotalPixels/2){
            //need to go to left
            distanceToCorrectPixels = xTotalPixels/2 - (x0+x3)/2;
            angleToCorrect = 47.0 * distanceToCorrectPixels / xTotalPixels;
            return angleToCorrect*-1;
        }
        else return 0;
    }
    
   public double getRPMLow(int[] inputArray){
       double distance = getDistance(inputArray);
       
       return 0;
   }
   
   public double getRPMHigh(int[] inputArray){
       double distance = getDistance(inputArray);
       
       return 0;
   }
}