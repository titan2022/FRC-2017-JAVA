package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

//parameter of speed motor (feet/second)
public class AutoShooterSpeedCommand extends Command implements PIDOutput{
	
	PIDController shooterController; 
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double speed = 0; 
	boolean isFinished = false;
	double outputSpeed = 0;
	
	public AutoShooterSpeedCommand(double desiredRate){
		requires(shooterSubsystem);
		speed = desiredRate;
	}

	protected void initialize() {
		shooterController = new PIDController(ConstantsMap.KP_SHOOTER_SPEED, ConstantsMap.KI_SHOOTER_SPEED, ConstantsMap.KD_SHOOTER_SPEED, ConstantsMap.KF_SHOOTER_SPEED, shooterSubsystem.getShooterEncoder(), this);
		shooterController.setOutputRange(-1,1);
    	shooterController.setInputRange(-100, 100);
    	shooterController.setSetpoint(speed);
    	shooterController.enable();
	}
	
	protected void execute() {
		shooterSubsystem.activationServo();
    	shooterSubsystem.setShooterSpeed(outputSpeed);
    	
    	if(xboxMap.stopSystem()){
    		shooterController.disable();
    		end();
    		cancel();
    	}
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		outputSpeed = output;
	}
}
