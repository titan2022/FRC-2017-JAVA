package org.usfirst.frc.team2022.command.autonomous;

import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.subsystem.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeServoCommand extends Command{
	ShooterSubsystem shooterSubsystem = Robot.shooterSubsystem;
	String position;
	boolean isFinished = false;
	public ChangeServoCommand(String pos) {
		requires(shooterSubsystem);
		position = pos;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
	}
	protected void execute() {
		if(position.equals("open")){
			shooterSubsystem.setServo(1);
			isFinished = true;
		}
		else if(position.equals("close")){
			shooterSubsystem.setServo(0);
			isFinished = true;
		}
	}
}
