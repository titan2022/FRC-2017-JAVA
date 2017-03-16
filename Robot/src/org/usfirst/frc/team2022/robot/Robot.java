
package org.usfirst.frc.team2022.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team2022.command.ClimberCommand;
import org.usfirst.frc.team2022.command.DriveCommand;
import org.usfirst.frc.team2022.command.ShooterCommand;
import org.usfirst.frc.team2022.command.UltrasonicCommand;
import org.usfirst.frc.team2022.command.autonomous.group.AutoGearCommandGroup;
import org.usfirst.frc.team2022.command.autonomous.group.AutoShooterCenterCommandGroup;
import org.usfirst.frc.team2022.command.autonomous.group.AutoShooterLeftCommandGroup;
import org.usfirst.frc.team2022.command.autonomous.group.AutoShooterRightCommandGroup;
import org.usfirst.frc.team2022.subsystem.ClimberSubsystem;
import org.usfirst.frc.team2022.subsystem.DriveSubsystem;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
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
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	
	//Create References to commands
	public DriveCommand driveCommand;
	public ShooterCommand shooterCommand;
	private UltrasonicCommand ultrasonicCommand;
	public ClimberCommand climberCommand;
	
	//Autonomous
	CommandGroup autonomousCommand = new CommandGroup();

	SendableChooser<String> autoTypeChooser;
	SendableChooser<String> autoShooterChooser;
	SendableChooser<String> autoGearChooser;
	double position;
	double gear;
	
	
	//Create reference to OI
	public static OI oi;
	public XboxMap xboxMap = new XboxMap();
	
	//Initialization code ran when you turn on the robot

    public void robotInit() {    	

    	//Instantiate OI
    	oi = new OI();
    	
    	//Instantiate Commands
    	driveCommand = new DriveCommand();
    	shooterCommand = new ShooterCommand();
    	climberCommand = new ClimberCommand();
    	ultrasonicCommand = new UltrasonicCommand();
    	
    	//Create thread for streaming cameras
    	Thread t = new Thread(new Runnable(){
    		public void run(){
    			boolean allowCam1 = false;
        		
        		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
                camera1.setResolution(640, 480);
                camera1.setFPS(15);
                UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
                camera2.setResolution(640, 480);
                camera2.setFPS(15);
                
                CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
                CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
                CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 640, 480);
                
                Mat image = new Mat();
                
                while(!Thread.interrupted()) {
                	
                	if(oi.xbox.GetStartValue()) {
                		allowCam1 = !allowCam1;
                	}
                	
                    if(allowCam1){
                      cvSink2.setEnabled(false);
                      cvSink1.setEnabled(true);
                      cvSink1.grabFrame(image);
                    } else{
                      cvSink1.setEnabled(false);
                      cvSink2.setEnabled(true);
                      cvSink2.grabFrame(image);     
                    }
                    
                    outputStream.putFrame(image);
                }
                
    		}
    	});
    	t.start();

		
    	autoTypeChooser = new SendableChooser<String>();
    	autoTypeChooser.addDefault("Gear Autonomous", "Gear");
    	autoTypeChooser.addObject("Shooter Autonomous", "Shooter");
    	autoTypeChooser.addObject("Straight Autonomus", "Straight");
    	SmartDashboard.putData("Autonomous Mode", autoTypeChooser);
    	
    	autoGearChooser = new SendableChooser<String>();
    	autoGearChooser.addDefault("Position Gear 1 (Right)", "gearOption1"); 
    	autoGearChooser.addObject("Position Gear 2 (Middle)", "gearOption2"); 
    	autoGearChooser.addObject("Position Gear 3 (Left)", "gearOption3"); 
    	SmartDashboard.putData("Auto Gear Positions", autoGearChooser);
    	
    	autoShooterChooser = new SendableChooser<String>();
    	autoShooterChooser.addDefault("Left starting position", "shooterOption1"); 
    	autoShooterChooser.addObject("Center starting position", "shooterOption2"); 
    	autoShooterChooser.addObject("Right starting position", "shooterOption3"); 
    	SmartDashboard.putData("Auto Field Position", autoShooterChooser);  	
    }
    
    
    //This starts the methods for autonomous
    public void autonomousInit() {
    	ultrasonicCommand.start();
    	
    	if(autoTypeChooser.getSelected().equals("Gear")){
    		if(autoGearChooser.getSelected().equals("gearOption1")){
    			autonomousCommand = new AutoGearCommandGroup(1);
    		}
    		else if(autoGearChooser.getSelected().equals("gearOption2")){
    			autonomousCommand = new AutoGearCommandGroup(2);
    		}
    		else if(autoGearChooser.getSelected().equals("gearOption3")){
    			autonomousCommand = new AutoGearCommandGroup(3);
    		}
    	}
    	else if(autoTypeChooser.getSelected().equals("Shooter")){
    		if(autoShooterChooser.getSelected().equals("shooterOption1")){
    			autonomousCommand = new AutoShooterLeftCommandGroup();
    		}
    		else if(autoShooterChooser.getSelected().equals("shooterOption2")){
    			autonomousCommand = new AutoShooterCenterCommandGroup();
    		}
    		else if(autoShooterChooser.getSelected().equals("shooterOption3")){
    			autonomousCommand = new AutoShooterRightCommandGroup();
    		}
    	}
    	else if(autoTypeChooser.getSelected().equals("Straight")){
    		autonomousCommand = new CommandGroup();
    	}
    }
    
    //This starts the methods for teleop and stops methods for autonomous
    @Override
	public void teleopInit() {
    	if(autonomousCommand.isRunning()){
        	autonomousCommand.cancel();
    	}
    	if(!ultrasonicCommand.isRunning()){
    		ultrasonicCommand.start();
    	}
    	driveCommand.start();
    	shooterCommand.start();
    	climberCommand.start();
    }
    
    //This stops the methods for autonomous
	@Override
	public void disabledInit() {
		ultrasonicCommand.cancel();
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

    @Override
	public void robotPeriodic() {
		// TODO Auto-generated method stub
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
