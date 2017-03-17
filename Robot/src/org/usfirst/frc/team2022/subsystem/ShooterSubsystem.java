//package org.usfirst.frc.team2022.subsystem;
//
//import org.usfirst.frc.team2022.command.ShooterCommand;
//import org.usfirst.frc.team2022.robot.ConstantsMap;
//import org.usfirst.frc.team2022.robot.RobotMap;
//
//import com.ctre.CANTalon;
//import com.ctre.CANTalon.FeedbackDevice;
//import com.ctre.CANTalon.TalonControlMode;
//
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.Servo;
//import edu.wpi.first.wpilibj.command.Subsystem;
// 
//
///**
//*
//*/
//public class ShooterSubsystem extends Subsystem {
//
//	CANTalon shooterMotor1;
//	CANTalon agitatorMotor;
//	private double feederSpeed = -0.5;
//		
//	private Servo servo;	
//
//	public ShooterSubsystem(){
//		shooterMotor1 = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT);
//		agitatorMotor = new CANTalon(RobotMap.AGITATOR_MOTOR_PORT);
////		servo = new Servo(RobotMap.SERVO_MOTOR_PORT);
//
//		shooterMotor1.setInverted(true);
//		shooterMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		shooterMotor1.reverseSensor(false);
//	}	
//	
//	public void initDefaultCommand() {
//	// Set the default command for a subsystem here.
//	//setDefaultCommand(new MySpecialCommand());
//		setDefaultCommand(new ShooterCommand());
//	}
//	
//	//Motor Methods
//	public void setShooterSpeed(double speed) {
//		shooterMotor1.set(speed);
//	}
//	
//	public void setAgitatorSpeed(double speed){
//		agitatorMotor.set(speed);
//	}
//	
//	public double getVoltage(){
//		return shooterMotor1.getBusVoltage();
//	}
//	
//	public double getCurrent(){
//		return shooterMotor1.getOutputCurrent();
//	}
//  
//	public double getShooterSpeed(){
//		return shooterMotor1.getSpeed();
//	}
//	
//	public double getAgitatorSpeed() {
//		return agitatorMotor.get();
//	}
// 
//	public void stopShooter(){
//		shooterMotor1.set(0);
//	}
//	
//	public void stopAgitator() {
//		agitatorMotor.set(0);
//	}
//	
//	//Sensor Methods
//
//	//Get Encoder Distances
//	public double getShooterEncoderDistance(){
//		return shooterMotor1.getEncPosition();
//	}	
//	
//	//Get Encoder Rates
//	public double getShooterEncoderRate(){
//		return shooterMotor1.getEncVelocity();
//	}
//
//	//Servo Methods
//	public double getServo() {
//		return servo.get();
//	}
//
//	public void setServo(double position) {
//		servo.set(position);
//	}
//	
//	public void stop(){
//		stopShooter();
//		stopAgitator();
//	}
//}