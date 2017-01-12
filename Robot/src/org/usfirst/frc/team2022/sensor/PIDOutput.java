package org.usfirst.frc.team2022.sensor;

import org.usfirst.frc.team2022.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDOutput {
	double output = 0;
		
	public void pidWrite(double output){
		this.output = output;
	}
	
	public double getOutput(){
		return output;
	}
	
}
	

