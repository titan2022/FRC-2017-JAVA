package org.usfirst.frc.team2022.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Attack3 extends Joystick{
	/**
	 * Construct an instance of a Logitech Attack3 joystick (The kind in the
	 * KoP).
	 *Initialize Button6 and Button 7 for controlling the pickup relays
	 * @param port The port on the driver station that the joystick is plugged
	 * into.
	 * @return
	 */
	public Attack3(int port) {
		super(port);
	} 
	
	public double getXAxis(){
		return getX();
//		return getAxis(Joystick.AxisType.kX);
	}
	
	public double getYAxis(){
		return getY();
//		return getAxis(Joystick.AxisType.kY);
	}
	/**
	 * Return the value of the Twist axis for this joystick This value is always
	 * 0 since there is no twist axis.
	 * @return 0 since there is no twist axis
	 */
	public double getTwist() {
		return 0;
	} 
	/**
	 * Get a JoystickButton for the Command Subsystem OI Class
	 *
	 * @param button The button as an integer
	 * @return JoystickButton
	 */
	public JoystickButton GetButton(int button) {
		return (new JoystickButton(this, button));
	}
	
	public boolean getButton(int button) {
		return (new JoystickButton(this, button)).get();
	}
}
