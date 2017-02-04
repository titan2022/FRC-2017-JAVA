package org.usfirst.frc.team2022.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoStraightCommand extends CommandGroup{

	public AutoStraightCommand(){
		command(200, 0);
	}
	
	protected boolean isFinished() {
		return false;
	}

	public void command(double distance, double turn){
		double distanceStraight = SmartDashboard.getNumber("Distance Straight", distance);
		double angleTurn = SmartDashboard.getNumber("Angle Turn", turn);
		double speed = SmartDashboard.getNumber("Speed", 0.5);
				
		addSequential(new AutoDriveStraight(distanceStraight, speed));
		addSequential(new AutoDriveTurn(angleTurn, speed));
	}
}
