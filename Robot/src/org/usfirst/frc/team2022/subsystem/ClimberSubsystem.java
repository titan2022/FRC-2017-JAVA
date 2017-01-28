package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem{

	private CANTalon climbMotor;
	private DigitalInput inputSwitch;
	private double climberSpeed = 0; 
	private boolean climberPosition = false; 
	
	public ClimberSubsystem(){
		climbMotor = new CANTalon(RobotMap.motorPortClimber);
		inputSwitch = new DigitalInput(RobotMap.motorPortSwitch);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub	
	}
	
	public double getSpeedClimber() {
		return climberSpeed; 
	}
	
	public void setClimbSpeed(double climberSpeed){
		climbMotor.set(climberSpeed);
	}
	
	public void stop(){
		climbMotor.set(0);
	}
	
	public boolean getclimberPosition() {
		return climberPosition; 
	}
}
