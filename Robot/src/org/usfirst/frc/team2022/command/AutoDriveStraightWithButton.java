package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class AutoDriveStraightWithButton extends Command implements PIDOutput {
	
	private boolean finished = false;
	private double inchesToDrive = 0;
	private double speed = 0;
	double rotateToAngleRate = 0;
	
	//PID Objects
	PIDController straightController;
	PIDController speedController;
	DummyPIDOutput pidOutput;
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;

    public AutoDriveStraightWithButton(double inchesToDrive, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.inchesToDrive = inchesToDrive;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pidOutput = new DummyPIDOutput();
    	
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	driveSubsystem.resetEncoders();
    	
    	//Create PIDController and enable
    	double kP = SmartDashboard.getNumber("P", 0.3);
    	double kD = SmartDashboard.getNumber("D", 0.05);
    	double kI = SmartDashboard.getNumber("I", 0.1);
    	straightController = new PIDController(kP, kI, kD, ConstantsMap.kF, driveSubsystem.getGyro(), this);
    	straightController.setOutputRange(-0.1, 0.1);
    	straightController.setInputRange(-180, 180);
    	straightController.setAbsoluteTolerance(0);
    	straightController.setContinuous(true);
    	straightController.setSetpoint(0);
    	straightController.enable();
    	
    	double dP	 = SmartDashboard.getNumber("dP", 0.3);
    	double dD = SmartDashboard.getNumber("dD", 0.05);
    	double dI = SmartDashboard.getNumber("dI", 0.1);
    	speedController = new PIDController(dP, dI, dD, ConstantsMap.kF, driveSubsystem.getRightEncoder(), pidOutput);
    	speedController.setOutputRange(-0.25, 0.25);
    	speedController.setInputRange(-inchesToDrive - 5, inchesToDrive + 5);
    	speedController.setAbsoluteTolerance(0.5);
    	speedController.setContinuous(false);
    	speedController.setSetpoint(inchesToDrive);
    	speedController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	if (oi.xbox.GetAValue()) {
	        int directionFactor = ((inchesToDrive - driveSubsystem.getRightEncoderDistance())>=0) ? 1 : -1;
			//Get PIDController output
			SmartDashboard.putNumber("Drive speed", pidOutput.getOutput());
			//adjust speed of each wheel
			driveSubsystem.tankDrive(-(directionFactor*speedController.get() + rotateToAngleRate), directionFactor*speedController.get()- rotateToAngleRate);
			if(driveSubsystem.getRightEncoderDistance() > inchesToDrive){
				finished = true;
			}
    	} else if (oi.xbox.GetBValue()) {
    		end();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	driveSubsystem.disableBrakeMode();
    	straightController.disable();
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public void pidWrite(double output) {
		// TODO Auto-generated method stub
		rotateToAngleRate = output;
	}
}
