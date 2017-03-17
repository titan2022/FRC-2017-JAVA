package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveTurnCommand extends Command{
	
	private boolean finished = false;
	private double degreeToTurn = 0;
	double outputSpeed = 0;
	

	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();
	
	CustomPIDController pidController;

    public AutoDriveTurnCommand(double degreeToTurn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.degreeToTurn = degreeToTurn;

    	driveSubsystem.resetGyro();
    	driveSubsystem.enableBrake();

    	pidController = new CustomPIDController(ConstantsMap.KP_DRIVE_TURN, ConstantsMap.KI_DRIVE_TURN, ConstantsMap.KD_DRIVE_TURN, ConstantsMap.KF_DRIVE_TURN);
    	pidController.setInputRange(-180, 180);
    	pidController.setAbsoluteTolerance(1);
    	pidController.setOutputRange(-ConstantsMap.KSPEED_DRIVE_TURN, ConstantsMap.KSPEED_DRIVE_TURN);
    	pidController.setSetpoint(degreeToTurn);
    	System.out.println("Inside turn autonomous command");

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    	
    		//adjust speed of each wheel

    	double newSpeed = pidController.getOutput(driveSubsystem.getGyroAngle());
		driveSubsystem.setLeftSpeed(-newSpeed);
		driveSubsystem.setRightSpeed(-newSpeed);
		if(xboxMap.stopSystem()){
			finished = true;
    		cancel();
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
