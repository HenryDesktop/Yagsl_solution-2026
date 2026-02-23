// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
private final SwerveSubsystem drivebase = new SwerveSubsystem();

  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);


  public RobotContainer() {
    configureBindings();

            // Put the chooser on Shuffleboard

        // Set a default auto so it runs even if you don't pick one        
  }
SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(), //gives inputs to swerve from left joystick for translating
                                                                () -> driverController.getLeftY() * -1,
                                                                () -> driverController.getLeftX() * -1)
                                                            .withControllerRotationAxis(driverController::getRightX)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .scaleTranslation(.5)
                                                            .scaleRotation(drivebase.getSwerveDrive().getMaximumChassisAngularVelocity()) //SPEED CHANGE
                                                            .allianceRelativeControl(true);

  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(driverController::getRightX, //gives inputs from the right joystick for turning
                                                                                             driverController::getRightY)
                                                           .headingWhile(true);
 
Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle); //allows robot to move at field oriented angle
Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
  


private void configureBindings() {
  
    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);

    driverController.a().onTrue(new InstantCommand (drivebase::zeroGyro, drivebase));

  }


  // public Command getAutonomousCommand(String pathName) {
  //   // Create a path following command using AutoBuilder. This will also trigger event markers. //
  //   return drivebase.getAutonomousCommandSub("New Auto");
  // }

    public Command getAutonomousCommand() {
    return null;
  }

}
