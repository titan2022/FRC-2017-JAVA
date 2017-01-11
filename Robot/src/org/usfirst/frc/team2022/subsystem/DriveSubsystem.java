package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon left1,left2,right1,right2;
	
	public DriveSubsystem() {
		//Instantiate motors		
		left1 = new CANTalon(RobotMap.motorPort1);
		left2 = new CANTalon(RobotMap.motorPort2);
		right1 = new CANTalon(RobotMap.motorPort3);		
		right2 = new CANTalon(RobotMap.motorPort4);		
		
		
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
    	setDefaultCommand(new DriveCommand());
    }
    
}

	