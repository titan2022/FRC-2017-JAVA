package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoShooterSpeedCommand;
import org.usfirst.frc.team2022.command.autonomous.ChangeServoCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoNewShooterCommandGroup extends CommandGroup {

    public AutoNewShooterCommandGroup(String side) {
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
    	
    	if(side.equals("Red")){
    		addSequential(new AutoDriveStraightCommand(70));
    		addParallel(new AutoShooterSpeedCommand(ConstantsMap.SHOOTING_SPEED));
    		addSequential(new AutoDriveStraightCommand(-(70 - ConstantsMap.RED_DIST_TO_SHOOT)));
    		addSequential(new ChangeServoCommand("open"));
    		
    	}
    	else{
    		addSequential(new AutoDriveStraightCommand(70));
    		addParallel(new AutoShooterSpeedCommand(ConstantsMap.SHOOTING_SPEED));
    		addSequential(new AutoDriveStraightCommand(-(70 - ConstantsMap.BLUE_DIST_TO_SHOOT)));
    		addSequential(new AutoDriveTurnCommand(4));
    		addSequential(new ChangeServoCommand("open"));
    	}
    	
    }
}
