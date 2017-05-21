package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.ArmCommand;
import org.usfirst.frc.team2022.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmSubsystem extends Subsystem{
	
	private CANTalon bottomMotor;
	private CANTalon middleMotor;
	
	private static final double SPEED_UP = 0.8;
	private static final double SPEED_DOWN = -0.4;
	
	public ArmSubsystem(){
		bottomMotor = new CANTalon(RobotMap.BOTTOM_ARM);
		middleMotor = new CANTalon(RobotMap.MIDDLE_ARM);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArmCommand());
		bottomMotor.setSafetyEnabled(false);
		middleMotor.setSafetyEnabled(false);
	}
	
	public boolean bottomMotorAtTop(){
        return bottomMotor.isFwdLimitSwitchClosed();
    }
    public boolean bottomMotorAtBottom(){
        return bottomMotor.isRevLimitSwitchClosed();
    }
    public boolean middleMotorAtTop(){
        return middleMotor.isFwdLimitSwitchClosed();
    }
    public boolean middleMotorAtBottom(){
        return middleMotor.isRevLimitSwitchClosed();
    }
	
	public void moveUp(boolean choice){ //true moves the bottom motor and false moves the middle motor
		if(choice){
			bottomMotor.set(SPEED_UP);
			if(bottomMotorAtTop()){
				bottomMotor.set(0);
			}
		}
		if(!choice){
			middleMotor.set(SPEED_UP);
			if(middleMotorAtTop()){
				middleMotor.set(0);
			}
		}
	}
	
	public void moveDown(boolean choice){ //true moves the bottom motor and false moves the middle motor
		if(choice){
			bottomMotor.set(SPEED_DOWN);
			if(bottomMotorAtBottom()){
				bottomMotor.set(0);
			}
		}
		if(!choice){
			middleMotor.set(SPEED_DOWN);
			if(middleMotorAtBottom()){
				middleMotor.set(0);
			}
		}
	}
	
	public void stopBottomMotor(){
		bottomMotor.set(0);
	}
		
	public void stopMiddleMotor(){
		middleMotor.set(0);
	}
}
