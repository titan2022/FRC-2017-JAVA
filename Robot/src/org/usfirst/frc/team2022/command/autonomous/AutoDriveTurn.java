package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveTurn extends Command {
	
	private boolean finished = false;
	private double degreeToTurn = 0;
	private double speed = 0;
	private boolean clockwise = true;
	
	//PID Objects
	PIDController turnController;
	DummyPIDOutput pidOutput;
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	
	

    public AutoDriveTurn(double degreeToTurn, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.degreeToTurn = degreeToTurn;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Create pid output object
    	pidOutput = new DummyPIDOutput();
    	
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	
    	//Create PIDController and enable
    	double dP	 = SmartDashboard.getNumber("P", 0.3);
    	double dD = SmartDashboard.getNumber("D", 0.05);
    	double dI = SmartDashboard.getNumber("I", 0.1);
    	turnController = new PIDController(dP, dI, dD, ConstantsMap.kF, driveSubsystem.getGyro(), pidOutput);
    	turnController.setOutputRange(-speed, speed);
    	turnController.setContinuous(true);
    	turnController.setInputRange(-360, 360);
    	turnController.setAbsoluteTolerance(0.05);
    	turnController.setSetpoint(degreeToTurn);
    	turnController.enable();
    	
    	if(driveSubsystem.getGyroAngle() < degreeToTurn){
    		clockwise = true;
    	}
    	else{
    		clockwise = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	if(clockwise){
        		//Get PIDController output
    		double pidOutputValue = turnController.get();
    		SmartDashboard.putNumber("Output", pidOutputValue);
    		
    		//adjust speed of each wheel
    		driveSubsystem.setLeftSpeed(-pidOutputValue);
    		driveSubsystem.setRightSpeed(-pidOutputValue);
//    		driveSubsystem.tankDrive(pidOutputValue, -pidOutputValue);
    		if(turnController.onTarget() || oi.xbox.GetRightBumperValue()){
    			finished = true;
    		}
    	}
    	else{
    		double pidOutputValue = turnController.get();
    		
    		//adjust speed of each wheel
    		driveSubsystem.tankDrive(pidOutputValue, pidOutputValue);
    		if(turnController.onTarget() || oi.xbox.GetRightBumperValue()){
    			finished = true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
