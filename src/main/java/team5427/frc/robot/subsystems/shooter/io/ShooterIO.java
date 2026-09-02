package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.Amp;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volt;
import static edu.wpi.first.units.Units.Volts;

import org.checkerframework.checker.units.qual.Angle;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;

public interface ShooterIO {
    
    public static class ShooterIOInputs {
        public AngularVelocity leftFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
        public LinearVelocity leftFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);
        public AngularAcceleration leftFlywheelMotorAngularAcceleration = RotationsPerSecondPerSecond.of(0.0);
        public double leftHoodMotorPosition = 0.0;
        public double leftHoodMotorAngularVelocityRadians = 0.0;
        public Voltage leftFlywheelLeaderMotorVoltage = Volts.of(0);
        public Current leftFlywheelLeaderMotorCurrent = Amp.of(0.0);
        
        public Voltage leftFlywheelFollowerMotorVoltage = Volts.of(0);
        public Current leftFlywheelFollowerMotorCurrent = Amp.of(0.0);


        public AngularVelocity rightFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
        public LinearVelocity rightFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);
        public double rightHoodMotorPosition = 0.0;
        public double rightHoodMotorAngularVelocityRadians = 0.0;
        public Voltage rightFlywheelLeaderMotorVoltage = Volts.of(0);
        public Current rightFlywheelLeaderMotorCurrent = Amp.of(0.0);
        public Voltage rightFlywheelFollowerMotorVoltage = Volts.of(0);
        public Current rightFlywheelFollowerMotorCurrent = Amp.of(0.0);
    }
    

    public default void updateInputs(ShooterIOInputs inputs) {}
    
    public default void setLeftHoodAngle(Rotation2d a) {}
    public default void setLeftHoodAngle(Angle a) {}
    public default void setLeftHoodAngle(double a) {}
    public default void setLeftFlywheelSpeed(LinearVelocity v) {}
    public default void setLeftFlywheelSpeed(AngularVelocity v) {}
    public default void setLeftFlywheelSpeed(double angularVelocity) {}
    public default void resetLeftHoodAngle(Rotation2d a) {}

    public default void setRightHoodAngle(Rotation2d a) {}
    public default void setRightHoodAngle(Angle a) {}
    public default void setRightHoodAngle(double a) {}
    public default void setRightFlywheelSpeed(LinearVelocity v) {}
    public default void setRightFlywheelSpeed(AngularVelocity v) {}
    public default void setRightFlywheelSpeed(double angularVelocity) {}
    public default void resetRightHoodAngle(Rotation2d a) {}
}
