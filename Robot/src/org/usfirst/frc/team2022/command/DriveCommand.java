package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.command.autonomous.VisionTable;
import org.usfirst.frc.team2022.command.autonomous.group.AutoGearCommandGroup;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveCommand extends Command {
	
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	XboxMap xboxMap = new XboxMap();
		
	CommandGroup commandGroup = new CommandGroup();
	
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
    	double speedLeft = xboxMap.getSpeedLeftWheel();   
    	 
    	if(Math.abs(speedLeft) < 0.1){
    		speedLeft = 0;
    	}
    	driveSubsystem.setLeftSpeed(speedLeft);

    	double speedRight = xboxMap.getSpeedRightWheel();
    	if(Math.abs(speedRight) < 0.1){

    		speedRight = 0; 
    	}
    	driveSubsystem.setRightSpeed(speedRight);
    	
    	//Autonomous gear
    	if(xboxMap.startAutoGearPlacement()){
    		commandGroup = new AutoGearCommandGroup();    		
    	}
    	
    	if(xboxMap.stopSystem()){
			commandGroup.cancel();
			VisionTable.setPegDone(true);
	  		VisionTable.setProcessPeg(false);    		
    	}
    	
    	//Brake
    	if(brakeState){
			driveSubsystem.enableBrake();
		}
		else if(!brakeState){
			driveSubsystem.disableBrake();
		}
    	
    	if(xboxMap.startAutoBrakerSystem() && (System.currentTimeMillis()-lastPressed)>200){  
    		
    		brakeState = !brakeState;
    		lastPressed = System.currentTimeMillis();
    	}
    	
    	
    	SmartDashboard.putNumber("Right Encoder Distance", driveSubsystem.getRightEncoderDistance());
    	SmartDashboard.putNumber("Left Encoder Distance", driveSubsystem.getLeftEncoderDistance());
    	SmartDashboard.putNumber("Right Encoder", driveSubsystem.getRightEncoderCount());
    	SmartDashboard.putNumber("Left Encoder", driveSubsystem.getLeftEncoderCount());
    	SmartDashboard.putNumber("Gyro Angle", driveSubsystem.getGyroAngle());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveSubsystem.stop();
    }
}
