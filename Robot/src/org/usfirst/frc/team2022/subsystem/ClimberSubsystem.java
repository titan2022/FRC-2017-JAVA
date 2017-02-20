package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.ClimberCommand;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
 

/**
*
*/
public class ClimberSubsystem extends Subsystem {

	CANTalon climberMotor;	

	public ClimberSubsystem(){
		
		climberMotor = new CANTalon(RobotMap.CLIMBER_MOTOR_PORT);		

	}	
	
	public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ClimberCommand());
	}

	
	//Motor Methods
	public void setClimberSpeed(double speed) {
		climberMotor.set(speed);
	}
	
	public double getClimberSpeed(){
		return climberMotor.getSpeed();
	}
	
	public void stopClimber(){
		climberMotor.set(0);
	}
}