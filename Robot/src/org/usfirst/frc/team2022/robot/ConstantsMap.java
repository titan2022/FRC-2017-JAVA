package org.usfirst.frc.team2022.robot;

/*
 * Easy place to access variables that need to be easily changed
 */

public class ConstantsMap {

	public static final double DRIVE_ENCODER_DIST_PER_TICK_LEFT = ((4* Math.PI)/(360)*1.5);
	public static final double DRIVE_ENCODER_DIST_PER_TICK_RIGHT = ((4* Math.PI)/(500)*1.5);
	public static final double SHOOTER_ENCODER_DIST_PER_TICK = 0; 

	// maximun distance in inches we expect the robot to see
	private static final double kMaxDistance = 196.85;

	// factor to convert sensor values to a distance in inches
	private static final double kValueToInches = 0.125;

	public static final double ROBOT_LENGTH_INCHES = 36.5; 

	
	public static final double motorSpeed = 0;
	
	public static final double climberSpeed = 0; 

	public static double KP_DRIVE_ANGLE = 0.3;
	public static double KI_DRIVE_ANGLE = 0;
	public static double KD_DRIVE_ANGLE = 0; 
	public static double KF_DRIVE_ANGLE = 0;
	
	public static double KP_DRIVE_SPEED = 0;
	public static double KI_DRIVE_SPEED = 0;
	public static double KD_DRIVE_SPEED = 0;
	public static double KF_DRIVE_SPEED = 0;
	
	public static double KP_SHOOTER_SPEED = 0;
	public static double KI_SHOOTER_SPEED = 0;
	public static double KD_SHOOTER_SPEED = 0;
	public static double KF_SHOOTER_SPEED = 0;
	
	public static double P = 0.01;

	public static double I = 0; 
	public static double D = 0; 
	public static double F = 0;

}
