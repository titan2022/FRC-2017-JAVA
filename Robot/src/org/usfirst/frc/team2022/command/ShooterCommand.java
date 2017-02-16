package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.command.autonomous.AutoShooterSpeedCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterCommand extends Command {
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	AutoShooterSpeedCommand autoShooterCommand = new AutoShooterSpeedCommand(31000);
	XboxMap xboxMap = new XboxMap();
	OI oi = Robot.oi;
	
	public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterSubsystem);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
//    	shooterSubsystem.resetEncoders();
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	double diffSpeed = SmartDashboard.getNumber("ShooterSpeed", 1);
    	double manualSpeed = oi.xbox.GetRightTriggers();
    	shooterSubsystem.setShooterSpeed(manualSpeed);
//    	System.out.println("Running");
    	SmartDashboard.putNumber("Shooter Speed", shooterSubsystem.getShooterSpeed());
    	SmartDashboard.putNumber("Manual Speed", manualSpeed);


    	if(xboxMap.getManualShooterSpeed() > 0.05){
        	shooterSubsystem.setClimberAgitatorSpeed(0.2);
    	} else {
    		shooterSubsystem.setClimberAgitatorSpeed(0);
    	}
    	
    	if(oi.xbox.GetBValue())
    	{
    		autoShooterCommand = new AutoShooterSpeedCommand(ConstantsMap.SHOOTING_SPEED);
	   		autoShooterCommand.start();
    	}
//	   		 
	   	if(xboxMap.stopSystem()){
	   		if(!autoShooterCommand.isCanceled()){
		   		autoShooterCommand.cancel();
	   		}
	   	}
//	   	
//	   	if(xboxMap.openGate()){
//	   		shooterSubsystem.activationServo();
//	   	}
	   	
	   	SmartDashboard.putNumber("Shooter Encoder Rate", shooterSubsystem.getShooterEncoderRate());
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


