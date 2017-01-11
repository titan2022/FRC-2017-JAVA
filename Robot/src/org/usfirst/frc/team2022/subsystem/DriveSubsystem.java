package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon left, right;
	
	public DriveSubsystem() {
		//Instantiate motors		
		left = new CANTalon(RobotMap.leftDrivePort);
		right = new CANTalon(RobotMap.rightDrivePort);		
		
		
	}
	// Setter methods for each side.
	public void setLeftSpeed(double speed) {
		
		left.set(speed);
		
	}
	
	public void setRightSpeed (double speed) {
		
		right.set(speed);
		
	}
	
	// Getter methods for each side.
	public double getLeftSpeed() {
		
		return left.getSpeed();
		
	}
	
	public double getRightSpeed() {
		
		return right.getSpeed();
		
	}
	public void stop() {
		
		this.left.set(0);
		this.right.set(0);

	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

	