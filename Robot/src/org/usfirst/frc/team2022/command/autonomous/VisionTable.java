package org.usfirst.frc.team2022.command.autonomous;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionTable {
	
	public static NetworkTable table = NetworkTable.getTable("SmartDashboard");
	
	//Process
	public static void setProcessBoiler(boolean bool){
		table.putBoolean("processBoiler", bool);
	}
	
	public static void setProcessPeg(boolean bool){
		table.putBoolean("processPeg", bool);
	}
	
	//Done
	public static boolean getBoilerDone(){
		return table.getBoolean("boilerDone", false);
	}
	
	public static boolean getPegDone(){
		return table.getBoolean("pegDone", false);
	}
	
	public static void setBoilerDone(boolean bool){
		table.putBoolean("boilerDone", bool);
	}
	
	public static void setPegDone(boolean bool){
		table.putBoolean("pegDone", bool);
	}
	
	//Boiler things
	public static double getBoilerAngle(){
		System.out.println("angle: " + table.getNumber("boilerAngle", 0));
		return table.getNumber("boilerAngle", 0);
	}
	
	public static double getUltrasonicDistance(){
		return table.getNumber("UltrasonicDistance", 0);
	}
	
	//Peg things
	public static double getPegDistance(){
		return table.getNumber("pegDistance", 0);
	}
	
	public static double getPegAngle(){
		return table.getNumber("pegAngle", 0);
	}
}
