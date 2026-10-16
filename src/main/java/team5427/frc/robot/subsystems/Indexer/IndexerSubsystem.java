package team5427.frc.robot.subsystems.Indexer;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.logging.Logger;

import org.littletonrobotics.junction.LoggedPowerDistribution;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.Indexer.io.IndexerIO;
import team5427.frc.robot.subsystems.Indexer.io.IndexerIOTalonFX;
import team5427.frc.robot.subsystems.intake.io.IntakeIOSim;


public class IndexerSubsystem extends SubsystemBase{
    private AngularVelocity indexerSpeed;
    private AngularVelocity hopperSpeed;

    private IndexerIO io;
    private IndexerInputsAutoLogged inputsAutoLogged;

    public static IndexerSubsystem m_instance;

    public static IndexerSubsystem getInstance() {
        if (m_instance == null) {
            m_instance = new IndexerSubsystem(Optional.empty());
        }
        return m_instance;
    }

    private IndexerSubsystem(Optional<IndexerIO> io) {
        inputsAutoLogged = new IndexerInputsAutoLogged();
        switch (Constants.currentMode) {
            case REAL:
                io = new IndexerIOTalonFX();
                break;
            default:
                break;
        }

        indexerSpeed = RotationsPerSecond.of(0);
        hopperSpeed = RotationsPerSecond.of(0);
    }

    @Override
    public void periodic(){
        io.updateInputs(inputsAutoLogged);
        io.setIndexerVelocity(hopperSpeed);
        io.setIndexerVelocity(indexerSpeed);
        
        Logger.processInputs("Indexer/Inputs", inputsAutoLogged);
        //add log() later
    }

    public void setIndexerSpeed(AngularVelocity speed) {
        indexerSpeed = speed;
    }

    public void setHopperSpeed(AngularVelocity speed) {
        hopperSpeed = speed;
    }

}
