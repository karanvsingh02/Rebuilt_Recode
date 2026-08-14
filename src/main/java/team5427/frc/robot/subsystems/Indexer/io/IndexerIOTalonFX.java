package team5427.frc.robot.subsystems.Indexer.io;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.subsystems.Indexer.IndexerConstants;
import team5427.lib.motors.SteelTalonFX;

public class IndexerIOTalonFX {
    private SteelTalonFX leftIndexerMotor;
    private SteelTalonFX rightIndexerMotor;
    private SteelTalonFX hopperMotor;

    private StatusSignal<AngularVelocity> leftIndexerMotorAngularVelocity;
    private StatusSignal<AngularVelocity> rightIndexerMotorAngularVelocity;
    private StatusSignal<AngularVelocity> hopperMotorAngularVelocity;

    private StatusSignal<LinearVelocity> leftIndexerMotorLinearVelocity;
    private StatusSignal<LinearVelocity> rightIndexerMotorLinearVelocity;
    private StatusSignal<LinearVelocity> hopperMotorLinearVelocity;

    private StatusSignal<Voltage> leftIndexerMotorVoltage;
    private StatusSignal<Voltage> rightIndexerMotorVoltage;
    private StatusSignal<Voltage> hopperMotorVoltage;

    private StatusSignal<Current> leftIndexerMotorCurrent;
    private StatusSignal<Current> rightIndexerMotorCurrent;
    private StatusSignal<Current> hopperMotorCurrent;

    public IndexerIOTalonFX() {
        leftIndexerMotor = new SteelTalonFX(IndexerConstants.kLeftIndexerMotorCanId);
        rightIndexerMotor = new SteelTalonFX(IndexerConstants.kRightIndexerMotorCanId);
        hopperMotor = new SteelTalonFX(IndexerConstants.kHopperMotorCanId);

        leftIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
        rightIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
        hopperMotor.apply(IndexerConstants.kHopperMotorConfiguration);
        //NOT DONE
    }
}
