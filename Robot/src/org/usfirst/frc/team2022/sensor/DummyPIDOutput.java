package org.usfirst.frc.team2022.sensor;
 
import edu.wpi.first.wpilibj.PIDOutput;

/*
 * This acts as a stand in for the PIDOutput parameter in
 * PIDControllers 
 * */

public class DummyPIDOutput implements PIDOutput{

	private double output;
		
	public DummyPIDOutput(){
		output = 0;
	}
	
	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
	
	public double getOutput(){
		return output;
	}
} 