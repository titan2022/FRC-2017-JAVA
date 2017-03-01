package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//parameter of speed motor (feet/second)
public class BangBangShooterCommand extends Command{
	
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double desiredRate;
	boolean isFinished = false;
	NetworkTable sd = NetworkTable.getTable("Preferences");

	
	public BangBangShooterCommand(double desiredRate){
		requires(shooterSubsystem);
		this.desiredRate = desiredRate;
		
	}

	protected void initialize() {

	}
	
	protected void execute() {
		//Set speed from pid controller
		double speed;
		if(shooterSubsystem.getShooterEncoderRate() < desiredRate){
			speed = 1;
		}
		else{
			speed = 0;
		}
    	shooterSubsystem.setShooterSpeed(speed);
    	
    	
    	
    	SmartDashboard.putNumber("Shooter Speed", speed);
    	SmartDashboard.putNumber("Vision", NetworkTable.getTable("SmartDashboard").getNumber("boilerAngle", 100));
    	SmartDashboard.putNumber("Voltage", shooterSubsystem.getVoltage());
    	SmartDashboard.putNumber("Encoder Encoder Rate", shooterSubsystem.getShooterEncoderRate());

    	if(xboxMap.stopSystem()){
//    		pw.close();
    		shooterSubsystem.stop();
    		cancel();
    		end();
    	}
    	//90
    	//44.5 inches to shoot
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
