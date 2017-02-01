package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
 

/**
*
*/
public class ShooterSubsystem extends Subsystem {

	CANTalon motor = new CANTalon(RobotMap.motorPortShooter);
	double speed = 0;
	private Encoder shooterEncoder;

	public ShooterSubsystem(){
		//Instantiate Encoder
		shooterEncoder = new Encoder(RobotMap.shooterEncoderA, RobotMap.shooterEncoderB, false);
		shooterEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		//Set Encoder distanceFromTower per pulse
		shooterEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
	}
	
	public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double speed) {
		motor.set(speed);
	}
  
	public double getSpeed(){
		return speed;
	}
 
	public void stop(double speed){
		//motor.set(0);
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