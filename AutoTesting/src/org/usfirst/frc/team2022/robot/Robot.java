
package org.usfirst.frc.team2022.robot;

import org.usfirst.frc.team2022.command.AutoDriveStraight;
import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;


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
	
	//Create References to commands
	public DriveCommand driveCommand;
	public AutoDriveStraight autoDriveStraight;
		
	//Create reference to OI
	public static OI oi;
	
	//Initialization code ran when you turn on the robot

    public void robotInit() {    	

    	//Instantiate OI
    	oi = new OI();
    	
    	//Instantiate Commands
    	driveCommand = new DriveCommand();
    	autoDriveStraight = new AutoDriveStraight(120, 0.2);
    	
    	CameraServer.getInstance().startAutomaticCapture();
    }
    
	
    
    //This starts the methods for autonomous
    public void autonomousInit() {
    	autoDriveStraight.start();
    	
    }
    
    //This starts the methods for teleop and stops methods for autonomous
    @Override
	public void teleopInit() {
    	autoDriveStraight.cancel();
    	driveCommand.start();
    }
    
    //This stops the methods for autonomous
	@Override
	public void disabledInit() {
		autoDriveStraight.cancel();
		driveCommand.cancel();
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
