package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearCommandGroup extends CommandGroup {
	//Create Reference to Network Table
	NetworkTable table;
	
	public AutoGearCommandGroup(){
		table = NetworkTable.getTable("SmartDashboard");
		
		double speed = 0.5;
		
		Timer.delay(1);
    	double pegDistance = table.getNumber("pegDistance", 0);
    	double pegAngle = table.getNumber("pegAngle", 0);
		  		
  		addSequential(new AutoDriveTurnCommand(pegAngle, speed));
  		addSequential(new AutoDriveStraightCommand(pegDistance, speed));
	}
	
	public AutoGearCommandGroup(int position){	
		table = NetworkTable.getTable("SmartDashboard");
		
		// position 1 is from the right side 
		// position 2 is from the middle 
		// position 3 is from the left side 
		
		if(position == 1){
			run(220, -135); 
		}	
		else if(position == 2){
			run(50, 0);
		}
		else if(position == 3){
			run(220, 135); 
		}
	}
	
	public void run(double distance, double angle){
		
				
		addSequential(new AutoDriveStraightCommand(distance, 0.5));
		addSequential(new AutoDriveTurnCommand(angle, 0.5));
				
		Timer.delay(1);
    	double pegDistance = table.getNumber("pegDistance", 0);
    	double pegAngle = table.getNumber("pegAngle", 0);
		  		
  		addSequential(new AutoDriveTurnCommand(pegAngle, 0.5));
  		addSequential(new AutoDriveStraightCommand(pegDistance, 0.5));
	}
}
