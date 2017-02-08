package org.usfirst.frc.team2022.robot;

public class XboxMap {
	OI oi = Robot.oi;
	
	public double getSpeedRightWheel(){ 
		return oi.xbox.GetRightY();
	}
	
	public double getSpeedLeftWheel() {
		return oi.xbox.GetLeftY();
	}
	
	public boolean startAutoGearPlacement(){
		return oi.xbox.GetXValue();
	}
	
	public boolean startAutoBrakerSystem(){
		return oi.xbox.GetRightBumperValue();
	}
	
	public double getManualShooterSpeed(){
		return oi.xbox.GetRightTriggers();
	}
	
	public boolean startAutoShooterSystem(){
		return oi.xbox.GetAValue();
	}
	
	public double getManualClimberSpeed(){
		return oi.xbox.GetLeftTriggers();
	}
	
	public boolean startAutoClimberSystem(){
		return oi.xbox.GetLeftBumperValue();
	}

	public boolean stopSystem() {
		return oi.xbox.GetYValue();
	}

	public boolean openGate() {
		return oi.xbox.GetBValue();
	}
	
	public boolean switchCamera(){
		if(oi.xbox.getPOV() == 0){
			return true;
		}
		else{
			return false;
		}
	}
}


