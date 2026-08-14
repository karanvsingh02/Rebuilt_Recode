package team5427.frc.robot.subsystems.Indexer;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Distance;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorUtil;
import team5427.lib.motors.MotorConfiguration.MotorMode;

public class IndexerConstants {
    public static MotorConfiguration kIndexerMotorConfiguration = new MotorConfiguration();
    public static MotorConfiguration kHopperMotorConfiguration = new MotorConfiguration();

    public static final CANDeviceId kLeftIndexerMotorCanId = new CANDeviceId(26);
    public static final CANDeviceId kRightIndexerMotorCanId = new CANDeviceId(27);
    public static final CANDeviceId kHopperMotorCanId = new CANDeviceId(28);

    public static final Distance kIndexerFlyWheelRadius = Inches.of(1);
    public static final Distance kHopperFlyWheelRadius = Inches.of(0.5);

    static{
        kHopperMotorConfiguration.gearRatio = new ComplexGearRatio(18/24);
        kHopperMotorConfiguration.mode = MotorMode.kFlywheel;
        kHopperMotorConfiguration.isArm = false;
        kHopperMotorConfiguration.isInverted = true;
        kHopperMotorConfiguration.idleState = MotorConfiguration.IdleState.kCoast;
        kHopperMotorConfiguration.currentLimit = 40;
        kHopperMotorConfiguration.finalDiameterMeters = kHopperFlyWheelRadius.times(2.0).in(Meters);
        kHopperMotorConfiguration.withFOC = true;
        kHopperMotorConfiguration.maxVelocity = kHopperMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM) / 5;
        kHopperMotorConfiguration.maxAcceleration = kHopperMotorConfiguration.maxAcceleration * 3;
        kHopperMotorConfiguration.kP = 0.2;
        kHopperMotorConfiguration.kV = 0.5; 
        kHopperMotorConfiguration.kS = 0.5;
    }

    static{

        kIndexerMotorConfiguration.gearRatio = new ComplexGearRatio(1);
        kIndexerMotorConfiguration.mode = MotorMode.kFlywheel;
        kIndexerMotorConfiguration.isArm = false;
        kIndexerMotorConfiguration.isInverted = false;
        kIndexerMotorConfiguration.idleState = MotorConfiguration.IdleState.kBrake;
        kIndexerMotorConfiguration.currentLimit = 60;
        kIndexerMotorConfiguration.finalDiameterMeters = kIndexerFlyWheelRadius.times(2.0).in(Meters);
        kIndexerMotorConfiguration.withFOC = false;
        kIndexerMotorConfiguration.maxVelocity = kIndexerMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM);
        kIndexerMotorConfiguration.maxAcceleration = kIndexerMotorConfiguration.maxAcceleration * 3;
        kIndexerMotorConfiguration.kP = 0.4;
        kIndexerMotorConfiguration.kV = 0.1;
        kIndexerMotorConfiguration.kA = 0.1;
        kIndexerMotorConfiguration.kS = 0.1;
    }

}
