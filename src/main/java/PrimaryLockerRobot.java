import exception.PrimaryLockerRobotException;
import java.util.List;

public class PrimaryLockerRobot {

  final private List<Locker> lockers;

  public PrimaryLockerRobot(List<Locker> lockers) throws PrimaryLockerRobotException {
    for (Locker locker : lockers) {

      if (locker.getBagType() != BagType.M) {
        throw new PrimaryLockerRobotException();
      }
    }
    this.lockers = lockers;
  }
}
