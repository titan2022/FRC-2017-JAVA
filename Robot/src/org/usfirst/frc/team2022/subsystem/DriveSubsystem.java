package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.robot.ConstantsMap;

import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon left1,left2,right1,right2;
	private Encoder leftEncoder, rightEncoder;
	private AnalogGyro gyro; 
	private Ultrasonic ultra;
	double Kp = 0; 
	

	

	
	public DriveSubsystem() {
		//Instantiate motors		
		left1 = new CANTalon(RobotMap.motorPort1);
		left2 = new CANTalon(RobotMap.motorPort2);
		right1 = new CANTalon(RobotMap.motorPort3);		
		right2 = new CANTalon(RobotMap.motorPort4);		

		//Instantiate Encoders
		leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, false);
		rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false);
		
		//Instantiate Gyro
		gyro = new AnalogGyro(1);
		
		//Instantiate Ultrasonic 
		ultra = new Ultrasonic(RobotMap.pingChannel, RobotMap.echoChannel);
		
		//Set Encoder distanceFromTower per pulse
		rightEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
	}
	
	public AnalogGyro getGyro(){
		return gyro;
	}
	
	public double getGyroAngle(){
		return gyro.getAngle(); 
	}
	
	public void SetSensitivity(double sensitivity){
		Kp = sensitivity; 
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public void calibrate(){
		gyro.calibrate();
	}
		
	public Ultrasonic getUltraSensor(){
		return ultra;
	}
	
	public double getRangeInches(){
		return ultra.getRangeInches();
	}
	public double getRangeMillimeters(){
		return ultra.getRangeMM();
	}
	
	// Setter methods for each side.
	public void setLeftSpeed(double speed) {		
		left1.set(speed);
		left2.set(speed);	
	}	
	public void setRightSpeed (double speed) {
		right1.set(speed);
		right2.set(speed);		
	}
	
	// Getter method for each side.
	public double getLeftSpeed() {		
		return left1.getSpeed();
	}	
	public double getRightSpeed() {		
		return right1.getSpeed();		
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed){
		setLeftSpeed(leftSpeed);
		setRightSpeed(rightSpeed);
	}
	
	//Get Encoder 
	public Encoder getRightEncoder(){
		return rightEncoder;
	}
	public Encoder getLeftEncoder(){
		return leftEncoder;
	}
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
	}	
	public double getLeftEncoderDistance(){
		return leftEncoder.getDistance();
	}
	
	//Get Encoder counts
	public int getLeftEncoderCount(){
		return leftEncoder.get();
	}	
	public int getRightEncoderCount(){
		return rightEncoder.get();
	}
	
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	//reset encoders
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
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

	