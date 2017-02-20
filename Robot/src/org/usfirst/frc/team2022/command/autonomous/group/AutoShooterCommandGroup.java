package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoShooterSpeedCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class AutoShooterCommandGroup extends CommandGroup {

	NetworkTable table;
	
    public AutoShooterCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	table = NetworkTable.getTable("SmartDashboard");
		
		Timer.delay(1);
    	double boilerDistance = table.getNumber("UltrasonicDistance", 0);
    	double boilerAngle = table.getNumber("boilerAngle", 0);
		  		
  		addSequential(new AutoDriveTurnCommand(boilerAngle));
  		
  		Timer.delay(0.2);
  		boilerDistance = table.getNumber("boilerDistance", 0);
  		addSequential(new AutoDriveStraightCommand(boilerDistance - ConstantsMap.DIST_TO_SHOOT));
  		
  		AutoShooterSpeedCommand autoShooterSpeedCommand = new AutoShooterSpeedCommand(ConstantsMap.SHOOTING_SPEED);
  		autoShooterSpeedCommand.start();
    	
    }
}
