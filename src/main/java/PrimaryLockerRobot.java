import exception.InvalidBagType;
import exception.NoEmptyLockerException;
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

  public Ticket saveBag(Bag bag) throws NoEmptyLockerException, InvalidBagType {
    if (!bag.getBagType().equals(BagType.M)) {
      throw new InvalidBagType();
    }
    return lockers.stream()
        .filter(locker -> locker.getCapability() != 0)
        .findFirst()
        .orElseThrow(()->new NoEmptyLockerException())
        .saveBag(bag);
  }
}
