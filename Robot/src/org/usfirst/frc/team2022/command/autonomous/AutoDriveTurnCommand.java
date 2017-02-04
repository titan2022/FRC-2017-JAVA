package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveTurnCommand extends Command implements PIDOutput{
	
	private boolean finished = false;
	private double degreeToTurn = 0;
	private double speed = 0;
	double outputSpeed = 0;
	
	//PID Objects
	PIDController turnController;
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

    public AutoDriveTurnCommand(double degreeToTurn, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveSubsystem);
    	this.degreeToTurn = degreeToTurn;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Reset gyro so reading is 0
    	driveSubsystem.resetGyro();
    	
    	//Create PIDController and enable
    	turnController = new PIDController(ConstantsMap.KP_SHOOTER_SPEED, ConstantsMap.KI_SHOOTER_SPEED, ConstantsMap.KD_SHOOTER_SPEED, ConstantsMap.KF_SHOOTER_SPEED, driveSubsystem.getGyro(), this);
    	turnController.setOutputRange(-speed, speed);
    	turnController.setContinuous(true);
    	turnController.setInputRange(-360, 360);
    	turnController.setAbsoluteTolerance(0.05);
    	turnController.setSetpoint(degreeToTurn);
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * While the right encoder distance is less than inchesToDrive
    	 * and xbox right bumper is not pressed, use output to drive straight
    	 */
    		
    		//adjust speed of each wheel
    		driveSubsystem.setLeftSpeed(-outputSpeed);
    		driveSubsystem.setRightSpeed(-outputSpeed);
//    		driveSubsystem.tankDrive(pidOutputValue, -pidOutputValue);
    		if(turnController.onTarget() || xboxMap.stopSystem()){
    			finished = true;
    			end();
    			cancel();
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
    
    public void pidWrite(double output) {
		// TODO Auto-generated method stub
		outputSpeed = output;
	}
}
