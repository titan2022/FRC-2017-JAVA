package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterCommand extends Command {
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	
	OI oi = Robot.oi;
	
	public ShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterSubsystem);
    }
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	double manualSpeed = oi.xbox.GetRightTriggers();
    	shooterSubsystem.setSpeed(manualSpeed);
    	
    	if(oi.xbox.GetAValue() == true)
    	{
    		AutoShooterCommand autoShooterCommand = new AutoShooterCommand(ConstantsMap.motorSpeed);
    		while(!isFinished()){
    			autoShooterCommand.start();
    		}
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
    	shooterSubsystem.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooterSubsystem.setSpeed(0);
    }

}


