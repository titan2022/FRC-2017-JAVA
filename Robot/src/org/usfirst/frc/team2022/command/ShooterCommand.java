package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.command.autonomous.AutoShooterCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterCommand extends Command {
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	AutoShooterCommand autoShooterCommand; 	
	XboxMap xboxMap = new XboxMap();
	
	public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterSubsystem);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	shooterSubsystem.resetEncoders();
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	double manualSpeed = xboxMap.getManualShooterSpeed();
    	shooterSubsystem.setShooterSpeed(manualSpeed);
    	if(xboxMap.getManualShooterSpeed() > 0.05){
        	shooterSubsystem.setClimberAgitatorSpeed(0.2);
    	} else {
    		shooterSubsystem.setClimberAgitatorSpeed(0);
    	}
    	
    	if(xboxMap.startAutoShooterSystem())
    	{
    		shooterSubsystem.setClimberAgitatorSpeed(0.2);
    		    		
    		autoShooterCommand = new AutoShooterCommand(ConstantsMap.motorSpeed);
	   		autoShooterCommand.start();
    	}
	   		 
	   	if(xboxMap.stopSystem()){
	   		autoShooterCommand.cancel();
	   	}
    	
    	SmartDashboard.putNumber("Left Encoder Raw Count = ", shooterSubsystem.getShooterEncoderCount());
    	SmartDashboard.putNumber("Left Encoder Distance = ", shooterSubsystem.getShooterEncoderDistance());
    	SmartDashboard.putNumber("Left Encoder Rate = ", shooterSubsystem.getShooterEncoderRate());
   }
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	 // Called once after isFinished returns true
    protected void end() {
    	shooterSubsystem.setShooterSpeed(0);
    	shooterSubsystem.setClimberAgitatorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooterSubsystem.setShooterSpeed(0);
    	shooterSubsystem.setClimberAgitatorSpeed(0);
    }

}


