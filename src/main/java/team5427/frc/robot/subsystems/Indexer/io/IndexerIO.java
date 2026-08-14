package team5427.frc.robot.subsystems.Indexer.io;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;

public interface IndexerIO {
    @AutoLog
    public static class IndexerIoInputs{
        public boolean leftIndexerMotorConnected = false;
        public boolean rightIndexerMotorConnected = false;
        public boolean hopperMotorConnected = false;

        public AngularVelocity leftIndexerMotorAngularVelocity = RotationsPerSecond.of(0);
        public AngularVelocity rightIndexerMotorAngularVelocity = RotationsPerSecond.of(0);
        public AngularVelocity hopperMotorAngularVelocity = RotationsPerSecond.of(0);

        public LinearVelocity leftIndexerMotorLinearVelocity = MetersPerSecond.of(0);
        public LinearVelocity rightIndexerMotorLinearVelocity = MetersPerSecond.of(0);
        public LinearVelocity hopperMotorLinearVelocity = MetersPerSecond.of(0);

        public Voltage leftIndexerMotorVoltage = Volts.of(0);
        public Voltage rightIndexerMotorVoltage = Volts.of(0);
        public Voltage hopperMotorVoltage = Volts.of(0);

        public Current leftIndexerMotorCurrent = Amps.of(0);
        public Current rightIndexerMotorCurrent = Amps.of(0);
        public Current hopperMotorCurrent = Amps.of(0);

    }

    public default void updateInputs(IndexerIoInputs inputs) {}

    public default void setIndexerVelocity(AngularVelocity velocity) {}

    public default void setHopperVelocity(AngularVelocity velocity) {}
    
}
