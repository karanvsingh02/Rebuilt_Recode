package team5427.frc.robot.subsystems.shooter.io;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
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
    private StatusSignal<Current> lefFlywheelLeaderMotorCurrent;
    private StatusSignal<Current> leftFlywheelFollowerMotorCurrent;

    private StatusSignal<AngularVelocity> leftHoodMotorAngularVelocity;
    private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
    private StatusSignal<Angle> leftHoodMotorPosition;
    private StatusSignal<Angle> rightHoodMotorPosition;


    public ShooterIOTaloxFX() {
        leftHoodMotor = new SteelTalonFX(ShooterConstants.kLeftHoodMotorCanId);
        leftFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelLeaderMotorCanId);
        leftFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelFollowMotorCanId);

        rightHoodMotor = new SteelTalonFX(ShooterConstants.kRightHoodMotorCanId);
        rightFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelLeaderMotorCanId);
        rightFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelFollowMotorCanId);


    }
}