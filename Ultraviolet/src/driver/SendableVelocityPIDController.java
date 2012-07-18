/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SendablePIDController;

/**
 *
 * @author JAG
 */
public class SendableVelocityPIDController extends SendablePIDController {
        /**
     * Allocate a PID object with the given constants for P, I, D, using a 50ms period.
     * @param p the proportional coefficient
     * @param i the integral coefficient
     * @param d the derivative coefficient
     * @param source The PIDSource object that is used to get values
     * @param output The PIDOutput object that is set to the output value
     */
    public SendableVelocityPIDController(double p, double i, double d, PIDSource source, PIDOutput output) {
        super(p, i, d, source, output);
    }
    
}
