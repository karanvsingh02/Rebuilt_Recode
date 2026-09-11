package team5427.frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Meter;
import static edu.wpi.first.units.Units.MetersPerSecond;
import edu.wpi.first.math.filter.Debouncer;
import static edu.wpi.first.units.Units.DegreesPerSecond;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Optional;
import java.util.function.Supplier;
import org.ironmaple.simulation.drivesims.SwerveDriveSimulation;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.Constants.Mode;
import team5427.frc.robot.subsystems.intake.io.IntakeIO;
import team5427.frc.robot.subsystems.intake.io.IntakeIOInputsAutoLogged;
import team5427.frc.robot.subsystems.intake.io.IntakeIOTalonFX;
import team5427.frc.robot.subsystems.intake.io.IntakeIOSim;

public class IntakeSubsystem extends SubsystemBase {
  private LinearVelocity intakingSpeed;
  private Rotation2d intakingAngle;

  private IntakeIO io;
  private IntakeIOInputsAutoLogged inputsAutoLogged;

  public static IntakeSubsystem m_instance;

  public final Alert kIntakingSpeedOutOfBounds = new Alert("OutOfBounds", "Intaking Speed Requested Out of Bounds", AlertType.kWarning);
  public final Alert kIntakingRotationOutOfBounds = new Alert("OutOfBounds", "Intaking Rotation Requested Out of Bounds", AlertType.kWarning);

  public static IntakeSubsystem getInstance(
      Supplier<SwerveDriveSimulation> swerveDriveSimulationSupplier) {
    if (m_instance == null) {
      m_instance = new IntakeSubsystem(Optional.of(swerveDriveSimulationSupplier));
    }
    return m_instance;
  }

  public static IntakeSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new IntakeSubsystem(Optional.empty());
    }
    return m_instance;
  }

  private IntakeSubsystem(Optional<Supplier<SwerveDriveSimulation>> swerveDriveSimulationSupplier) {
    inputsAutoLogged = new IntakeIOInputsAutoLogged();
    IntakeIO ioInstance;
    switch (Constants.currentMode) {
      case REAL:
        ioInstance = new IntakeIOTalonFX();
        break;
      case SIM:
        if (swerveDriveSimulationSupplier.isEmpty()) {
          DriverStation.reportWarning(
              "Intake Subsystem Simulation did not receive a Swerve Drive Simulation Supplier - replacing with no operating IntakeIO",
              true);
              ioInstance = new IntakeIO(){};
        }
        else{
          ioInstance = new IntakeIOSim(swerveDriveSimulationSupplier.get());
        }
        break;
      default:
      ioInstance = new IntakeIO(){};
        break;
    }
    io = ioInstance;
    intakingSpeed = MetersPerSecond.of(0.0);
    intakingAngle = Rotation2d.kZero;
  }

  @Override
public void periodic() {
    io.updateInputs(inputsAutoLogged);
    boolean speedOutOfBounds = Math.abs(intakingSpeed.in(MetersPerSecond)) > 10.0;
    kIntakingSpeedOutOfBounds.set(speedOutOfBounds);
    io.setRollerSpeed(
        MetersPerSecond.of(
            MathUtil.clamp(
                intakingSpeed.in(MetersPerSecond),
                -10.0,
                10.0)));

    boolean rotationOutOfBounds = intakingAngle.getDegrees()> IntakeConstants.kPivotMaximumRotation.getDegrees()|| intakingAngle.getDegrees() < IntakeConstants.kPivotMinimumRotation.getDegrees();
    kIntakingRotationOutOfBounds.set(rotationOutOfBounds);
    io.setPivotRotation(
        Rotation2d.fromDegrees(
            MathUtil.clamp(
                intakingAngle.getDegrees(),
                IntakeConstants.kPivotMinimumRotation.getDegrees(),
                IntakeConstants.kPivotMaximumRotation.getDegrees())));
    Logger.processInputs("Intake/Inputs", inputsAutoLogged);
    log();
}

  public boolean isPivotAtSetpoint() {
    return Math.abs(
        inputsAutoLogged.pivotMotorRotation
            .minus(intakingAngle)
            .getDegrees()) < 10.0;
  }

  public void simulateIntaking(boolean isIntaking) {
    if (Constants.currentMode.equals(Mode.SIM)
        && io instanceof IntakeIOSim ioSim) {
      ioSim.setRunning(isIntaking);
    }
  }

  public void setIntakingSpeed(LinearVelocity speed) {
    intakingSpeed = speed;
  }

  public void setIntakingVoltage(Voltage volts) {
    io.setPivotSpeed(volts);
  }

  public void setIntakingRotation(Rotation2d angle) {
    intakingAngle = angle;
  }

  public void setPivotMotorCurrentLimit(Current limit) {
    io.setPivotCurrentLimit(limit);
  }

  public void resetPivotMotorPosition(Rotation2d rotation) {
    io.resetPivotMotorPosition(rotation);
  }

  public Rotation2d getIntakingRotation2d() {
    return intakingAngle;
  }
  private final Debouncer speedBouncer = new Debouncer(0.1);
    public boolean isPivotSpeedZero() {
    return speedBouncer.calculate(
        Math.abs(inputsAutoLogged.pivotMotorAngularVelocity.in(DegreesPerSecond))
            <= 0.1);
  }

  public void reverseIntakeHome() {
    resetPivotMotorPosition(IntakeConstants.kPivotMaximumRotation);
  }

  public void intake() {
    setIntakingSpeed(IntakeConstants.kRollerIntakeVelocity);
    setIntakingRotation(IntakeConstants.kPivotIntakeRotation);
  }

  public void disableRollerMotor(boolean shouldDisable) {
    io.disableRollerMotor(shouldDisable);
  }

  public void disablePivotMotor(boolean shouldDisable) {
    io.disablePivotMotor(shouldDisable);
  }

  public boolean isRollerMotorDisabled() {
    return inputsAutoLogged.rollerMotorDisabled;
  }

  public boolean isPivotMotorDisabled() {
    return inputsAutoLogged.pivotMotorDisabled;
  }

  public void log() {
    Logger.recordOutput("Intake/IntakingSpeed", intakingSpeed.in(MetersPerSecond));
    Logger.recordOutput("Intake/IntakingAngle", intakingAngle);
  }
}
