package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.command.autonomous.AutoShooterSpeedCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class UltrasonicCommand extends Command{

	NetworkTable table; 
	
	private AnalogInput ultrasonic;
	
	public UltrasonicCommand(){
		table = NetworkTable.getTable("SmartDashboard");
		
		//Instantiate Ultrasonic 
		ultrasonic = new AnalogInput(RobotMap.K_ULTRASONIC_PORT);	
	}
	
	protected void initialize() {
		
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	addData();
   	}

	public void addData(){
		table.putNumber("Ultrasonic Distance", getAverageRangeInInches());
	}
	
	public double getVoltage(){
		return ultrasonic.getVoltage();
	}
	
	public double getAverageVoltage(){
		return ultrasonic.getAverageVoltage();
	}
	
	public double getRangeInInches(){
		return (getVoltage() / 0.0247904);
	}
	
	public double getAverageRangeInInches(){
		return (getAverageVoltage() / 0.0247904);
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
