package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import org.apache.commons.math3.ml.distance.CanberraDistance;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Velocity;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorUtil;
import team5427.lib.motors.MotorConfiguration.IdleState;
import team5427.lib.motors.MotorConfiguration.MotorMode;

public final class ShooterConstants {
    
    public static final CANDeviceId kLeftHoodMotorCanId = new CANDeviceId(20);
    public static final CANDeviceId kLeftFlywheelLeaderMotorCanId = new CANDeviceId(21);
    public static final CANDeviceId kLeftFlywheelFollowMotorCanId = new CANDeviceId(22);
    public static final CANDeviceId kRightHoodMotorCanId = new CANDeviceId(23);
    public static final CANDeviceId kRightFlywheelLeaderMotorCanId = new CANDeviceId(24);
    public static final CANDeviceId kRightFlywheelFollowMotorCanId = new CANDeviceId(25);

    public static final MotorConfiguration kLeftHoodMotorConfiguration = new MotorConfiguration();
    public static final MotorConfiguration kLeftFlywheelMotorConfiguration = new MotorConfiguration();
    public static final MotorConfiguration kRightHoodMotorConfiguration = new MotorConfiguration();
    public static final MotorConfiguration kRightFlywheelMotorConfiguration = new MotorConfiguration();

    public static final ComplexGearRatio kHoodMotorGearRatio = new ComplexGearRatio((12.0 / 30.0), (28.0 / 324.0));
    public static final ComplexGearRatio kFlywheelMotorGearRatio = new ComplexGearRatio((28.0 / 36.0), (30.0 / 36.0));

    public static final double kTopFlywheelRadiusMeters = Units.inchesToMeters(1.0);
    public static final double kBottomFlywheelRadiusMeters = Units.inchesToMeters(2.0);

    public static final Rotation2d kHoodHardstopPosition = Rotation2d.fromDegrees(28.01);
    public static final Rotation2d kHoodMaximumRotation = Rotation2d.fromDegrees(62.227);

    public static final LinearVelocity kShooterStowVelocity = MetersPerSecond.of(0.0);

    static {
        kLeftHoodMotorConfiguration.gearRatio = kHoodMotorGearRatio;
        kLeftHoodMotorConfiguration.idleState = IdleState.kBrake;
        kLeftHoodMotorConfiguration.isArm = true;
        kLeftHoodMotorConfiguration.withFOC = true;
        kLeftHoodMotorConfiguration.isInverted = false;
        kLeftHoodMotorConfiguration.mode = MotorMode.kServo;

        kLeftHoodMotorConfiguration.maxVelocity = kLeftHoodMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
        kLeftHoodMotorConfiguration.maxAcceleration = kLeftHoodMotorConfiguration.maxVelocity;
        kLeftHoodMotorConfiguration.altV = kLeftHoodMotorConfiguration.maxVelocity / 2.0;
        kLeftHoodMotorConfiguration.altA = kLeftHoodMotorConfiguration.maxAcceleration;
        kLeftHoodMotorConfiguration.altJ = 1000.0;
        kLeftHoodMotorConfiguration.kP = 800.02000045776367;
        kLeftHoodMotorConfiguration.kD = 0;
        kLeftHoodMotorConfiguration.kI = 0;
        kLeftHoodMotorConfiguration.currentLimit = 20;
    }

    static {
        kRightHoodMotorConfiguration.gearRatio = kHoodMotorGearRatio;
        kRightHoodMotorConfiguration.isArm = true;
        kRightHoodMotorConfiguration.isInverted = true;
        kRightHoodMotorConfiguration.withFOC = true;
        kRightHoodMotorConfiguration.idleState = IdleState.kBrake;
        kRightHoodMotorConfiguration.mode = MotorMode.kServo;

        kRightHoodMotorConfiguration.maxVelocity = kRightHoodMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
        kRightHoodMotorConfiguration.maxAcceleration = kRightHoodMotorConfiguration.maxVelocity;
        kRightHoodMotorConfiguration.altA = kRightHoodMotorConfiguration.maxAcceleration;
        kRightHoodMotorConfiguration.altV = kRightHoodMotorConfiguration.maxVelocity / 2.0;
        kRightHoodMotorConfiguration.altJ = 1000.0;
        kRightHoodMotorConfiguration.kP = 800.02000045776367;
        kRightHoodMotorConfiguration.kD = 0;
        kRightHoodMotorConfiguration.kI = 0;
        kRightHoodMotorConfiguration.currentLimit = 20;
    }

