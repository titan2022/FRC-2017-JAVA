package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class DriveCommand extends Command {
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	boolean brakeState = true;
	long lastPressed = 0;
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
    	 
    	if(Math.abs(speedLeft) < 0.1){
    		speedLeft = 0;
    	}
    	driveSubsystem.setLeftSpeed(speedLeft);

    	double speedRight = oi.xbox.GetRightY();
    	if(Math.abs(speedRight) < 0.1){

    		speedRight = 0; 
    	}
    	driveSubsystem.setRightSpeed(speedRight);
    	
    	//SmartDashboard.putNumber("Left Encoder Raw Count = ", driveSubsystem.getLeftEncoderCount());
    	//SmartDashboard.putNumber("Right Encoder Raw Count = ", driveSubsystem.getRightEncoderCount());
    	//SmartDashboard.putNumber("Left Encoder Distance = ", driveSubsystem.getLeftEncoderDistance());
    	//SmartDashboard.putNumber("Right Encoder Distance = ", driveSubsystem.getRightEncoderDistance());
    	//SmartDashboard.putNumber("Left Encoder Rate = ", driveSubsystem.getLeftEncoderRate());
    	//SmartDashboard.putNumber("Right Encoder Rate = ", driveSubsystem.getRightEncoderRate());
    	//SmartDashboard.putNumber("Gyro Angle = ", driveSubsystem.getGyroAngle());

    	SmartDashboard.putNumber("Range in Inches =  ", driveSubsystem.getRangeInInches());
    	SmartDashboard.putNumber("Range Average in Inches =  ", driveSubsystem.getAverageRangeInInches());
    	
    	
    	//Brake
    	//Brake
    	driveSubsystem.getLeft1().enableBrakeMode(brakeState);
		driveSubsystem.getLeft2().enableBrakeMode(brakeState);
		driveSubsystem.getRight1().enableBrakeMode(brakeState);
		driveSubsystem.getRight2().enableBrakeMode(brakeState);
    	if(oi.xbox.GetRightBumperValue() && (lastPressed-System.currentTimeMillis()>20)){  
    		brakeState = !brakeState;
    		lastPressed = System.currentTimeMillis();
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
