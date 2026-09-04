package team5427.lib.tables;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;

// Distance -> Pivot Angle
// Distance -> Flywheel 1 speed
// Distance -> Flywheel 2 speed

public class LookUpTable {
  private InterpolatingTreeMap<Double, Double> pivotAngleLookup;
  private InterpolatingTreeMap<Double, Double> flywheelLookup;
  private InterpolatingTreeMap<Double, Double> timeOfFlightLookup;

  public LookUpTable() {
    pivotAngleLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
    flywheelLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
    timeOfFlightLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
  }

  public void addPivotAngle(Double distance, Double pivotAngle) {
    pivotAngleLookup.put(distance, pivotAngle);
  }

  /* public void addFlyWheel1Speed(Double distance, Double speed) {
    flywheel1Lookup.put(distance, speed);
  }

  public void addFlyWheel2Speed(Double distance, Double speed) {
    flywheel2Lookup.put(distance, speed);
  } */

  public Double getPivotAngle(Double distance) {
    return pivotAngleLookup.get(distance);
  }

  
  /* public Double getFlyWheel1Speed(Double distance) {
    return flywheel1Lookup.get(distance);
  }

  public Double getFlyWheel2Speed(Double distance) {
    return flywheel2Lookup.get(distance);
  } */

  public void resetPivotAngles() {
    pivotAngleLookup.clear();
  }

  /* public void resetFlywheel1Speeds() {
    flywheel1Lookup.clear();
  }

  public void resetFlywheel2Speeds() {
    flywheel2Lookup.clear();
  } */


  public void addFlywheelSpeed(Double d, Double s) {
    flywheelLookup.put(d, s);
  }
  public void addTimeOfFlight(Double d, double t) {
    timeOfFlightLookup.put(d, t);
  }

  public Double getTimeOfFlight(Double d) {
    return timeOfFlightLookup.get(d);
  }

  public void reset() {
    flywheelLookup.clear();
    timeOfFlightLookup.clear();
    pivotAngleLookup.clear();

  }


}
