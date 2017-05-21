package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ArmCommand extends Command{

	ArmSubsystem armSubsystem = Robot.armSubsystem;
	
	XboxMap xboxMap = new XboxMap();
	OI oi = Robot.oi;
	
	public ArmCommand() {
    	requires(armSubsystem);
    }

	protected void initialize(){
		
	}
	  
	protected void execute(){ 
		
		if(xboxMap.BottomArmUp()){
			armSubsystem.moveUp(true);
		}
		else if(xboxMap.BottomArmDown()){
			armSubsystem.moveDown(true);
		}
		else if(xboxMap.MiddleArmUp()){
			armSubsystem.moveUp(false);
		}
		else if(xboxMap.MiddleArmDown()){
			armSubsystem.moveDown(false);
		}
	}	
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
