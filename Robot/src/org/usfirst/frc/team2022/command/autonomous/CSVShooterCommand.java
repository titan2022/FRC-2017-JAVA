package org.usfirst.frc.team2022.command.autonomous;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

//parameter of speed motor (feet/second)
public class CSVShooterCommand extends Command{
	
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	OI oi = Robot.oi;
	XboxMap xboxMap = new XboxMap();

	double speed = 0; 
	double desiredRate = 0;
	boolean isFinished = false;
	double outputSpeed = 0;
	
	long lastPressed = 0;
	
	CustomPIDController pidController;
	
	NetworkTable sd = NetworkTable.getTable("Preferences");
	
	PrintWriter pw;
	
	long firstTime;
	
	public CSVShooterCommand(){
		requires(shooterSubsystem);
    	
	}

	protected void initialize() {
    	try {
			pw = new PrintWriter(new File("/home/lvuser/" + sd.getString("FileName", "DefaultName") + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	firstTime = System.currentTimeMillis();
    	StringBuilder sb = new StringBuilder();
		sb.append("Time");
		sb.append(',');
		sb.append("Encoder Rate");
		sb.append(',');
		sb.append("Shooter Speed");
		sb.append("\n");
		pw.write(sb.toString());
	}
	
	protected void execute() {
		
		shooterSubsystem.setShooterSpeed(1);
		StringBuilder sb = new StringBuilder();
		sb.append(Long.toString((System.currentTimeMillis() - firstTime)));
		sb.append(',');
		sb.append(Double.toString(shooterSubsystem.getShooterEncoderRate()));
		sb.append(',');
		sb.append(Double.toString(shooterSubsystem.getShooterSpeed()));
		sb.append("\n");
		
		pw.write(sb.toString());

    	if(xboxMap.stopSystem()){
    		pw.close();
    		isFinished = true;
    		end();
    		cancel();
    	}

    }

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
