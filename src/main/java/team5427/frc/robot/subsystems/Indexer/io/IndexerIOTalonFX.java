package team5427.frc.robot.subsystems.Indexer.io;

import static edu.wpi.first.units.Units.MetersPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.Indexer.IndexerConstants;
import team5427.frc.robot.subsystems.Indexer.io.IndexerIO.IndexerIoInputs;
import team5427.lib.motors.SteelTalonFX;

public class IndexerIOTalonFX implements IndexerIO {
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

    private boolean isLeftIndexerMotorDisabled = false;
    private boolean isRightIndexerMotorDisabled = false;
    private boolean isHopperMotorDisabled = false;

    public IndexerIOTalonFX() {
        leftIndexerMotor = new SteelTalonFX(IndexerConstants.kLeftIndexerMotorCanId);
        rightIndexerMotor = new SteelTalonFX(IndexerConstants.kRightIndexerMotorCanId);
        hopperMotor = new SteelTalonFX(IndexerConstants.kHopperMotorCanId);

        leftIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
        rightIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
        hopperMotor.apply(IndexerConstants.kHopperMotorConfiguration);
        
        leftIndexerMotor.setEncoderPosition(0);
        rightIndexerMotor.setEncoderPosition(0);
        hopperMotor.setEncoderPosition(0);

        leftIndexerMotorAngularVelocity = leftIndexerMotor.getTalonFX().getVelocity();
        rightIndexerMotorAngularVelocity = rightIndexerMotor.getTalonFX().getVelocity();
        hopperMotorAngularVelocity = hopperMotor.getTalonFX().getVelocity();

        leftIndexerMotorVoltage = leftIndexerMotor.getTalonFX().getMotorVoltage();
        rightIndexerMotorVoltage = rightIndexerMotor.getTalonFX().getMotorVoltage();
        hopperMotorVoltage = hopperMotor.getTalonFX().getMotorVoltage();

        leftIndexerMotorCurrent = leftIndexerMotor.getTalonFX().getStatorCurrent();
        rightIndexerMotorCurrent = rightIndexerMotor.getTalonFX().getStatorCurrent();
        hopperMotorCurrent = hopperMotor.getTalonFX().getStatorCurrent();

        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kHighPriorityUpdateFrequency, 
        leftIndexerMotorAngularVelocity, rightIndexerMotorAngularVelocity, hopperMotorAngularVelocity);

        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kMediumPriorityUpdateFrequency,
        leftIndexerMotorCurrent, rightIndexerMotorCurrent, hopperMotorCurrent);

        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kLowPriorityUpdateFrequency, 
        leftIndexerMotorVoltage, rightIndexerMotorVoltage, hopperMotorVoltage);
    }

    public void updateInputs(IndexerIoInputs inputs){
        inputs.leftIndexerMotorConnected = leftIndexerMotor.getTalonFX().isConnected();
        inputs.rightIndexerMotorConnected = rightIndexerMotor.getTalonFX().isConnected();
        inputs.hopperMotorConnected = hopperMotor.getTalonFX().isConnected();

        BaseStatusSignal.refreshAll(leftIndexerMotorAngularVelocity, rightIndexerMotorAngularVelocity, hopperMotorAngularVelocity,
        leftIndexerMotorCurrent, rightIndexerMotorCurrent, hopperMotorCurrent, 
        leftIndexerMotorVoltage, rightIndexerMotorVoltage, hopperMotorVoltage);

        inputs.leftIndexerMotorAngularVelocity = leftIndexerMotorAngularVelocity.getValue();
        inputs.rightIndexerMotorAngularVelocity = rightIndexerMotorAngularVelocity.getValue();
        inputs.hopperMotorAngularVelocity = hopperMotorAngularVelocity.getValue();

        inputs.leftIndexerMotorCurrent = leftIndexerMotorCurrent.getValue();
        inputs.rightIndexerMotorCurrent = rightIndexerMotorCurrent.getValue();
        inputs.hopperMotorCurrent = hopperMotorCurrent.getValue();

        inputs.leftIndexerMotorVoltage = leftIndexerMotorVoltage.getValue();
        inputs.rightIndexerMotorVoltage = rightIndexerMotorVoltage.getValue();
        inputs.hopperMotorVoltage = hopperMotorVoltage.getValue();

        inputs.leftIndexerMotorLinearVelocity = MetersPerSecond.of(leftIndexerMotor.getEncoderVelocity(leftIndexerMotorAngularVelocity));
        inputs.rightIndexerMotorLinearVelocity = MetersPerSecond.of(rightIndexerMotor.getEncoderVelocity(rightIndexerMotorAngularVelocity));
        inputs.hopperMotorLinearVelocity = MetersPerSecond.of(hopperMotor.getEncoderVelocity(hopperMotorAngularVelocity));
    }

    public void setIndexerVelocity(AngularVelocity velocity){
        if(!isLeftIndexerMotorDisabled && !isRightIndexerMotorDisabled){
            leftIndexerMotor.setSetpoint(velocity);
            rightIndexerMotor.setSetpoint(velocity);
        }
    }

    public void setHopperVelocity(AngularVelocity velocity){
        if(!isLeftIndexerMotorDisabled){
            hopperMotor.setSetpoint(velocity);
        }
    }

    public void setIndexerVelocity(LinearVelocity velocity){
        if(!isLeftIndexerMotorDisabled && !isRightIndexerMotorDisabled){
            leftIndexerMotor.setSetpoint(velocity);
            rightIndexerMotor.setSetpoint(velocity);
        }
    }

    public void setHopperVelocity(LinearVelocity velocity){
        if(!isHopperMotorDisabled){
            hopperMotor.setSetpoint(velocity);
        }
    }

    public void disableLeftIndexerMotor(boolean shouldDisable){
        isLeftIndexerMotorDisabled = shouldDisable;
        if(shouldDisable){
            leftIndexerMotor.setRawPercentage(0);
        }
    }

    public void disableRightIndexerMotor(boolean shouldDisable){
        isRightIndexerMotorDisabled = shouldDisable;
        if(shouldDisable){
            rightIndexerMotor.setRawPercentage(0);
        }
    }

    public void disableHopperMotor(boolean shouldDisable){
        isHopperMotorDisabled = shouldDisable;
        if(shouldDisable){
            hopperMotor.setRawPercentage(0);
        }
    }
}
