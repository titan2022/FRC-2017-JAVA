
package org.usfirst.frc.team2022.robot;

import org.usfirst.frc.team2022.command.ClimberCommand;
import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.command.ShooterCommand;
import org.usfirst.frc.team2022.command.UltrasonicCommand;
import org.usfirst.frc.team2022.command.autonomous.group.AutoGearCommandGroup;
import org.usfirst.frc.team2022.command.autonomous.group.AutoShooterCenterCommandGroup;
import org.usfirst.frc.team2022.command.autonomous.group.AutoShooterLeftCommandGroup;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Instantiate Subsystems
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	
	//Create References to commands
	public DriveCommand driveCommand;
	public ShooterCommand shooterCommand;
	public ClimberCommand climberCommand;
	private UltrasonicCommand ultrasonicCommand;
	
	//Autonomous
	CommandGroup autonomousCommand;

	SendableChooser autoTypeChooser;
	SendableChooser autoShooterChooser;
	SendableChooser autoGearChooser;
	double position;
	double gear;
	
	CameraServer server;
	
	//Create reference to OI
	public static OI oi;
	
	//Initialization code ran when you turn on the robot

    public void robotInit() {    	

    	//Instantiate OI
    	oi = new OI();
    	
    	//Instantiate Commands
    	driveCommand = new DriveCommand();
    	shooterCommand = new ShooterCommand();
    	climberCommand = new ClimberCommand();
    	ultrasonicCommand = new UltrasonicCommand();
    	
    	server = CameraServer.getInstance();
    	server.startAutomaticCapture("cam0", "cam0");
    	
    	
    	autoTypeChooser = new SendableChooser();
    	autoTypeChooser.addDefault("Gear Autonomous", "Gear");
    	autoTypeChooser.addObject("Shooter Autonomous", "Shooter");
    	SmartDashboard.putData("Autonomous Mode", autoTypeChooser);
    	
    	autoGearChooser = new SendableChooser();
    	autoGearChooser.addDefault("Position Gear 1 (Right)", new AutoGearCommandGroup(1));
    	autoGearChooser.addObject("Position Gear 2 (Middle)", new AutoGearCommandGroup(2));
    	autoGearChooser.addObject("Position Gear 3 (Left)", new AutoGearCommandGroup(3));
    	SmartDashboard.putData("Auto Gear Positions", autoGearChooser);
    	
    	autoShooterChooser = new SendableChooser();
    	autoShooterChooser.addDefault("Left starting position", new AutoShooterLeftCommandGroup());
    	autoShooterChooser.addObject("Center starting position", new AutoShooterCenterCommandGroup());
    	autoShooterChooser.addObject("Right starting position", new AutoShooterLeftCommandGroup());
    	SmartDashboard.putData("Auto Field Position", autoShooterChooser);
    }
    
    
    //This starts the methods for autonomous
    public void autonomousInit() {
    	ultrasonicCommand.start();
    	
    	if(autoTypeChooser.getSelected().equals("Gear")){
    		autonomousCommand = (CommandGroup) autoGearChooser.getSelected();
    	}
    	else{
    		autonomousCommand = (CommandGroup) autoShooterChooser.getSelected();
    	}
    }
    
    //This starts the methods for teleop and stops methods for autonomous
    @Override
	public void teleopInit() {
    	autonomousCommand.cancel();
    	driveCommand.start();
    	shooterCommand.start();
    	climberCommand.start();
    }
    
    //This stops the methods for autonomous
	@Override
	public void disabledInit() {
		driveCommand.cancel();
		shooterCommand.cancel();
		climberCommand.cancel();
	}
    
	//Methods below this line do not need to be edited/////////////////////////////////////////////////////////////////////////
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if(oi.xbox.getPOV() == 0){
    		server.startAutomaticCapture("cam0", "cam0");
    	}
    	else if(oi.xbox.getPOV() == 180){
    		server.startAutomaticCapture("cam1", "cam1");
    	}
    	
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }


	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
}
