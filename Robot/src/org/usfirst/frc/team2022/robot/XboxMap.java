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
	
//	public boolean startAutoBrakerSystem(){
//		return oi.xbox.GetRightBumperValue();
//	}
	
	public boolean switchySwitch(){
		return oi.xbox.GetRightBumperValue();
	}
	
	public boolean startAutoGearAlignment(){
		if(oi.xbox.getPOV() == 180){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//Shooter commands	
	public boolean alignShooter(){
		return oi.xbox.GetBValue();
	}
	
	public boolean startManualShooterCommand(){
		return oi.xbox.GetXValue();
	}

//	public boolean openGate() {
//		return oi.xbox.GetBackValue();
//	}
	
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

	public boolean runAgitator() {
		// TODO Auto-generated method stub
		if(oi.xbox.GetRightTriggers() > 0.4){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean moveTowardsGear() {
		// TODO Auto-generated method stub
		return oi.xbox.GetAValue();
//		if(oi.xbox.getPOV() == 0){
//			return true;
//		}
//		else{
//			return false;
//		}
	}

	public boolean moveToShooter() {
		// TODO Auto-generated method stub
		return oi.xbox.GetAValue();
	}
}


