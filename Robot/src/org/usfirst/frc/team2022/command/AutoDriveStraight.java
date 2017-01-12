package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public abstract class AutoDriveStraight extends Command {
	
	private boolean finished = false;
	private double inchesToDrive = 0;
	private double speed = 0;
	
	//PID Objects
	PIDController straightController;
	DummyPIDOutput pidOutput;
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	
	

    public AutoDriveStraight(double inchesToDrive, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.inchesToDrive = inchesToDrive;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Create pid output object
    	pidOutput = new DummyPIDOutput();
    	
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	
    	//Create PIDController and enable
    	straightController = new PIDController(ConstantsMap.kP, ConstantsMap.kI, ConstantsMap.kD, ConstantsMap.kF, driveSubsystem.getGyro(), pidOutput);
    	straightController.setOutputRange(-1,1);
    	straightController.setInputRange(-90, 90);
    	straightController.setSetpoint(0);
    	straightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	while(driveSubsystem.getRightEncoderDistance() < inchesToDrive || !oi.xbox.GetRightBumperValue()){
    		//Get PIDController output
    		double pidOutputValue = straightController.get();
    		
    		//adjust speed of each wheel
    		driveSubsystem.tankDrive(speed - pidOutputValue, speed + pidOutputValue);
    	}
    	//Disable controller and end command
    	straightController.disable();
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