    static {
        kLeftFlywheelMotorConfiguration.isArm = false;
        kLeftFlywheelMotorConfiguration.isInverted = false;
        kLeftFlywheelMotorConfiguration.withFOC = true;
        kLeftFlywheelMotorConfiguration.gearRatio = kFlywheelMotorGearRatio;
        kLeftFlywheelMotorConfiguration.idleState = IdleState.kCoast;
        kLeftFlywheelMotorConfiguration.mode = MotorMode.kFlywheel;
        kLeftFlywheelMotorConfiguration.finalDiameterMeters = kBottomFlywheelRadiusMeters * 2;

        kLeftFlywheelMotorConfiguration.maxVelocity = kLeftFlywheelMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM);
        kLeftFlywheelMotorConfiguration.maxAcceleration = kLeftFlywheelMotorConfiguration.maxVelocity;
        kLeftFlywheelMotorConfiguration.altV = kLeftFlywheelMotorConfiguration.maxVelocity / 2.0;
        kLeftFlywheelMotorConfiguration.altA = kLeftFlywheelMotorConfiguration.maxAcceleration;
        kLeftFlywheelMotorConfiguration.altJ = 1000.0;

        kLeftFlywheelMotorConfiguration.kP = 0.08;
        kLeftFlywheelMotorConfiguration.kI = 0.0;
        kLeftFlywheelMotorConfiguration.kD = 0.0;

        kLeftFlywheelMotorConfiguration.kV = 0.075;
        kLeftFlywheelMotorConfiguration.kA = 0.05;
        kLeftFlywheelMotorConfiguration.kS = 0.1;
        kLeftFlywheelMotorConfiguration.kG = 0.0;
        kLeftFlywheelMotorConfiguration.kFF = 0.0;

        kLeftFlywheelMotorConfiguration.currentLimit = 90;
    }


    static {
        kRightFlywheelMotorConfiguration.isArm = false;
        kRightFlywheelMotorConfiguration.isInverted = true;
        kRightFlywheelMotorConfiguration.withFOC = true;
        kRightFlywheelMotorConfiguration.gearRatio = kFlywheelMotorGearRatio;
        kRightFlywheelMotorConfiguration.idleState = IdleState.kCoast;
        kRightFlywheelMotorConfiguration.mode = MotorMode.kFlywheel;
        kRightFlywheelMotorConfiguration.finalDiameterMeters = kBottomFlywheelRadiusMeters * 2;

        kRightFlywheelMotorConfiguration.maxVelocity = kRightFlywheelMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM);
        kRightFlywheelMotorConfiguration.maxAcceleration = kRightFlywheelMotorConfiguration.maxVelocity;
        kRightFlywheelMotorConfiguration.altV = kRightFlywheelMotorConfiguration.maxVelocity / 2.0;
        kRightFlywheelMotorConfiguration.altA = kRightFlywheelMotorConfiguration.maxAcceleration;
        kRightFlywheelMotorConfiguration.altJ = 1000.0;

        kRightFlywheelMotorConfiguration.kP = 0.08;
        kRightFlywheelMotorConfiguration.kI = 0.0;
        kRightFlywheelMotorConfiguration.kD = 0.0;

        kRightFlywheelMotorConfiguration.kV = 0.075;
        kRightFlywheelMotorConfiguration.kA = 0.05;
        kRightFlywheelMotorConfiguration.kS = 0.1;
        kRightFlywheelMotorConfiguration.kG = 0.0;
        kRightFlywheelMotorConfiguration.kFF = 0.0;

        kRightFlywheelMotorConfiguration.currentLimit = 90;
    }
}
