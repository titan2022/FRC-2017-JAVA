package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
import org.usfirst.frc.team2022.command.autonomous.VisionTable;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveCommand extends Command {
	
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	XboxMap xboxMap = new XboxMap();
	OI oi = Robot.oi;
	
//	CommandGroup commandGroup = new CommandGroup();
	
	boolean brakeState = true;
	boolean inverted = true;
	long lastPressedBrake = 0;
	long lastPressedInvert = 0;

	
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
    	
    	
    	//invert drive
    	if(xboxMap.switchySwitch() && (System.currentTimeMillis()-lastPressedInvert)>200) {    		
    		driveSubsystem.switchTheSwitchySwitch();
    		lastPressedInvert = System.currentTimeMillis();   		
    	}
    
    	
    	//Autonomous gear
    	if(xboxMap.startAutoGearPlacement()){
    		VisionTable.setPegDone(false);
    		VisionTable.setProcessPeg(true);
    		Timer.delay(1);
    		
        	double pegDistance = VisionTable.getPegDistance();
        	double pegAngle = VisionTable.getPegAngle();
    		System.out.println("Should not be here");
      		AutoDriveTurnCommand autoDriveTurnCommand = new AutoDriveTurnCommand(pegAngle-6);
      		autoDriveTurnCommand.start();
    	}
    	
    	if(oi.xbox.getPOV()==180){
    		Timer.delay(1);
      		double pegDistance = VisionTable.getPegDistance();
      		AutoDriveStraightCommand autoDriveStraightCommand = new AutoDriveStraightCommand(pegDistance - 5);
      		autoDriveStraightCommand.start();
      		
      		VisionTable.setPegDone(true);
      		VisionTable.setProcessPeg(false);
    	}
    	
    	if(xboxMap.stopSystem()){
//			commandGroup.cancel();
			VisionTable.setPegDone(true);
	  		VisionTable.setProcessPeg(false);    		
    	}
    	
    	//Brake
    	
    	
    	if(xboxMap.startAutoBrakerSystem() && (System.currentTimeMillis()-lastPressedBrake)>200){  
    		
    		brakeState = !brakeState;
    		lastPressedBrake = System.currentTimeMillis();
    		if(brakeState){
    			driveSubsystem.enableBrake();
    		}
    		else if(!brakeState){
    			driveSubsystem.disableBrake();
    		}
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
