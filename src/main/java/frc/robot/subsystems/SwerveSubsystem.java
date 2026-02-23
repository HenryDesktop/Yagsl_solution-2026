// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import static edu.wpi.first.units.Units.Meter;




public class SwerveSubsystem extends SubsystemBase {
  /** Creates a new SwerveSubsystem. */

File directory = new File(Filesystem.getDeployDirectory(),"swerve");
SwerveDrive swerveDrive;



  public SwerveSubsystem() {

    try
    {
      swerveDrive = new SwerveParser(directory).createSwerveDrive(Constants.maxSpeed, new Pose2d(new Translation2d(Meter.of(1), Meter.of(4)),Rotation2d.fromDegrees(0)));
      // Alternative method if you don't want to supply the conversion factor via JSON files.
      // swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed, angleConversionFactor, driveConversionFactor);
    } catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public Pose2d getPose()
  {
    return swerveDrive.getPose();
  }

  public void resetOdometry(Pose2d initialHolonomicPose)
  {
      System.out.println("RESET ODOMETRY: ");
    swerveDrive.resetOdometry(initialHolonomicPose);
  }

  public ChassisSpeeds getRobotVelocity()
  {
    return swerveDrive.getRobotVelocity();
  }

    public Rotation2d getHeading()
  {
    return getPose().getRotation();
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Module oriented [0]", swerveDrive.getModulePositions()[0].angle.getDegrees());
    SmartDashboard.putNumber("Module oriented [1]", swerveDrive.getModulePositions()[1].angle.getDegrees());
    SmartDashboard.putNumber("Module oriented [2]", swerveDrive.getModulePositions()[2].angle.getDegrees());
    SmartDashboard.putNumber("Module oriented [3]", swerveDrive.getModulePositions()[3].angle.getDegrees());

  }

    public void zeroGyro()
  {
    swerveDrive.zeroGyro();
  }

  public SwerveDrive getSwerveDrive() {
    return swerveDrive;
  }
  
public void driveFieldOriented(ChassisSpeeds velocity) {
swerveDrive.driveFieldOriented(velocity);
  }

public Command driveFieldOriented(Supplier<ChassisSpeeds> velocity){
  return run(() -> 
  {swerveDrive.driveFieldOriented(velocity.get());
  });
}

}