package org.usfirst.frc.team2022.robot;

import org.usfirst.frc.team2022.controller.Attack3;
import org.usfirst.frc.team2022.controller.Xbox;

/**
* This class is the glue that binds the controls on the physical operator
* interface to the commands and command groups that allow control of the robot.
*/
public class OI {
	//User interface Constants
	public double attackThrottleSensitivity=.1;
	//Controllers
	public Xbox xbox;
	public Attack3 attack3,attack4;
	public OI(){
		xbox = new Xbox(0);
		attack3 = new Attack3(1);
		attack4 = new Attack3(2);
	}
}