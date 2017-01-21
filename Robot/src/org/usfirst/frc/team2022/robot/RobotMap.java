package org.usfirst.frc.team2022.robot;

/**
 * This class is initialized in the Robot class and is referenced
 * in the subsystem classes. This class holds the variables to port 
 * numbers for sensors and motors.
 * 
 * Standard format for port number variables:
 * public static final portNumber = 0;
 */

public class RobotMap {

	// ports for drive base


	
	public static final int kUltrasonicPort = 0;

	public static final int motorPort1 = 2;
	public static final int motorPort2 = 5;
	public static final int motorPort3 = 3;
	public static final int motorPort4 = 1;
	
	//ports for gyro
	public static final int gyro = 1;


	// encoder ports for drive base
	public static int leftEncoderA = 2;
	public static int leftEncoderB = 3;
	public static int rightEncoderA = 0;
	public static int rightEncoderB = 1;
	//public static int shooterEncoderA = 0;
	//public static int shooterEncoderB = 1;
	//public static int shooterEncoderA = 0;
}

