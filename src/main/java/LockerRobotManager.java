import exception.LockerRobotManagerException;
import exception.PrimaryLockerRobotException;
import java.util.List;

public class LockerRobotManager {

  final private List<Locker> lockers;
  final private List<PrimaryLockerRobot> primaryLockerRobots;
  final private List<SuperLockerRobot> superLockerRobots;

  public LockerRobotManager(List<Locker> lockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots)
      throws LockerRobotManagerException {
    for (Locker locker : lockers) {

      if (locker.getBagType() != BagType.S) {
        throw new LockerRobotManagerException();
      }
    }


    this.lockers = lockers;
    this.primaryLockerRobots = primaryLockerRobots;
    this.superLockerRobots = superLockerRobots;
  }
}
