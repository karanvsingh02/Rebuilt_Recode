package team5427.frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.Indexer.IndexerConstants;
import team5427.frc.robot.subsystems.Indexer.IndexerSubsystem;

public class IndexerStow extends Command{
    private IndexerSubsystem indexerSubsystem;
    public IndexerStow(){
        indexerSubsystem = IndexerSubsystem.getInstance();
        addRequirements(indexerSubsystem);
    }

    @Override
    public void execute(){
        indexerSubsystem.setIndexerSpeed(IndexerConstants.kIndexerStowVelocity);
        indexerSubsystem.setHopperSpeed(IndexerConstants.kIndexerStowVelocity);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
