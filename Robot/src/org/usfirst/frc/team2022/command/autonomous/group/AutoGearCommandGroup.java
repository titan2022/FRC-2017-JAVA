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
		
		VisionTable.setPegDone(false);
		VisionTable.setProcessPeg(true);
		Timer.delay(1);
		while(!VisionTable.getPegDone()){
			
		}
    	double pegDistance = VisionTable.getPegDistance();
    	double pegAngle = VisionTable.getPegAngle();
		System.out.println("Should not be here");
  		addSequential(new AutoDriveTurnCommand(pegAngle));
  		
  		Timer.delay(1);
  		pegDistance = VisionTable.getPegDistance();
  		addSequential(new AutoDriveStraightCommand(pegDistance));
  		
  		VisionTable.setPegDone(true);
  		VisionTable.setProcessPeg(false);
	}
	
	public AutoGearCommandGroup(int position){	
		
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
		System.out.println("Should not be here 4");
		VisionTable.setPegDone(false);
		VisionTable.setProcessPeg(true);
		addSequential(new AutoDriveStraightCommand(distance));
		addSequential(new AutoDriveTurnCommand(angle));
				
		Timer.delay(1);
		double pegDistance = VisionTable.getPegDistance();
    	double pegAngle = VisionTable.getPegAngle();
		  		
  		addSequential(new AutoDriveTurnCommand(pegAngle));
  		
  		Timer.delay(1);
  		pegDistance = VisionTable.getPegDistance();
  		addSequential(new AutoDriveStraightCommand(pegDistance));
  		VisionTable.setPegDone(true);
  		VisionTable.setProcessPeg(false);
	}
}
