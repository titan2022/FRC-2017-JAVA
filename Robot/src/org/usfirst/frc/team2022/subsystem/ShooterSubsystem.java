package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.RobotMap;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
 

/**
*
*/
public class ShooterSubsystem extends Subsystem {

	CANTalon motor = new CANTalon(RobotMap.motorPort5);
	double speed = 0;
	private Encoder shooterEncoder;

	public ShooterSubsystem(){
		shooterEncoder = new Encoder(RobotMap.shooterEncoderA, RobotMap.shooterEncoderB, false);
	}
	
	public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double speed) {
		this.speed = speed;
		motor.set(speed);
	}
  
	public double getSpeed(){
		return speed;
	}
 
	public void stop(double speed){
		motor.set(0);
	 }
	
	//Get Encoder 
	public Encoder getShooterEncoder(){
		return shooterEncoder;
	}
	
	//Get Encoder Distances
	public double getShooterEncoderDistance(){
		return shooterEncoder.getDistance();
	}	

	//Get Encoder counts
	public int getShooterEncoderCount(){
		return	shooterEncoder.get();
	}	
	
	//Get Encoder Rates
	public double getShooterEncoderRate(){
		return shooterEncoder.getRate();
	}
		
	//reset encoders
	public void resetEncoders(){
		shooterEncoder.reset();
	}
}