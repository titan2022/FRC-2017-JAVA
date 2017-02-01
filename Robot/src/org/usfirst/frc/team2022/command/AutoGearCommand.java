package org.usfirst.frc.team2022.command;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearCommand extends CommandGroup {
	//Create Reference to Network Table
	NetworkTable table;
	
	public AutoGearCommand(){
		table = NetworkTable.getTable("GRIP/myCountoursReport");
		
		double distanceStraight = SmartDashboard.getNumber("Distance Straight", 114.3);
		double angleTurn = SmartDashboard.getNumber("Angle Turn", 45);
		double speed = SmartDashboard.getNumber("Speed", 0.5);
		
		addSequential(new AutoDriveStraight(distanceStraight, speed));
		addSequential(new AutoDriveTurn(angleTurn, speed));
		
		Timer.delay(1);
    	double pegDistance = table.getNumber("pegDistance", 0);
    	double pegAngle = table.getNumber("pegAngle", 0);
   		System.out.println("Distance: " + pegDistance + " Angle: " + pegAngle);
   		
   		addSequential(new AutoDriveTurn(pegAngle, speed));
   		addSequential(new AutoDriveStraight(pegDistance, speed));		
	}
}
