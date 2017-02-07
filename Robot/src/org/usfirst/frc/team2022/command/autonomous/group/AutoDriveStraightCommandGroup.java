package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveStraightCommandGroup extends CommandGroup {
	//Create Reference to Network Table
	
	public AutoDriveStraightCommandGroup(double distance, double speed){	
  		addSequential(new AutoDriveStraightCommand(distance, speed));
	}
	
}
