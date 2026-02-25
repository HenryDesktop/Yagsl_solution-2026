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
      
  }
SwerveInputStream driveAngularVelocity =
    SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                () -> -driverController.getLeftY(),
                                                  () -> -driverController.getLeftX())
                                                  .withControllerRotationAxis(driverController::getRightX)
                                                  .deadband(OperatorConstants.DEADBAND)
                                                  .scaleTranslation(.6)
                                                  .scaleRotation(1.0)
                                                  .allianceRelativeControl(true);

 SwerveInputStream driveDirectAngle =
      SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                () -> -driverController.getLeftY(),
                                                  () -> -driverController.getLeftX())
                                              .withControllerHeadingAxis(
                                                  driverController::getRightX,
                                                  driverController::getRightY)
                                                .deadband(OperatorConstants.DEADBAND)
                                                .scaleTranslation(.6)
                                                .scaleRotation(1.0)
                                                .headingWhile(true)
                                                .allianceRelativeControl(true);

Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
  


private void configureBindings() {
  
    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);

    driverController.a().onTrue(new InstantCommand (drivebase::zeroGyro, drivebase));

  }

    public Command getAutonomousCommand() {
    return null;
  }

}
