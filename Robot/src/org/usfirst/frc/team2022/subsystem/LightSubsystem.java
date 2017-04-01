package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.LightCommand;
import org.usfirst.frc.team2022.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightSubsystem extends Subsystem{
	Spark s1 = new Spark(2);
	Spark s2 = new Spark(3);


	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LightCommand(0));
		
	}
	public void Off(){
		System.out.println("s1 is " + s1.isAlive());
		System.out.println(s1.getPosition());
		s1.setSpeed(1);
		s2.setSpeed(1);
		//r2.set(Value.kOn);
		//System.out.println("off");
	}
	public void White(){
		//r2.set(Value.kRevers2se);
		s1.setSpeed(1);
		s2.setSpeed(1);

		//System.out.println("white");
	}

}
