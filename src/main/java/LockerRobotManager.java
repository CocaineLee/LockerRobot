import exception.InvalidBagType;
import exception.LockerRobotManagerException;
import exception.NoEmptyLockerException;
import exception.PrimaryLockerRobotException;
import java.util.List;
import java.util.Optional;

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

  public Ticket saveBag(Bag bag) throws NoEmptyLockerException, InvalidBagType {
    switch (bag.getBagType()) {
      case S:
        return lockers.stream()
            .filter(locker -> locker.getAvailability() != 0)
            .findFirst()
            .orElseThrow(()->new NoEmptyLockerException())
            .saveBag(bag);
      case M:
        return primaryLockerRobots.stream()
            .filter(r -> r.isNotFull())
            .findFirst()
            .orElseThrow(()->new NoEmptyLockerException())
            .saveBag(bag);
      case L:
        return superLockerRobots.stream()
            .filter(r -> r.isNotFull())
            .findFirst()
            .orElseThrow(()->new NoEmptyLockerException())
            .saveBag(bag);
      default:
        return null;
    }
  }
}
