package org.usfirst.frc.team2022.robot;

public class XboxMap {
	OI oi = Robot.oi;
	
	//Drive commands
	public double getSpeedRightWheel(){ 
		return oi.xbox.GetRightY();
	}
	
	public double getSpeedLeftWheel() {
		return oi.xbox.GetLeftY();
	}
	
	public boolean startAutoBrakerSystem(){
		return oi.xbox.GetRightBumperValue();
	}
	
	public boolean switchySwitch(){
		return oi.xbox.GetLeftBumperValue();
	}
	
	public boolean startAutoGearPlacement(){
		return oi.xbox.GetXValue();
	}
	
	
	//Shooter commands	
	public boolean startAutoShooterSystem(){
		return oi.xbox.GetAValue();
	}
	
	public boolean startManualShooterCommand(){
		return oi.xbox.GetBValue();
	}

	public boolean openGate() {
		return oi.xbox.GetBackValue();
	}
	
	//Climber commands
	public double getManualClimberSpeed(){
		return oi.xbox.GetLeftTriggers();
	}
	
	
	//Camera command
	public boolean switchCamera(){
		return oi.xbox.GetStartValue();
	}
	
	
	//Universal stop command
	public boolean stopSystem() {
		return oi.xbox.GetYValue();
	}
}


