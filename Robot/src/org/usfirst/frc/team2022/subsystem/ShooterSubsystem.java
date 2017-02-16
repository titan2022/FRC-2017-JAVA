package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.ShooterCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
 

/**
*
*/
public class ShooterSubsystem extends Subsystem {

	CANTalon shooterMotor1 = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT_1);
//	CANTalon shooterMotor2 = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT_2);
	CANTalon agitatorAndClimberMotor = new CANTalon(RobotMap.CLIMBER_AGITATOR_MOTOR_PORT); 
	
	private DigitalInput limitSwitch;
	
	private Servo servo;
	

	public ShooterSubsystem(){
		shooterMotor1.setInverted(true);
//		shooterMotor2.setInverted(true);

//		//Instantiate Encoder
//		
		//Set Encoder distanceFromTower per pulse
		
//		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_PORT);
		
//		servo = new Servo(RobotMap.SERVO_MOTOR_PORT);
	}
	
	public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ShooterCommand());
	}

	public void setShooterSpeed(double speed) {
		shooterMotor1.set(speed);
//		shooterMotor2.set(speed);
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
//		shooterMotor2.set(0);
	}
	
	public void stopAgitatorClimber() {
		agitatorAndClimberMotor.set(0);
	}

	//Get Encoder Distances
	public double getShooterEncoderDistance(){
		return shooterMotor1.getEncPosition();
	}	
	
	//Get Encoder Rates
	public double getShooterEncoderRate(){
		return shooterMotor1.getEncVelocity();
	}
		
	//reset encoders
	public void resetEncoders(){
		
	}

	public boolean getclimberPosition() {
		// TODO Auto-generated method stub
		return limitSwitch.get();
	}

	public double getServo() {
		return servo.get();
	}

	public void activationServo() {
		if(servo.get() > 0.9){
			servo.set(0);
		}
		else{
			servo.set(1);
		}
	}
	
}