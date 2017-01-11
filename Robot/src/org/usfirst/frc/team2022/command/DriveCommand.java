package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class DriveCommand extends Command {
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	
    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedLeft = oi.xbox.GetLeftY();
    	if(speedLeft > 0.1)
    		speedLeft = 0;
    	driveSubsystem.setLeftSpeed(speedLeft);
    	double speedRight = oi.xbox.GetRightY();
    	if(speedRight > 0.1)
    		speedRight = 0; 
    	driveSubsystem.setRightSpeed(speedRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSubsystem.setLeftSpeed(0);
		driveSubsystem.setRightSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveSubsystem.setLeftSpeed(0);
		driveSubsystem.setRightSpeed(0);
    }
}
