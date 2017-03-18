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
public class AutoShooterSpeedCommand extends Command{
	
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double speed = 0; 
	double desiredRate = 0;
	boolean isFinished = false;
	double outputSpeed = 0;
	
	long lastPressed = 0;
	
	CustomPIDController pidController;
	NetworkTable sd;
	long firstTime;
	boolean first = false;
	
	public AutoShooterSpeedCommand(double desiredRate){
		requires(shooterSubsystem);
		this.desiredRate = desiredRate;
		
		sd = NetworkTable.getTable("Preferences");
//    	pidController = new CustomPIDController(ConstantsMap.KP_SHOOTER_SPEED, ConstantsMap.KI_SHOOTER_SPEED, ConstantsMap.KD_SHOOTER_SPEED, ConstantsMap.KF_SHOOTER_SPEED);
    	pidController = new CustomPIDController(sd.getNumber("P", ConstantsMap.KP_SHOOTER_SPEED), sd.getNumber("P", ConstantsMap.KI_SHOOTER_SPEED), sd.getNumber("P", ConstantsMap.KD_SHOOTER_SPEED), sd.getNumber("P", ConstantsMap.KF_SHOOTER_SPEED));
    	pidController.setInputRange(0, 6000);
    	pidController.setOutputRange(0, 1);
    	pidController.setAbsoluteTolerance(100);
    	pidController.setSetpoint(desiredRate);
    	firstTime = System.currentTimeMillis();
    	
	}

	protected void initialize() {
	}
	
	protected void execute() {
		//Set speed from pid controller
		desiredRate = sd.getNumber("Rate", 0);
		pidController.setSetpoint(desiredRate);
		pidController.setP(sd.getNumber("P", ConstantsMap.KP_SHOOTER_SPEED));
		pidController.setI(sd.getNumber("I", ConstantsMap.KI_SHOOTER_SPEED));
		pidController.setD(sd.getNumber("D", ConstantsMap.KD_SHOOTER_SPEED));
		pidController.setF(sd.getNumber("F", ConstantsMap.KF_SHOOTER_SPEED));
		if(first == false){
			firstTime = System.currentTimeMillis();
			first = true;
		}
		if(System.currentTimeMillis() - firstTime > 2000){
			shooterSubsystem.setServo(1);
		}
		SmartDashboard.putNumber("Shooter Encoder Rate", shooterSubsystem.getShooterSpeed());
		SmartDashboard.putNumber("Voltage", shooterSubsystem.getVoltage());
		
		double speed = pidController.getOutput(shooterSubsystem.getShooterSpeed());
		System.out.println("speed:" + speed + " Shooter Speed: " + shooterSubsystem.getShooterSpeed());
    	shooterSubsystem.setShooterSpeed(speed);
    	
//    	Activate agitator
//		if(xboxMap.runAgitator()){
//    		shooterSubsystem.setAgitatorSpeed(0.4);
//    	}
//    	else{
//    		shooterSubsystem.setAgitatorSpeed(0);
//    	}
	    

    	if(xboxMap.stopSystem()){
    		shooterSubsystem.stop();
    		isFinished = true;
    		end();
    	}
    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
