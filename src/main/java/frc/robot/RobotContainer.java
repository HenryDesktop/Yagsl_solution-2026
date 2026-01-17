package frc.robot;

import frc.robot.commands.ComAcomodo;
import frc.robot.commands.ComSwerve;
import frc.robot.subsystems.SubSwerve;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final CommandXboxController m_DriveControl = new CommandXboxController(0);
  private final CommandXboxController m_MechaControl = new CommandXboxController(1);
  private SendableChooser<Command> m_autChooser;

  public RobotContainer() {

    configureBindings();

    new Thread(() -> {
      try {
        Thread.sleep(1000);
        m_autChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto chooser", m_autChooser);
      } catch (Exception e){}
    }).start();
  }

  private void configureBindings() {

    new Trigger(() -> Math.abs(m_DriveControl.getLeftY()) >= 0.05 || Math.abs(m_DriveControl.getLeftX()) >= 0.05 ||
    Math.abs(m_DriveControl.getRightX()) >= 0.05).whileTrue(new ComSwerve(() -> m_DriveControl.getLeftX(),
                                                                          () -> m_DriveControl.getLeftY(),
                                                                          () -> m_DriveControl.getRightX()));

    m_DriveControl.x().whileTrue(new InstantCommand(() -> SubSwerve.getInstance().resetGyro()));
    m_DriveControl.a().onTrue(new ComAcomodo());
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(m_autChooser.getSelected(), new InstantCommand(() -> SubSwerve.getInstance().stop()));
    //return new InstantCommand(() -> SubSwerve.getInstance().stop());
  }
}
