import exception.SuperLockerRobotException;
import java.util.List;

public class SuperLockerRobot  {

  final private List<Locker> lockers;

  public SuperLockerRobot(List<Locker> lockers) throws SuperLockerRobotException {
    for (Locker locker : lockers) {
      if (!locker.getBagType().equals(BagType.L)) {
        throw new SuperLockerRobotException();
      }
    }
    this.lockers = lockers;
  }
}
