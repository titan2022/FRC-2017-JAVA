package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import com.sun.javafx.geom.transform.BaseTransform.Degree;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public abstract class AutoDriveTurn extends Command {
	
	private boolean finished = false;
	private double degreeToTurn = 0;
	private double speed = 0;
	
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
    	turnController = new PIDController(ConstantsMap.kP, ConstantsMap.kI, ConstantsMap.kD, ConstantsMap.kF, driveSubsystem.getGyro(), pidOutput);
    	turnController.setOutputRange(-1,1);
    	turnController.setInputRange(-90, 90);
    	turnController.setSetpoint(degreeToTurn);
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	while(driveSubsystem.getGyro().getAngle() < degreeToTurn || !oi.xbox.GetRightBumperValue()){
    		//Get PIDController output
    		double pidOutputValue = turnController.get();
    		
    		//adjust speed of each wheel
    		driveSubsystem.tankDrive(speed, (-speed));
    	}
    	//Disable controller and end command
    	turnController.disable();
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
