//package org.usfirst.frc.team2022.command.autonomous.group;
//
//import org.usfirst.frc.team2022.command.autonomous.AutoDriveStraightCommand;
//import org.usfirst.frc.team2022.command.autonomous.AutoDriveTurnCommand;
//import org.usfirst.frc.team2022.command.autonomous.AutoShooterSpeedCommand;
//import org.usfirst.frc.team2022.robot.ConstantsMap;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class AutoShooterRightCommandGroup extends CommandGroup {
//	
//	private double degToTurn = -(180 - Math.atan(243/96));
//	private double distToShootPos = Math.sqrt(Math.pow(162, 2) + Math.pow(96, 2)) - 
//			ConstantsMap.DIST_TO_SHOOT;
//    
//    public  AutoShooterRightCommandGroup() {
//        // Add Commands here:
//        // e.g. addSequential(new Command1());
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//
//        // A command group will require all of the subsystems that each member
//        // would require.
//        // e.g. if Command1 requires chassis, and Command2 requires arm,
//        // a CommandGroup containing them would require both the chassis and the
//        // arm.
//    	addSequential(new AutoDriveStraightCommand(96));
//    	addSequential(new AutoDriveTurnCommand(degToTurn));
//    	addSequential(new AutoDriveStraightCommand(distToShootPos));
//    	
//    	addSequential(new AutoShooterSpeedCommand(ConstantsMap.SHOOTING_SPEED));
//    	
//    }
//}
