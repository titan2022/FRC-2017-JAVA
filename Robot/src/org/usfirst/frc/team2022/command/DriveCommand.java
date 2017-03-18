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
		
	boolean brakeState = true;
	long lastPressed = 0;
	double turtleSpeed = 0.3;
	boolean turtle = false;
	
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
    	

    	double speedRight = xboxMap.getSpeedRightWheel();
    	if(Math.abs(speedRight) < 0.1){
    		speedRight = 0; 
    	}
    	
    	if(oi.xbox.GetRightBumperValue() && System.currentTimeMillis() - lastPressed > 200){
    		turtle = !turtle;
    		lastPressed = System.currentTimeMillis();
    	}
    	
//    	if(xboxMap.switchySwitch()) {
//    		driveSubsystem.switchTheSwitchySwitch();
//    	}
    	
    	//Autonomous gear
//    	if(xboxMap.startAutoGearAlignment()){
//    		Timer.delay(1);
//    		
//        	double pegAngle = VisionTable.getPegAngle();
//      		AutoDriveTurnCommand autoDriveTurnCommand = new AutoDriveTurnCommand(pegAngle-6);
//      		autoDriveTurnCommand.start();
//    	}
    	
    	if(oi.xbox.getPOV() == 0){
    		driveSubsystem.setLeftSpeed(-turtleSpeed);
        	driveSubsystem.setRightSpeed(turtleSpeed);
    	}
    	else if (oi.xbox.getPOV() == 180){
    		driveSubsystem.setLeftSpeed(turtleSpeed);
        	driveSubsystem.setRightSpeed(-turtleSpeed);
    	}
    	else if(turtle){
    		driveSubsystem.setLeftSpeed(speedLeft * turtleSpeed);
        	driveSubsystem.setRightSpeed(speedRight * turtleSpeed);

    	}
    	else{
    		driveSubsystem.setLeftSpeed(speedLeft);
        	driveSubsystem.setRightSpeed(speedRight);

    	}
    	
    	if(xboxMap.moveTowardsGear()){
//    		Timer.delay(1);
//      		double pegDistance = VisionTable.getPegDistance();
//      		AutoDriveStraightCommand autoDriveStraightCommand = new AutoDriveStraightCommand(pegDistance - 5);
//      		autoDriveStraightCommand.start();
    		double distance = VisionTable.getUltrasonicDistance();
    		AutoDriveStraightCommand autoDriveStraightCommand = new AutoDriveStraightCommand(distance-11.7);
      		autoDriveStraightCommand.start();
    	}
    	
    	if(xboxMap.stopSystem()){
 		
    	}
    	
    	//Brake
//    	if(brakeState){
//			driveSubsystem.enableBrake();
//		}
//		else if(!brakeState){
//			driveSubsystem.disableBrake();
//		}
    	
//    	if(xboxMap.startAutoBrakerSystem() && (System.currentTimeMillis()-lastPressed)>200){  
//    		
//    		brakeState = !brakeState;
//    		lastPressed = System.currentTimeMillis();
//    	}
    	    	    	
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
