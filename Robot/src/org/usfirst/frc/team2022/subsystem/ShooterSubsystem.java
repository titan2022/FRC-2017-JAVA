package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
 

/**
*
*/
public class ShooterSubsystem extends Subsystem {

	CANTalon shooterMotor1 = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT_1);
	CANTalon shooterMotor2 = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT_2);
	CANTalon agitatorAndClimberMotor = new CANTalon(RobotMap.CLIMBER_AGITATOR_MOTOR_PORT); 
	
	private DigitalInput limitSwitch;
	
	private Encoder shooterEncoder;

	public ShooterSubsystem(){
		//Instantiate Encoder
		shooterEncoder = new Encoder(RobotMap.shooterEncoderA, RobotMap.shooterEncoderB, false);
		shooterEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		//Set Encoder distanceFromTower per pulse
		shooterEncoder.setDistancePerPulse(ConstantsMap.SHOOTER_ENCODER_DIST_PER_TICK);
		
		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_PORT);
	}
	
	public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	}

	public void setShooterSpeed(double speed) {
		shooterMotor1.set(speed);
		shooterMotor2.set(speed);
	}
	
	public void setClimberAgitatorSpeed(double speed) {
		agitatorAndClimberMotor.set(speed);
	}
  
	public double getShooterSpeed(){
		return shooterMotor1.get();
	}
	
	public double getClimberAgitatorSpeed() {
		return agitatorAndClimberMotor.get();
	}
 
	public void stopShooter(double speed){
		shooterMotor1.set(0);
		shooterMotor2.set(0);
	}
	
	public void stopAgitatorClimber() {
		agitatorAndClimberMotor.set(0);
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

	public boolean getclimberPosition() {
		// TODO Auto-generated method stub
		return limitSwitch.get();
	}
}