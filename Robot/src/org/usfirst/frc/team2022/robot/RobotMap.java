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

	// ports for drive base

	public static final int MOTOR_PORT_SWITCH = 1;
	
	public static final int K_ULTRASONIC_PORT = 0;

	public static final int LEFT_DRIVE_PORT_1 = 2;
	public static final int LEFT_DRIVE_PORT_2 = 5;
	public static final int LEFT_DRIVE_PORT_3 = 4;
	public static final int RIGHT_DRIVE_PORT_1 = 3;
	public static final int RIGHT_DRIVE_PORT_2 = 1;
	public static final int RIGHT_DRIVE_PORT_3 = 6;
	
	//ports for gyro
	public static final int GYRO_PORT = 1;

	//port for servo (gate that opens and closes for balls to go through)
	public static final int SERVO_MOTOR_PORT = 0;
	
	//port for limit switch (returns if bot has reached top)
	public static int LIMIT_SWITCH_PORT = 0;

	// encoder ports for drive base
	public static int LEFT_ENCODER_PORT_A = 2;
	public static int LEFT_ENCODER_PORT_B = 3;
	public static int RIGHT_ENCODER_PORT_A = 0;
	public static int RIGHT_ENCODER_PORT_B = 1;
	//public static int shooterEncoderA = 0;
	//public static int shooterEncoderB = 1;
	//public static int shooterEncoderA = 0;
	

	public static int CLIMBER_AGITATOR_MOTOR_PORT = 0;
	public static int SHOOTER_MOTOR_PORT_1 = 0;
	public static int SHOOTER_MOTOR_PORT_2 = 0;

	public static DigitalSource shooterEncoderA;
	public static DigitalSource shooterEncoderB;

	
}

