package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveStraightCommand extends Command implements PIDOutput{
	
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

    public AutoDriveStraightCommand(double inchesToDrive, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.inchesToDrive = inchesToDrive;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	driveSubsystem.enableBrake();
    	
    	pidOutput = new DummyPIDOutput();
    	
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	driveSubsystem.resetEncoders();
    	
    	//Create PIDController and enable
    	straightController = new PIDController(ConstantsMap.KP_DRIVE_ANGLE, ConstantsMap.KI_DRIVE_ANGLE, ConstantsMap.KD_DRIVE_ANGLE, ConstantsMap.KF_DRIVE_ANGLE, driveSubsystem.getGyro(), this);
    	straightController.setOutputRange(-0.1, 0.1);
    	straightController.setInputRange(-180, 180);
    	straightController.setAbsoluteTolerance(0);
    	straightController.setContinuous(true);
    	straightController.setSetpoint(0);
    	straightController.enable();
    	
    	speedController = new PIDController(ConstantsMap.KP_DRIVE_SPEED, ConstantsMap.KI_DRIVE_SPEED, ConstantsMap.KD_DRIVE_SPEED, ConstantsMap.KF_DRIVE_SPEED, driveSubsystem.getRightEncoder(), pidOutput);
    	speedController.setOutputRange(-0.5, 0.5);
    	speedController.setInputRange(-inchesToDrive - 5, inchesToDrive + 5);
    	speedController.setAbsoluteTolerance(1);
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
        int directionFactor = ((inchesToDrive - driveSubsystem.getRightEncoderDistance())>=0) ? 1 : -1;
		
        //adjust speed of each wheel
		driveSubsystem.tankDrive(-(directionFactor*speedController.get() + rotateToAngleRate), directionFactor*speedController.get()- rotateToAngleRate);
		if(speedController.onTarget()){
			finished = true;
			end();
			cancel();
		}

    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
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

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		rotateToAngleRate = output;
	}
}
