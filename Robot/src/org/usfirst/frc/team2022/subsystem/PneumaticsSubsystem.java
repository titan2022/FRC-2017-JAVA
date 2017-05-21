package org.usfirst.frc.team2022.subsystem;

import org.usfirst.frc.team2022.command.PneumaticsCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsSubsystem extends Subsystem{

	private Compressor mainCompressor;
	private Solenoid piston1extract;
	private Solenoid piston1retract;
	private Relay spike1;
	
	public PneumaticsSubsystem(){
		mainCompressor = new Compressor(1);
		piston1extract = new Solenoid(1);
		piston1retract = new Solenoid(2);
		spike1 = new Relay(2);
	}
	
	
	public void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new PneumaticsCommand());
	}
	
	public void solenoidExtract(){
		piston1extract.set(true);
		piston1retract.set(false);
	}
	
	public void solenoidRetract(){
		piston1extract.set(false);
		piston1retract.set(true);
	}
	
	public void solenoidOff(){
		piston1extract.set(false);
		piston1retract.set(true);
	}
	
	public void spikeForward(){
		spike1.set(Relay.Value.kForward);
	}
	
	public void spikeBackward(){
		spike1.set(Relay.Value.kReverse);
	}
	
	public void spikeOff(){
		spike1.set(Relay.Value.kOff);
	}
	
	public void stop(){
		mainCompressor.stop();
	}

}
