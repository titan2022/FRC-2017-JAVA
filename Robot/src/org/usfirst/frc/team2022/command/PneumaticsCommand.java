package org.usfirst.frc.team2022.command;

import org.usfirst.frc.team2022.robot.OI;
import org.usfirst.frc.team2022.robot.Robot;
import org.usfirst.frc.team2022.robot.XboxMap;
import org.usfirst.frc.team2022.subsystem.PneumaticsSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class PneumaticsCommand extends Command{

	PneumaticsSubsystem pneumaticsSubsystem = Robot.pneumaticsSubsystem;
	XboxMap xboxMap = new XboxMap();
	OI oi = Robot.oi;
	
	public PneumaticsCommand(){
		requires(pneumaticsSubsystem);
	}
	
    protected void initialize() {
    	
    }
    
    protected void execute() {    	
    	if(xboxMap.pneumaticSwitchEx()){
    		pneumaticsSubsystem.solenoidExtract();
    	}
    	else if(xboxMap.pneumaticSwitchRe()){
    		pneumaticsSubsystem.solenoidExtract();
    	}
    	else if(xboxMap.pneumaticSwitchRe() && xboxMap.pneumaticSwitchEx()){
    		pneumaticsSubsystem.solenoidOff();
    	}
    }

	protected boolean isFinished() {
	     return false;
	}

	protected void end() {
		pneumaticsSubsystem.stop();
	}
}
