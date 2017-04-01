package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
import org.usfirst.frc.team2022.command.autonomous.VisionTable;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearCommandGroup extends CommandGroup {
	//Create Reference to Network Table
	
	public AutoGearCommandGroup(){
		
		Timer.delay(1);
		
    	double pegDistance = VisionTable.getPegDistance();
    	double pegAngle = VisionTable.getPegAngle();
  		addSequential(new AutoDriveTurnCommand(pegAngle));
  		
  		Timer.delay(1);
  		pegDistance = VisionTable.getPegDistance();
  		addSequential(new AutoDriveStraightCommand(pegDistance));
	}
	
	public AutoGearCommandGroup(int position){	
		
		// position 1 is from the right side 
		// position 2 is from the middle 
		// position 3 is from the left side 
		
		//System.out.println("Position:" + position);
		if(position == 1){
			runCommand(94.745, -60, 63); 
		}	
		else if(position == 2){
			addSequential(new AutoDriveStraightCommand());
		}
		//7*12 + 11 - 27
		else if(position == 3){
			runCommand(83.879, 60, 88); 
		}
	}
	//11.7
	public void runCommand(double distance, double angle, double secondDistance){
		//System.out.println("run");
		addSequential(new AutoDriveStraightCommand(distance));
//		addSequential(new AutoDriveTurnCommand(angle));
				
//		Timer.delay(1);
//		double pegDistance = VisionTable.getPegDistance();
//    	double pegAngle = VisionTable.getPegAngle();
//		  		
////  		addSequential(new AutoDriveStraightCommand(secondDistance));
//  		
//  		Timer.delay(1);
//  		pegDistance = VisionTable.getPegDistance();
//  		addSequential(new AutoDriveStraightCommand(pegDistance));
	}
}
