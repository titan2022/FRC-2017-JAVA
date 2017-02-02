package org.usfirst.frc.team2022.command;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearCommand extends CommandGroup {
	//Create Reference to Network Table
	NetworkTable table;
	
	public AutoGearCommand(int position){	
		table = NetworkTable.getTable("SmartDashboard");
		
		// position 1 is from the right side 
		// position 2 is from the middle 
		// position 3 is from the left side 
		
		if(position == 1){
			command(220, -135); 
		}		
		else if(position == 2){
			command(50, 0);
		}
		else if(position == 3){
			command(220, 135); 
		}
	}
	
	public void command(double distance, double turn){
		double distanceStraight = SmartDashboard.getNumber("Distance Straight", distance);
		double angleTurn = SmartDashboard.getNumber("Angle Turn", turn);
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
