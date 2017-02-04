package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command{
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem; 
	
	XboxMap xboxMap = new XboxMap();
	
	public ClimberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterSubsystem);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	double manualSpeed = xboxMap.getManualClimberSpeed();
    	
    	if (xboxMap.startAutoClimberSystem()) {
    		while(!shooterSubsystem.getclimberPosition()) {
    			shooterSubsystem.setClimberAgitatorSpeed(ConstantsMap.climberSpeed);
    			if(xboxMap.stopSystem()){
    				break;
    			}
    		}
    	}
    	
    	if (shooterSubsystem.getclimberPosition() == false) {
    		shooterSubsystem.setClimberAgitatorSpeed(manualSpeed);
    	}
    	else {
    		shooterSubsystem.stopAgitatorClimber();;
    	}
    	
    }
    
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}	
}