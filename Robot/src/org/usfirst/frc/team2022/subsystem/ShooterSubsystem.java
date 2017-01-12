package org.usfirst.frc.team2022.subsystem;


import org.usfirst.frc.team2022.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	CANTalon motor = new CANTalon(RobotMap.motorPort5);
	double speed = 0;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }
    
    public void setSpeed(double speed) {
    	this.speed = speed;
    	motor.set(speed);
    }
    
    public double getSpeed(){
    	return speed;
    }
    
    public void stop(double speed){
    	motor.set(0);
    }

}

