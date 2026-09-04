package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Radian;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.lib.motors.SteelTalonFX;

public class ShooterIOTaloxFX implements ShooterIO {
    private SteelTalonFX leftHoodMotor;
    private SteelTalonFX leftFlywheelLeaderMotor;
    private SteelTalonFX leftFlywheelFollowerMotor;
    private SteelTalonFX rightHoodMotor;
    private SteelTalonFX rightFlywheelLeaderMotor;
    private SteelTalonFX rightFlywheelFollowerMotor;

    private StatusSignal<AngularVelocity> leftFlywheelMotorAngularVelocity;
    private StatusSignal<AngularVelocity> rightFlywheelMotorAngularVelocity;
    private StatusSignal<AngularAcceleration> leftFlywheelMotorAngularAcceleration;
    private StatusSignal<AngularAcceleration> rightFlywheelMotorAngularAcceleration;
    private StatusSignal<Voltage> leftFlywheelLeaderMotorVoltage;
    private StatusSignal<Voltage> leftFlywheelFollowerMotorVoltage;
    private StatusSignal<Current> leftFlywheelLeaderMotorCurrent;
    private StatusSignal<Current> leftFlywheelFollowerMotorCurrent;

    private StatusSignal<AngularVelocity> leftHoodMotorAngularVelocity;
    private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
    private StatusSignal<Angle> leftHoodMotorPosition;
    private StatusSignal<Angle> rightHoodMotorPosition;
    private StatusSignal<Voltage> rightFlywheelLeaderMotorVoltage;
    private StatusSignal<Voltage> rightFlywheelFollowerMotorVoltage;
    private StatusSignal<Current> rightFlywheelLeaderMotorCurrent;
    private StatusSignal<Current> rightFlywheelFollowerMotorCurrent;


