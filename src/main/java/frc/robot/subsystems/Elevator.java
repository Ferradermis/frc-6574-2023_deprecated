// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
//Gatorvator
public class Elevator extends SubsystemBase {

    public WPI_TalonFX intakeLeft = new WPI_TalonFX(Constants.ELEVATOR_LEFT_CAN_ID, "rio");
	public WPI_TalonFX intakeRight = new WPI_TalonFX(Constants.ELEVATOR_RIGHT_CAN_ID, "rio");

    	/** Creates a new Elevator. */
	public Elevator() {
		
	}

}
