package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import java.lang.constant.Constable;
import java.util.Optional;

import org.littletonrobotics.junction.Logger;

import team5427.frc.robot.Constants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import team5427.frc.robot.subsystems.shooter.io.ShooterIO;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOTaloxFX;
import team5427.frc.robot.subsystems.shooter.io.ShooterIO.ShooterIOInputs;
import team5427.lib.kinematics.shooter.ShotCalculator;

public class ShooterSubsystem extends SubsystemBase {
    private ShooterIO io;
    private ShooterIOInputsAutoLogged inputsAutoLogged;
    public static ShooterSubsystem m_instance;
    private ShotCalculator sc;

    @Getter private LinearVelocity leftShooterVelocity = MetersPerSecond.of(0);
    @Getter private LinearVelocity rightShooterVelocity = MetersPerSecond.of(0);
    @Getter private Rotation2d leftSAngle = Rotation2d.kZero;
    @Getter private Rotation2d rightSAngle = Rotation2d.kZero;

    public static ShooterSubsystem getInstance() {
        if (m_instance == null) {
            m_instance = new ShooterSubsystem();
        }

        return m_instance;
    }

    
    private ShooterSubsystem() {
        shooterAutoLogged = new ShooterIOInputsAutoLogged();
        ShotCalculator.Config config = new ShotCalculator.Config();
        config.launcherOffsetX = 0.0;
        config.launcherOffsetY = 0.0;
        sc = new ShotCalculator(config);


        switch (Constants.currentMode) {
            case REAL:
                io = new ShooterIOTaloxFX();
                break;
            case SIM:
                // havent implemented sim for recode
                break;
        }

        leftSAngle = ShooterConstants.kHoodHardstopPosition;
        rightSAngle = ShooterConstants.kHoodHardstopPosition;
    }


    @Override
    public void periodic() {
        io.updateInputs(inputsAutoLogged);

        io.setLeftFlywheelSpeed(leftShooterVelocity);
        io.setRightFlywheelSpeed(rightShooterVelocity);
        io.setLeftHoodAngle(leftSAngle);
        io.setRightHoodAngle(rightSAngle);

        Logger.processInputs("Shooter/Inputs", inputsAutoLogged);
        log();
    }

    public LinearVelocity getCurrentLeftShooterSpeed() {
        return inputsAutoLogged.leftFlywheelMotorLinearVelocity;
    }

    public LinearVelocity getCurrentRightShooterSpeed() {
        return inputsAutoLogged.rightFlywheelMotorLinearVelocity;
    }
    public Rotation2d getCurrentLeftShooterAngle() {
        return Rotation2d.fromRadians(inputsAutoLogged.leftHoodMotorPositionRadians);
    }

    public Rotation2d getCurrentRightShooterAngle() {
        return Rotation2d.fromRadians(inputsAutoLogged.rightHoodMotorPositionRadians);
    }

    public void setLeftShooterSpeed(LinearVelocity v) {
        leftShooterVelocity = v;
    }

    public void setRightShooterSpeed(LinearVelocity v) {
        rightShooterVelocity = v;
    }

    public void setLeftShooterAngle(Rotation2d a) {
        leftSAngle = a;
    }

    public void setRightShooterAngle(Rotation2d a) {
        rightSAngle = a;
    }


    public void log() {
        Logger.recordOutput("Shooter/RightShooterSpeedSetpoint", rightShooterVelocity);
        Logger.recordOutput("Shooter/LeftShooterSpeedSetpoint", leftShooterVelocity);

        Logger.recordOutput("Shooter/RightHoodAngleSetpoint", rightSAngle);
        Logger.recordOutput("Shooter/LeftHoodAngleSetpoint", leftSAngle);

        Logger.recordOutput("Shooter/RightShooterSpeed", getRightShooterVelocity());
        Logger.recordOutput("Shooter/LeftShooterSpeed", getLeftShooterVelocity());

        Logger.recordOutput("Shooter/RightHoodAngle", getRightShooterAngle());
        Logger.recordOutput("Shooter/LeftHoodAngle", getLeftShooterAngle());
    }
}
