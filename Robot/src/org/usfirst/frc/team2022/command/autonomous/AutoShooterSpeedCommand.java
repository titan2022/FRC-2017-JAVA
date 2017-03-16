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
	
	long firstTime;
	
	public AutoShooterSpeedCommand(double desiredRate){
		requires(shooterSubsystem);
		this.desiredRate = desiredRate;
		
    	pidController = new CustomPIDController(ConstantsMap.KP_SHOOTER_SPEED, ConstantsMap.KI_SHOOTER_SPEED, ConstantsMap.KD_SHOOTER_SPEED, ConstantsMap.KF_SHOOTER_SPEED);
    	pidController.setInputRange(0, 33000);
    	pidController.setOutputRange(0, 1);
    	pidController.setSetpoint(31000);
    	
	}

	protected void initialize() {
	}
	
	protected void execute() {
		//Set speed from pid controller
		double speed = pidController.getOutput(shooterSubsystem.getShooterEncoderRate());
    	shooterSubsystem.setShooterSpeed(speed);
    	
//    	Activate agitator
		if(xboxMap.runAgitator()){
    		shooterSubsystem.setAgitatorSpeed(0.4);
    	}
    	else{
    		shooterSubsystem.setAgitatorSpeed(0);
    	}
	    

    	if(xboxMap.stopSystem()){
    		shooterSubsystem.stop();
    		isFinished = true;
    		cancel();
    		end();
    	}
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
