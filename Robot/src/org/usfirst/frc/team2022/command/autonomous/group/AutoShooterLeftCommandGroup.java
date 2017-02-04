package org.usfirst.frc.team2022.command.autonomous.group;

import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
import org.usfirst.frc.team2022.robot.ConstantsMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShooterLeftCommandGroup extends CommandGroup {
    
    public  AutoShooterLeftCommandGroup() {
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
    	
    	addSequential(new AutoDriveStraightCommand(96 + ConstantsMap.ROBOT_LENGTH_INCHES, 1));
    	addSequential(new AutoDriveTurnCommand(180, 1));
    	addSequential(new AutoDriveStraightCommand(36 + ConstantsMap.ROBOT_LENGTH_INCHES, 1));
    	
    	
    	
    }
}
