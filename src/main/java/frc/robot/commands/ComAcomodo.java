package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.SubSwerve;

public class ComAcomodo extends Command {

  private PIDController turnPID;
  private double turnVel;
  private ChassisSpeeds swerveSpeeds;
  private boolean finish;

  public ComAcomodo() {

    addRequirements(SubSwerve.getInstance());
    turnPID = new PIDController(0.020, 0.01, 0.0); //0.015 0.005 0.001
    turnPID.setTolerance(0.4);
  }

  @Override
  public void initialize() {

    turnPID.setSetpoint(0);
  }

  @Override
  public void execute() {

    turnVel = turnPID.calculate(LimelightHelpers.getTX(""));
    swerveSpeeds = new ChassisSpeeds(0, 0, -turnVel);
    SubSwerve.getInstance().setChassisSpeed(swerveSpeeds);
    finish = turnPID.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {

    SubSwerve.getInstance().stop();
    finish = false;
  }

  @Override
  public boolean isFinished() {
    return finish;
  }
}
