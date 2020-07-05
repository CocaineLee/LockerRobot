import exception.InvalidBagType;
import exception.InvalidTicket;
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
        .filter(locker -> locker.getAvailability() != 0)
        .findFirst()
        .orElseThrow(() -> new NoEmptyLockerException())
        .saveBag(bag);
  }

  public Bag getBag(Ticket ticket) throws InvalidTicket {
    if (!ticket.getBagType().equals(BagType.M)) {
      throw new InvalidTicket();
    }
    return lockers.stream()
        .map(locker -> {
          try {
            return locker.getBag(ticket);
          } catch (InvalidTicket e) {
            return null;
          }
        })
        .filter(b -> b != null)
        .findFirst()
        .orElseThrow(() -> new InvalidTicket());
  }

  public Boolean isNotFull() {
    for (Locker locker : lockers) {
      if (locker.getAvailability() > 0) {
        return true;
      }
    }
    return false;
  }
}
