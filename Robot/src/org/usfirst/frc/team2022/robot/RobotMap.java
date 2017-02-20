package org.usfirst.frc.team2022.robot;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * This class is initialized in the Robot class and is referenced
 * in the subsystem classes. This class holds the variables to port 
 * numbers for sensors and motors.
 * 
 * Standard format for port number variables:
 * public static final portNumber = 0;
 */

public class RobotMap {
	
	//Motors
	//Drive motor ports
	public static final int LEFT_DRIVE_PORT_1 = 4;
	public static final int LEFT_DRIVE_PORT_2 = 5;
	public static final int LEFT_DRIVE_PORT_3 = 6;
	public static final int RIGHT_DRIVE_PORT_1 = 7;
	public static final int RIGHT_DRIVE_PORT_2 = 8;
	public static final int RIGHT_DRIVE_PORT_3 = 9;
	
	//Shooter motor ports
	public static int AGITATOR_MOTOR_PORT = 1;
	public static int SHOOTER_MOTOR_PORT = 2;
	
	//Climber motor ports
	public static int CLIMBER_MOTOR_PORT = 10;
	
	//Sensors
	//Drive base sensors
	public static final int GYRO_PORT = 0;
	public static final int K_ULTRASONIC_PORT = 1;

	//port for servo (gate that opens and closes for balls to go through)
	public static final int SERVO_MOTOR_PORT = 1;

	// encoder ports for drive base
	public static int LEFT_ENCODER_PORT_A = 7;
	public static int LEFT_ENCODER_PORT_B = 6;
	public static int RIGHT_ENCODER_PORT_A = 9;
	public static int RIGHT_ENCODER_PORT_B = 8;
	
	//Encoder ports for shooter
	public static int SHOOTER_ENCODER_A = 0;
	public static int SHOOTER_ENCODER_B = 1;
	
}

