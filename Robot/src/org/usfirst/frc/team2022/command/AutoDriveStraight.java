package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.sensor.DummyPIDOutput;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public abstract class AutoDriveStraight extends Command {
	
	private boolean finished = false;
	private double inchesToDrive = 0;
	private double speed = 0;
	
	//PID Objects
		PIDController straightController;
		DummyPIDOutput pidOutput;
		
	//References to objects in Robot
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	OI oi = Robot.oi;
	
	}
	
}
