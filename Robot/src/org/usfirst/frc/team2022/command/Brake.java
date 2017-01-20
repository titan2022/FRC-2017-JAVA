package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.RobotMap;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class Brake extends Command{
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	boolean brake = false;
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void execute() {
		if(!brake){
			driveSubsystem.getLeft1().enableBrakeMode(true);
			driveSubsystem.getLeft2().enableBrakeMode(true);
			driveSubsystem.getRight1().enableBrakeMode(true);
			driveSubsystem.getRight2().enableBrakeMode(true);
			brake = !brake;
		}
		else{
			driveSubsystem.getLeft1().enableBrakeMode(false);
			driveSubsystem.getLeft2().enableBrakeMode(false);
			driveSubsystem.getRight1().enableBrakeMode(false);
			driveSubsystem.getRight2().enableBrakeMode(false);
			brake = !brake;
		}
	}
	
}
