// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeProcess;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  /* Controllers */
  private final Joystick driver = new Joystick(0);
  public static Joystick operator = new Joystick(1);

	// STILL NEED TO FIGURE OUT TRIGGERS
	// xBox Variables
	public Trigger driver_aButton = new JoystickButton(driver, 1);
	public Trigger driver_bButton = new JoystickButton(driver, 2);
	public Trigger driver_xButton = new JoystickButton(driver, 3);
	public Trigger driver_yButton = new JoystickButton(driver, 4);
	public Trigger driver_leftBumper = new JoystickButton(driver, 5);
	public Trigger driver_rightBumper = new JoystickButton(driver, 6);
	public XBoxLeftTrigger driver_leftTrigger = new XBoxLeftTrigger(driver);
	public XBoxRightTrigger driver_rightTrigger = new XBoxRightTrigger(driver);
	public Trigger driver_backButton = new JoystickButton(driver, 7);
	public Trigger driver_startButton = new JoystickButton(driver, 8);
	public POVButton driver_upDpad = new POVButton(driver, 0);
	public POVButton driver_rightDpad = new POVButton(driver, 90);
	public POVButton driver_downDpad = new POVButton(driver, 180);
	public POVButton driver_leftDpad = new POVButton(driver, 270);

	public double getDriverLeftX() {
		return driver.getRawAxis(0);
	}

	public double getDriverLeftY() {
		return driver.getRawAxis(1);
	}

	public double getDriverRightX() {
		return driver.getRawAxis(4);
	}

	public double getDriverRightY() {
		return driver.getRawAxis(5);
	}

	public double getDriverLeftTrigger() {
		return driver.getRawAxis(2);
	}

	public double getDriverRightTrigger() {
		return driver.getRawAxis(3);
	}

	public boolean getDriverLeftBumper() {
		return driver.getRawButton(5);
	}

	public boolean getDriverRightBumper() {
		return driver.getRawButton(6);
	}

	// operator Variables
	public Trigger operator_aButton = new JoystickButton(operator, 1);
	public Trigger operator_bButton = new JoystickButton(operator, 2);
	public Trigger operator_xButton = new JoystickButton(operator, 3);
	public Trigger operator_yButton = new JoystickButton(operator, 4);
	public Trigger operator_leftBumper = new JoystickButton(operator, 5);
	public Trigger operator_rightBumper = new JoystickButton(operator, 6);
	public Trigger operator_backButton = new JoystickButton(operator, 7);
	public Trigger operator_startButton = new JoystickButton(operator, 8);
	public XBoxLeftTrigger operator_leftTrigger = new XBoxLeftTrigger(operator);
	public XBoxRightTrigger operator_rightTrigger = new XBoxRightTrigger(operator);
	public POVButton operator_upDpad = new POVButton(operator, 0);
	public POVButton operator_rightDpad = new POVButton(operator, 90);
	public POVButton operator_downDpad = new POVButton(operator, 180);
	public POVButton operator_leftDpad = new POVButton(operator, 270);

	public double getOperatorLeftX() {
		return operator.getRawAxis(0);
	}

	public double getOperatorLeftY() {
		return operator.getRawAxis(1);
	}

	public double getOperatorRightX() {
		return operator.getRawAxis(4);
	}

	public double getOperatorRightY() {
		return operator.getRawAxis(5);
	}

	public double getOperatorLeftTrigger() {
		return operator.getRawAxis(2);
	}

	public double getOperatorRightTrigger() {
		return operator.getRawAxis(3);
	}

	public boolean getOperatorLeftBumper() {
		return operator.getRawButton(5);
	}

	public boolean getOperatorRightBumper() {
		return operator.getRawButton(6);
	}

	public class XBoxRightTrigger extends Trigger {

		private final Joystick joystick;

		public XBoxRightTrigger(Joystick joystick) {
			this.joystick = joystick;
		}

		public boolean get() {
			return joystick.getRawAxis(3) > .25;
		}
	}

	public class XBoxLeftTrigger extends Trigger {

		private final Joystick joystick;

		public XBoxLeftTrigger(Joystick joystick) {
			this.joystick = joystick;
		}

		public boolean get() {
			return joystick.getRawAxis(2) > .25;
		}
	}

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  private final JoystickButton driverYButton = new JoystickButton(driver, XboxController.Button.kY.value);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
	public static final Intake intake = new Intake();
  //public static final Limelight limelight = new Limelight();
  //public static final PneumaticHub pch = new PneumaticHub(Constants.PCH_CAN_ID);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      boolean fieldRelative = true;
      boolean openLoop = true;
      s_Swerve.setDefaultCommand(new TeleopSwerve(s_Swerve, driver, translationAxis, strafeAxis, rotationAxis, fieldRelative, openLoop));
      // Configure the button bindings
      configureBindings();
	}

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
        //.onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

        /* Driver Buttons */
        driverYButton.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        driver_rightBumper.whileTrue(new IntakeProcess());
        //driver_xButton.whileTrue(new LimelightAlign(s_Swerve));
    
    
        /* Operator Buttons */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
        // An ExampleCommand will run in autonomous
        boolean fieldRelative = false;
        boolean openLoop = true;
    
        return null;
  }
}