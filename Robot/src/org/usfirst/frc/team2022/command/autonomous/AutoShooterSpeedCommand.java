package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//parameter of speed motor (feet/second)
public class AutoShooterSpeedCommand extends Command implements PIDOutput{
	
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double speed = 0; 
	double desiredRate = 0;
	boolean isFinished = false;
	double outputSpeed = 0;
	
	CustomPIDController pidController;
	
	public AutoShooterSpeedCommand(double desiredRate){
		requires(shooterSubsystem);
		this.desiredRate = desiredRate;
		NetworkTable sd = NetworkTable.getTable("Preferences");
		pidController = new CustomPIDController(sd.getNumber("P", ConstantsMap.KP_SHOOTER_SPEED), sd.getNumber("I", ConstantsMap.KI_SHOOTER_SPEED), sd.getNumber("D", ConstantsMap.KD_SHOOTER_SPEED));
		pidController.setInputRange(0, 35000);
    	pidController.setAbsoluteTolerance(0.0001);
    	pidController.setOutputRange(0, 1);
    	pidController.setSetpoint(0);
	}

	protected void initialize() {
		
	}
	
	protected void execute() {
//		shooterSubsystem.activationServo();
		double speed = pidController.getOutput(shooterSubsystem.getShooterEncoderRate());
    	shooterSubsystem.setShooterSpeed(speed);
    	SmartDashboard.putNumber("Shooter Speed", speed);
    	SmartDashboard.putNumber("Encoder Encoder Rate", shooterSubsystem.getShooterEncoderRate());
    	if(xboxMap.stopSystem()){
    		end();
    		cancel();
    	}
    	//90
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		outputSpeed = output;
	}
}