    public ShooterIOTaloxFX() {
        leftHoodMotor = new SteelTalonFX(ShooterConstants.kLeftHoodMotorCanId);
        leftFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelLeaderMotorCanId);
        leftFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelFollowMotorCanId);

        rightHoodMotor = new SteelTalonFX(ShooterConstants.kRightHoodMotorCanId);
        rightFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelLeaderMotorCanId);
        rightFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelFollowMotorCanId);

        leftHoodMotor.apply(ShooterConstants.kLeftHoodMotorConfiguration);
        leftFlywheelLeaderMotor.apply(ShooterConstants.kLeftFlywheelMotorConfiguration);
        leftFlywheelFollowerMotor.apply(ShooterConstants.kLeftFlywheelMotorConfiguration);
        rightHoodMotor.apply(ShooterConstants.kRightHoodMotorConfiguration);
        rightFlywheelLeaderMotor.apply(ShooterConstants.kRightFlywheelMotorConfiguration);
        rightFlywheelFollowerMotor.apply(ShooterConstants.kRightFlywheelMotorConfiguration);

        leftFlywheelLeaderMotor.setEncoderPosition(0.0);
        leftFlywheelFollowerMotor.setEncoderPosition(0.0);
        rightFlywheelLeaderMotor.setEncoderPosition(0.0);
        rightFlywheelFollowerMotor.setEncoderPosition(0.0);

        leftHoodMotor.setEncoderPosition(ShooterConstants.kHoodHardstopPosition);
        rightHoodMotor.setEncoderPosition(ShooterConstants.kHoodHardstopPosition);

        leftFlywheelFollowerMotor
        .getTalonFX()
        .setControl(
            new Follower(
                leftFlywheelLeaderMotor.getTalonFX().getDeviceID(), MotorAlignmentValue.Aligned));

        rightFlywheelFollowerMotor
        .getTalonFX()
        .setControl(
            new Follower(
                rightFlywheelLeaderMotor.getTalonFX().getDeviceID(), MotorAlignmentValue.Aligned));

        leftHoodMotorPosition = leftHoodMotor.getTalonFX().getPosition();
        leftHoodMotorAngularVelocity = leftHoodMotor.getTalonFX().getVelocity();
        leftFlywheelMotorAngularVelocity = leftFlywheelLeaderMotor.getTalonFX().getVelocity();
        leftFlywheelMotorAngularAcceleration = leftFlywheelLeaderMotor.getTalonFX().getAcceleration();
        leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
        leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotor.getTalonFX().getMotorVoltage();
        leftFlywheelLeaderMotorCurrent = leftFlywheelLeaderMotor.getTalonFX().getSupplyCurrent();
        leftFlywheelFollowerMotorCurrent = rightFlywheelFollowerMotor.getTalonFX().getSupplyCurrent();

        rightHoodMotorPosition = rightHoodMotor.getTalonFX().getPosition();
        rightHoodMotorAngularVelocity = rightHoodMotor.getTalonFX().getVelocity();
        rightFlywheelMotorAngularVelocity = rightFlywheelLeaderMotor.getTalonFX().getVelocity();
        rightFlywheelMotorAngularAcceleration = rightFlywheelLeaderMotor.getTalonFX().getAcceleration();
        rightFlywheelLeaderMotorVoltage = rightFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
        rightFlywheelFollowerMotorVoltage = rightFlywheelFollowerMotor.getTalonFX().getMotorVoltage();
        rightFlywheelLeaderMotorCurrent = rightFlywheelFollowerMotor.getTalonFX().getSupplyCurrent();
        rightFlywheelFollowerMotorCurrent = rightFlywheelFollowerMotor.getTalonFX().getSupplyCurrent();
    }

    @Override
  public void setLeftHoodAngle(Rotation2d angle) {
     leftHoodMotor.setSetpoint(angle);
  }

  /* 
  @Override
  public void setLeftHoodAngle(Angle a) {
    leftHoodMotor.setSetpoint(a);
  }
    */

  @Override
  public void setLeftHoodAngle(double radians) {
    leftHoodMotor.setSetpoint(Radians.of(radians));
  }

  @Override
  public void setLeftFlywheelSpeed(LinearVelocity velocity) {
    leftFlywheelLeaderMotor.setSetpoint(velocity);
  }
  
  @Override
  public void setLeftFlywheelSpeed(double rotationsPerSecond) {
    leftFlywheelLeaderMotor.setSetpoint(RotationsPerSecond.of(rotationsPerSecond));
  }
  @Override
  public void resetLeftHoodAngle(Rotation2d angle) {
    leftHoodMotor.setEncoderPosition(angle);
  }

  @Override
  public void setLeftFlywheelSpeed(AngularVelocity  velocity) {
    leftFlywheelLeaderMotor.setSetpoint(velocity);
  }
  @Override
  public void setRightHoodAngle(Rotation2d angle) {
    rightHoodMotor.setSetpoint(angle);
  }

  /*
  @Override
  public void setRightHoodAngle(Angle angle) {
    rightHoodMotor.setSetpoint(angle);
  } */

  @Override
  public void setRightHoodAngle(double radians) {
    rightHoodMotor.setSetpoint(Radians.of(radians));
  }

  @Override
  public void setRightFlywheelSpeed(LinearVelocity velocity) {
    rightFlywheelLeaderMotor.setSetpoint(velocity);
  }
  @Override
  public void setRightFlywheelSpeed(AngularVelocity velocity) {
    rightFlywheelLeaderMotor.setSetpoint(velocity);
  }

  @Override
  public void setRightFlywheelSpeed(double rotationsPerSecond) {
    rightFlywheelLeaderMotor.setSetpoint(RotationsPerSecond.of(rotationsPerSecond));
  }

  @Override
  public void resetRightHoodAngle(Rotation2d angle) {
    rightHoodMotor.setEncoderPosition(angle);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    BaseStatusSignal.refreshAll(
    leftFlywheelMotorAngularAcceleration,
    rightFlywheelMotorAngularAcceleration,
    leftHoodMotorPosition,
    rightHoodMotorPosition,
    leftFlywheelMotorAngularVelocity,
    rightFlywheelMotorAngularVelocity,
    leftHoodMotorAngularVelocity,
    rightHoodMotorAngularVelocity,
    leftFlywheelFollowerMotorVoltage,
    leftFlywheelLeaderMotorVoltage,
    rightFlywheelFollowerMotorVoltage,
    rightFlywheelLeaderMotorVoltage,
    rightFlywheelFollowerMotorCurrent,
    rightFlywheelLeaderMotorCurrent,
    leftFlywheelFollowerMotorCurrent,
    leftFlywheelLeaderMotorCurrent);

    inputs.leftHoodMotorPositionRadians = leftHoodMotorPosition.getValue().in(Radians);
    inputs.leftHoodMotorAngularVelocityRadiansPerSecond =
        leftHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.leftFlywheelAngularAcceleration = leftFlywheelMotorAngularAcceleration.getValue();
    inputs.leftFlywheelMotorAngularVelocity = leftFlywheelMotorAngularVelocity.getValue();
    inputs.leftFlywheelMotorLinearVelocity =
    MetersPerSecond.of(
        (ShooterConstants.kTopFlywheelRadiusMeters
                + ShooterConstants.kBottomFlywheelRadiusMeters)
            * leftFlywheelMotorAngularVelocity.getValue().in(RadiansPerSecond));
    inputs.leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotorVoltage.getValue();
    inputs.leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotorVoltage.getValue();

    inputs.rightHoodMotorPositionRadians = rightHoodMotorPosition.getValue().in(Radians);
    inputs.rightHoodMotorAngularVelocityRadiansPerSecond =
        rightHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.rightFlywheelAngularAcceleration = rightFlywheelMotorAngularAcceleration.getValue();
    inputs.rightFlywheelMotorAngularVelocity = rightFlywheelMotorAngularVelocity.getValue();
    inputs.rightFlywheelMotorLinearVelocity =
    MetersPerSecond.of(
        (ShooterConstants.kTopFlywheelRadiusMeters
                + ShooterConstants.kBottomFlywheelRadiusMeters)
            * rightFlywheelMotorAngularVelocity.getValue().in(RadiansPerSecond));
    inputs.rightFlywheelLeaderMotorVoltage = rightFlywheelLeaderMotorVoltage.getValue();
    inputs.rightFlywheelFollowerMotorVoltage = rightFlywheelFollowerMotorVoltage.getValue();
    inputs.leftFlywheelLeaderMotorCurrent = leftFlywheelLeaderMotorCurrent.getValue();
    inputs.leftFlywheelFollowerMotorCurrent = leftFlywheelFollowerMotorCurrent.getValue();
    inputs.rightFlywheelLeaderMotorCurrent = rightFlywheelLeaderMotorCurrent.getValue();
    inputs.rightFlywheelFollowerMotorCurrent = rightFlywheelFollowerMotorCurrent.getValue();
  }
}