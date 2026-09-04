package team5427.frc.robot.subsystems.shooter.io;

import team5427.lib.drivers.LoggedTunableNumber;
import team5427.lib.tables.LookUpTable;

public class AimingConstants {
    public static final LookUpTable kShootingTable = new LookUpTable();

  public static LoggedTunableNumber[] kShootingSpeeds = {
    new LoggedTunableNumber("Aiming/Speeds/Speed1", 12.85),
    new LoggedTunableNumber("Aiming/Speeds/Speed2", 13.45),
    new LoggedTunableNumber("Aiming/Speeds/Speed3", 13.85),
    new LoggedTunableNumber("Aiming/Speeds/Speed4", 14.6),
    new LoggedTunableNumber("Aiming/Speeds/Speed5", 14.9),
    new LoggedTunableNumber("Aiming/Speeds/Speed6", 15.6),
    new LoggedTunableNumber("Aiming/Speeds/Speed7", 15.9),
    new LoggedTunableNumber("Aiming/Speeds/Speed8", 17.5),
    new LoggedTunableNumber("Aiming/Speeds/Speed9", 17.6),
    new LoggedTunableNumber("Aiming/Speeds/Speed10", 19.0)
  };
  public static LoggedTunableNumber[] kShootingDistances = {
    new LoggedTunableNumber("Aiming/Distances/Distance1", 2.707),
    new LoggedTunableNumber("Aiming/Distances/Distance2", 2.982),
    new LoggedTunableNumber("Aiming/Distances/Distance3", 3.253),
    new LoggedTunableNumber("Aiming/Distances/Distance4", 3.519),
    new LoggedTunableNumber("Aiming/Distances/Distance5", 3.785),
    new LoggedTunableNumber("Aiming/Distances/Distance6", 4.048),
    new LoggedTunableNumber("Aiming/Distances/Distance7", 4.310),
    new LoggedTunableNumber("Aiming/Distances/Distance8", 4.571),
    new LoggedTunableNumber("Aiming/Distances/Distance9", 4.830),
    new LoggedTunableNumber("Aiming/Distances/Distance10", 5.000)
  };

  public static LoggedTunableNumber[] kShootingAngles = {
    new LoggedTunableNumber("Aiming/Angles/Angle1", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle2", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle3", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle4", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle5", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle6", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle7", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle8", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle9", 29.0),
    new LoggedTunableNumber("Aiming/Angles/Angle10", 29.0)
  };

  public static LoggedTunableNumber[] kTimeOfFlights = {
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight1", 0.5),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight2", 0.7),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight3", 0.8),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight4", .85),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight5", .95),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight6", 1.0),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight7", 1.1),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight6", 1.1),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight7", 1.225),
    new LoggedTunableNumber("Aiming/TimeOfFlights/TimeOfFlight8", 1.33)
  };

  static {updateLookupTables(); }
  public static void updateLookupTables() {
    kShootingTable.reset();
    for (int i =0; i < kShootingDistances.length; i++) {
      kShootingTable.addPivotAngle(kShootingDistances[i].get(), kShootingAngles[i].get() );
      kShootingTable.addFlywheelSpeed(kShootingDistances[i].get(), kShootingSpeeds[i].get());
      kShootingTable.addTimeOfFlight(kShootingDistances[i].get(),kTimeOfFlights[i].get());
    }
  }
}
