package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;
import org.usfirst.frc.team2022.subsystem.LightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LightCommand extends Command{
//	LightSubsystem lights = Robot.lights;
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	int selection;
	public LightCommand(int input){
//		requires(lights);
		selection = input;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void end(){
//		lights.Off();
	}
	protected void execute(){
//		lights.White();
//		if(selection == 0){
//			lights.Off();
//		}
//		else if(selection == 1){
//			lights.White();
//		}
	}
}
