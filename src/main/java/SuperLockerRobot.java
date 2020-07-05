import exception.InvalidBagType;
import exception.InvalidTicket;
import exception.NoEmptyLockerException;
import exception.SuperLockerRobotException;
import java.util.List;

public class SuperLockerRobot {

  final private List<Locker> lockers;

  public SuperLockerRobot(List<Locker> lockers) throws SuperLockerRobotException {
    for (Locker locker : lockers) {
      if (!locker.getBagType().equals(BagType.L)) {
        throw new SuperLockerRobotException();
      }
    }
    this.lockers = lockers;
  }

  public Ticket saveBag(Bag bag) throws NoEmptyLockerException, InvalidBagType {
    Locker locker = lockers.get(0);
    for (int i = 1; i < lockers.size(); i++) {
      if (locker.getAvailabilityRate() < lockers.get(i).getAvailabilityRate()) {
        locker = lockers.get(i);
      }
    }
//    Locker locker = lockers.stream().max((o1, o2) -> (o1.getAvailabilityRate() > (o2.getAvailabilityRate())) ? 0 : 1).get();
    return locker.saveBag(bag);
  }

  public Bag getBag(Ticket ticket) throws InvalidTicket {
    if (!ticket.getBagType().equals(BagType.L)) {
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
