package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

public final class Constants {

  public static class SwerveConstants {

    public static final int kFLDriveID = 3;
    public static final int kFRDriveID = 7;
    public static final int kBLDriveID = 1;
    public static final int kBRDriveID = 5;

    public static final int kFLTurnID = 4;
    public static final int kFRTurnID = 8;
    public static final int kBLTurnID = 2;
    public static final int kBRTurnID = 6;

    public static final int kFLEncoderID = 22;
    public static final int kFREncoderID = 23;
    public static final int kBLEncoderID = 24;
    public static final int kBREncoderID = 21;

    public static final boolean kFLDriveInverted = true;
    public static final boolean kFRDriveInverted = false;
    public static final boolean kBLDriveInverted = true;
    public static final boolean kBRDriveInverted = false;

    public static final boolean kFLTurnInverted = false;
    public static final boolean kFRTurnInverted = false;
    public static final boolean kBLTurnInverted = false;
    public static final boolean kBRTurnInverted = false;

    public static final boolean kFLEncoderInverted = false;
    public static final boolean kFREncoderInverted = false;
    public static final boolean kBLEncoderInverted = false;
    public static final boolean kBREncoderInverted = false;

    public static final int kGyroID = 25;

    public static final double kP_PID_Drive = 0.25;//0.45 0.35 0.38
    public static final double kI_PID_Drive = 0.12; //0.15 0.25 0.15
    public static final double kD_PID_Drive = 0.01;//0.001 0.01 0.005

    public static final double kP_PID_Turn = 0.003;

    public static final int kLimitCurrentDrive = 45;//45
    public static final int kLimitCurrentTurn = 35;//35
  } 

  public static class RobotConstants {

    public static final double kWheelRadio = 0.05;
    public static final double kWheelLenght = 2 * Math.PI * kWheelRadio;
    public static final double kWheelTransmision = 6.75;
    public static final double kDistanceFront_Back = 0.74;
    public static final double kDistanceLeft_Right = 0.74;

    private static final double kMaxSpeedMPerSec = 4.5;
    private static final double kPowerPercent = 65;// Porcentaje de la velocidad -- 85
    public static final double kPower = kMaxSpeedMPerSec * (kPowerPercent / 100);
    public static final Translation2d kFL_Location = new Translation2d(kDistanceFront_Back / 2,
         kDistanceLeft_Right / 2);
    public static final Translation2d kFR_Location = new Translation2d(kDistanceFront_Back / 2,
        -kDistanceLeft_Right / 2);
    public static final Translation2d kBL_Location = new Translation2d(-kDistanceFront_Back / 2,
         kDistanceLeft_Right / 2);
    public static final Translation2d kBR_Location = new Translation2d(-kDistanceFront_Back / 2,
        -kDistanceLeft_Right / 2);

    public static final int kLedPort = 0;
    public static final boolean redAlliance = true;
  }

  public static class ElevatorConstants{

    public static final byte kLeftID = 9;
    public static final byte kRightID = 10;
    public static final byte kShooterID = 11;//11
    public static final byte kPhotoPort = 0;

    public static final float kLevel0 = 0f;
    public static final float kLevel1 = 1.78f;//1.78 //2.7
    public static final float kLevel2 = 4.3f; //4.3 //3.27
    public static final float kLevel3 = 9.5f;//9.5 // 10.09

    public static final float kLevel2Seaweed = 0.0f;

    public static final float kP_PID = 0.5F;//0.5
    public static final float kI_PID = 0.01F;
    public static final float kD_PID = 0.001F;

    public static final byte kElevLimitCurrent = 45;
    public static final byte kShooterLimitCurrent = 30;
    public static final float maximumPower = 0.8f; //Velocidad máxima para ir a nivel 1,2 y 3
    public static final float kSuctionPower = -0.15f;
    public static final float kShootDown = -0.2f;
    public static final float kShootUp = -0.45f;

    public static final float kMinReefSensor = 19f;
    public static final float kMaxReefSensor = 28f;
  }

  public static class ClimberConstants{

    public static final byte kClimberID = 12;
    public static final byte kSwitchPort = 1;
    public static final byte kServoPort = 1;
    public static final float kDistanceSensor = 1.5f;
  }

  public static class IntakeConstants{

    public static final byte kIntakeID = 13;
    public static final byte kIntakeLimitCurrent = 25;

    public static final float kShootDown = 0.3f;
    public static final float kShootUp = 0.15f;
  }

  public static class FieldConstants{

    public static final double aprilTagHubHeightM = 1.12395 - 0.15;
  }
}
