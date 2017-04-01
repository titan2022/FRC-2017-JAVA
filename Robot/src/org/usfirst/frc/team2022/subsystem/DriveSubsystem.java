package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon left1,left2,left3,right1,right2, right3;

	private Encoder leftEncoder, rightEncoder;

	private AnalogGyro gyro;
	
	private DigitalInput limitSwitch, gearSwitch;
	
//	private boolean switchUrMom = false;

	public DriveSubsystem() {
		//Instantiate motors		
		left1 = new CANTalon(RobotMap.LEFT_DRIVE_PORT_1);
		left2 = new CANTalon(RobotMap.LEFT_DRIVE_PORT_2);
		left3 = new CANTalon(RobotMap.LEFT_DRIVE_PORT_3);
		right1 = new CANTalon(RobotMap.RIGHT_DRIVE_PORT_1);		
		right2 = new CANTalon(RobotMap.RIGHT_DRIVE_PORT_2);
		right3 = new CANTalon(RobotMap.RIGHT_DRIVE_PORT_3);
		
		left1.setInverted(true);
		left2.setInverted(true);
		left3.setInverted(true);
		right1.setInverted(true);
		right2.setInverted(true);
		right3.setInverted(true);

		//Instantiate Encoders
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PORT_A, RobotMap.LEFT_ENCODER_PORT_B, false);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PORT_A, RobotMap.RIGHT_ENCODER_PORT_B, false);
		
		//Instantiate Gyro
		gyro = new AnalogGyro(RobotMap.GYRO_PORT);
		gyro.calibrate();
		//Set Encoder distanceFromTower per pulse
		rightEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		leftEncoder.setDistancePerPulse(ConstantsMap.DRIVE_ENCODER_DIST_PER_TICK);
		
		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
		gearSwitch = new DigitalInput(RobotMap.GEAR_SWITCH);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public boolean getLimitSwitch(){
    	return limitSwitch.get();
    }
    
    public boolean getGearSwitch(){
    	return !gearSwitch.get();
    }

	// Setter methods for each side.
	public void setLeftSpeed(double speed) {
//		if(switchUrMom == false) {
			left1.set(speed);
			left2.set(speed);
			left3.set(speed);
//		} else {
//			right1.set(-speed);
//			right2.set(-speed);
//			right3.set(-speed);
//		}		
	}	
	public void setRightSpeed (double speed) {
//		if(switchUrMom) {
			right1.set(speed);
			right2.set(speed);		
			right3.set(speed);
//		} else {
//			left1.set(-speed);
//			left2.set(-speed);
//			left3.set(-speed);
//		}
	}
	
//	public void switchTheSwitchySwitch () {
//		switchUrMom = !switchUrMom;
//	}
	
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
	
	public void enableBrake(){
		left1.enableBrakeMode(true);
		left2.enableBrakeMode(true);
		left3.enableBrakeMode(true);
		right1.enableBrakeMode(true);
		right2.enableBrakeMode(true);
		right3.enableBrakeMode(true);
		
	}
	public void disableBrake(){
		left1.enableBrakeMode(false);
		left2.enableBrakeMode(false);
		left3.enableBrakeMode(false);
		right1.enableBrakeMode(false);
		right2.enableBrakeMode(false);
		right3.enableBrakeMode(false);
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
	
	//Gyro methods
	public AnalogGyro getGyro(){
		return gyro;
	}
	
	public double getGyroAngle(){
		return gyro.getAngle(); 
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void stop() {
		left1.set(0);
		left2.set(0);
		left3.set(0);
		right1.set(0);
		right2.set(0);
		right3.set(0);
	}
    
}

	