package team5427.frc.robot.commands.indexer;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.Indexer.IndexerConstants;
import team5427.frc.robot.subsystems.Indexer.IndexerSubsystem;

public class IndexerEject extends Command{
    private IndexerSubsystem indexerSubsystem;

    public IndexerEject(){
        indexerSubsystem = IndexerSubsystem.getInstance();
        addRequirements(indexerSubsystem);
    }

    @Override
    public void execute(){
        indexerSubsystem.setIndexerSpeed(RotationsPerSecond.of(IndexerConstants.kIndexerMotorConfiguration.maxVelocity * -1));
        indexerSubsystem.setIndexerSpeed(RotationsPerSecond.of(IndexerConstants.kHopperMotorConfiguration.maxVelocity * -1));
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupt){
        indexerSubsystem.setIndexerSpeed(IndexerConstants.kIndexerStowVelocity);
        indexerSubsystem.setHopperSpeed(IndexerConstants.kIndexerStowVelocity);
    }
}
