package org.usfirst.frc.team2022.command.autonomous;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CustomPIDController {

	private double kp, ki, kd, kf = 0;
	
	private double inputMin = -1;
	private double inputMax = 1;
	private double outputMin = -1;
	private double outputMax = 1;
	
	private double setpoint = 0;
	
	private double lastInput = 0;
	private double error = 0;
	private double lastError = 0;
	private double totalError = 0;
	private double output = 0;
	private double absoluteTolerance = 0;
	
	
	public CustomPIDController(double kp, double ki, double kd, double kf){
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kf = kf;
	}
	
	public void setP(double kp){
		this.kp = kp;
	}
	
	public void setI(double ki){
		this.ki = ki;
	}
	public void setD(double kd){
		this.kd = kd;
	}
	public void setF(double kf){
		this.kf = kf;
	}
	public double getOutput(double input){
		lastInput = input;
		error = setpoint - input;
		
		if(error * kp < outputMax && error * kp > outputMin){
			totalError += error;
		}
		else{
			totalError = 0;
		}
		
		output = (kp * error + ki * totalError + kd * (error - lastError)) + kf;
		lastError = error;
		
		if(output > outputMax){
			output = outputMax;
		}
		else if(output < outputMin){
			output = outputMin;
		}
		
		return output;
		
	}
	
	public void setSetpoint(double setpoint){
		this.setpoint = setpoint;
	}
	
	public void setInputRange(double min, double max){
		inputMin = min;
		inputMax = max;
	}
	
	public void setOutputRange(double min, double max){
		outputMin = min;
		outputMax = max;
	}
	
	public void setAbsoluteTolerance(double absoluteTolerance){
		this.absoluteTolerance = absoluteTolerance;
	}
	
	public boolean onTarget(){
		
		if(Math.abs(lastInput - setpoint) < absoluteTolerance){
			return true;
		}
		else{
			return false;
		}
	}
	
}
