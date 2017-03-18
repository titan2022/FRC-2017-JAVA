package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveStraightCommand extends Command{
	
	private boolean finished = false;
	private double inchesToDrive = 0;
	private double speed = 0;
	double rotateToAngleRate = 0;
	
	//PID Objects
	
	CustomPIDController pidController;
	CustomPIDController speedController;
	
	XboxMap xboxMap = new XboxMap();
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;

    public AutoDriveStraightCommand(double inchesToDrive) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.inchesToDrive = inchesToDrive;	
    	System.out.println("Inside autonomous command");
    	driveSubsystem.resetEncoders();
    	driveSubsystem.resetGyro();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Inside initialize command");

    	driveSubsystem.enableBrake();
    	
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	driveSubsystem.resetEncoders();
    	
    	//Create PIDController and enable
    	pidController = new CustomPIDController(ConstantsMap.KP_DRIVE_ANGLE, ConstantsMap.KI_DRIVE_ANGLE, ConstantsMap.KD_DRIVE_ANGLE, ConstantsMap.KF_DRIVE_ANGLE);
    	pidController.setInputRange(-180, 180);
    	pidController.setAbsoluteTolerance(1.5);
    	pidController.setOutputRange(-0.2, 0.2);
    	pidController.setSetpoint(0);    
    	
    	speedController = new CustomPIDController(ConstantsMap.KP_DRIVE_SPEED, ConstantsMap.KI_DRIVE_SPEED, ConstantsMap.KD_DRIVE_SPEED, ConstantsMap.KF_DRIVE_SPEED);
    	speedController.setInputRange(-1.5 * inchesToDrive, 1.5 * inchesToDrive);
    	speedController.setAbsoluteTolerance(0.1);
    	speedController.setOutputRange(-ConstantsMap.KSPEED_DRIVE_SPEED, ConstantsMap.KSPEED_DRIVE_SPEED);
    	speedController.setSetpoint(inchesToDrive);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Executing");

    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	rotateToAngleRate = pidController.getOutput(driveSubsystem.getGyroAngle());
		
        SmartDashboard.putNumber("Gyro Angle", driveSubsystem.getGyroAngle());
		
		speed = speedController.getOutput(driveSubsystem.getRightEncoderDistance());
        
		//adjust speed of each wheel
//		if(inchesToDrive == -5){
//			driveSubsystem.tankDrive(0.2, -0.2);
//			if(driveSubsystem.getRightEncoderDistance() > 4.8){
//				driveSubsystem.stop();
//				finished = true;
//				end();
//			}
//		}
//		else{
		driveSubsystem.tankDrive(-0.5 * (speed + rotateToAngleRate), 0.5 * (speed - rotateToAngleRate));
//		}
		if(speedController.onTarget() || oi.xbox.GetBValue()){
			finished = true;
			end();
		}

    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
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
