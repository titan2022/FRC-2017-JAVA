package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    	
		double absSpeedLeft = Math.abs(speedLeft);
    	if(absSpeedLeft < 0.1){
    		speedLeft = 0;
    	}
    	driveSubsystem.setLeftSpeed(speedLeft);

    	double speedRight = oi.xbox.GetRightY();

    	driveSubsystem.setSpeedRight(speedRight);
    	
    	SmartDashboard.putNumber("Left Encoder Raw Count", leftEncoder.get());
    	SmartDashboard.putNumber("Right Encoder Raw Count", rightEncoder.get());
    	SmartDashboard.putNumber("Left Encoder Distance", leftEncoder.getDistance());
    	SmartDashboard.putNumber("Right Encoder Distance", rightEncoder.getDistance());
    	SmartDashboard.putNumber("Left Encoder Rate", leftEncoder.getRate());
    	SmartDashboard.putNumber("Right Encoder Rate", rightEncoder.getRate());
    	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());

    	double absSpeedRight = Math.abs(speedRight);
    	if(absSpeedRight < 0.1){

    		speedRight = 0; 
    	}
    	

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
