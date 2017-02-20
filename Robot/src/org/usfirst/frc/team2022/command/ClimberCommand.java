package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberCommand extends Command {
	ClimberSubsystem climberSubsystem = Robot.climberSubsystem;
	
	XboxMap xboxMap = new XboxMap();
	
	public ClimberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(climberSubsystem);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
	   	
	   	//Climber methods
	   	double manualClimberSpeed = xboxMap.getManualClimberSpeed();
	   	
	   	climberSubsystem.setClimberSpeed(manualClimberSpeed);
    	
   	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	 // Called once after isFinished returns true
    protected void end() {
    	climberSubsystem.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	climberSubsystem.stopClimber();
    }

}


