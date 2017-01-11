package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.Gyro;
import org.usfirst.frc.team2022.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
package org.usfirst.frc.team2022.subsystem;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon left1,left2,right1,right2;
	private RobotDrive myRobot;
	private AnalogGyro gyro; 
	double Kp = 0; 
	
	public DriveSubsystem() {
		//Instantiate motors		
		left1 = new CANTalon(RobotMap.motorPort1);
		left2 = new CANTalon(RobotMap.motorPort2);
		right1 = new CANTalon(RobotMap.motorPort3);		
		right2 = new CANTalon(RobotMap.motorPort4);		
		
		gyro = new AnalogGyro(1);
	}
	
	public double getAngle(){
		return gyro.getAngle(); 
	}
	
	public void SetSensitivity(double sensitivity){
		Kp = sensitivity; 
	}
	
	public void reset(){
		gyro.reset();
	}
	
	public void calibrate(){
		gyro.calibrate();
	}
	
	// Setter methods for each side.
	public void setLeftSpeed(double speed) {
		
		left1.set(speed);
		left2.set(speed);
		
	}
	
	public void setRightSpeed (double speed) {
		
		left1.set(speed);
		left2.set(speed);		
	}
	
	// Getter method for each side.
	public double getLeftSpeed() {
		
		return left1.getSpeed();
	}
	
	public double getRightSpeed() {
		
		return right1.getSpeed();
		
	}
	public void stop() {
		
		this.left1.set(0);
		this.left2.set(0);
		this.right1.set(0);
		this.right2.set(0);

	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

	