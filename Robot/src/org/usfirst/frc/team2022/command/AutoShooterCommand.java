package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

//parameter of speed motor (feet/second)
public class AutoShooterCommand extends Command{
	PIDController shooterController; 
	DummyPIDOutput pidOutput;
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	double speed; 
	OI oi = Robot.oi;
	boolean isFinished = false; 
	
	public AutoShooterCommand(double desiredRate){
		speed = desiredRate;
		requires(shooterSubsystem);
	}

	protected void initialize() {
		shooterController = new PIDController(ConstantsMap.P, ConstantsMap.I, ConstantsMap.D, ConstantsMap.F, shooterSubsystem.getShooterEncoder(), pidOutput);
		shooterController.setOutputRange(-1,1);
    	shooterController.setInputRange(-100, 100);
    	shooterController.setSetpoint(speed);
    	shooterController.enable();
	}
	
	protected void execute() {
    	while(oi.xbox.GetBValue() == false){
    		double pidOutputValue = shooterController.get();
    		shooterSubsystem.setSpeed(pidOutputValue);
    	}
    	shooterSubsystem.setSpeed(0);
    	isFinished = true; 
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}
}
