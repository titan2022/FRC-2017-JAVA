package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//parameter of speed motor (feet/second)
public class AutoShooterSpeedCommand extends Command{
	
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double speed = 0; 
	double desiredRate = 0;
	boolean isFinished = false;
	double outputSpeed = 0;
	
	long lastPressed = 0;
	
	CustomPIDController pidController;
	
	NetworkTable sd = NetworkTable.getTable("Preferences");
	
	public AutoShooterSpeedCommand(double desiredRate){
		requires(shooterSubsystem);
		this.desiredRate = desiredRate;
		
    	pidController = new CustomPIDController(sd.getNumber("P", ConstantsMap.KP_SHOOTER_SPEED), sd.getNumber("I", ConstantsMap.KI_SHOOTER_SPEED), sd.getNumber("D", ConstantsMap.KD_SHOOTER_SPEED), sd.getNumber("F", ConstantsMap.KF_SHOOTER_SPEED));
    	pidController.setInputRange(0, 31000);
    	pidController.setOutputRange(0, 1);
    	pidController.setSetpoint(sd.getNumber("EncoderRate", 31000));
    	
	}

	protected void initialize() {
		
	}
	
	protected void execute() {
		//Set speed from pid controller
		double speed = pidController.getOutput(shooterSubsystem.getShooterEncoderRate());
    	shooterSubsystem.setShooterSpeed(speed);
    	
    	//Activate agitator
	    if(shooterSubsystem.getServo() == 0) {	
    		if(xboxMap.runAgitator()){
	    		shooterSubsystem.setAgitatorSpeed(0.4);
	    	}
	    	else{
	    		shooterSubsystem.setAgitatorSpeed(0);
	    	}
	    }
	    
	    //Open gate
    	if(xboxMap.openGate() && System.currentTimeMillis() - lastPressed > 500){
    		lastPressed = System.currentTimeMillis();
    		if(shooterSubsystem.getServo() > 0.5){
    			shooterSubsystem.setServo(0);
    		}
    		else{
    			shooterSubsystem.setServo(1);
    		}
    	}

//	    if((System.currentTimeMillis()-lastPressed)>200){  
//    		
//    		
//	    	if(shooterSubsystem.getShooterEncoderRate() < sd.getNumber("EncoderRate", 31000) + 300 && 
//	    			sd.getNumber("EncoderRate", 31000) - 300 < shooterSubsystem.getShooterEncoderRate()){
//	    	
//	    		shooterSubsystem.setServo(0);
//	    		
//	    	} else {
//	    		
//	    		shooterSubsystem.setServo(1);
//	    	}
//	    	
//	    	lastPressed = System.currentTimeMillis();	
//	    	
//	    }
    	
    	SmartDashboard.putNumber("Shooter Speed", speed);
    	SmartDashboard.putNumber("Vision", NetworkTable.getTable("SmartDashboard").getNumber("boilerAngle", 100));
    	SmartDashboard.putNumber("Voltage", shooterSubsystem.getVoltage());
    	SmartDashboard.putNumber("Encoder Encoder Rate", shooterSubsystem.getShooterEncoderRate());
    	if(xboxMap.stopSystem()){
    		end();
    		cancel();
    	}
    	//90
    	//44.5 inches to shoot
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
