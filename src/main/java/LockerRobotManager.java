import exception.InvalidBagType;
import exception.InvalidTicket;
import exception.LockerRobotManagerException;
import exception.NoEmptyLockerException;
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

  public Ticket saveBag(Bag bag) throws NoEmptyLockerException, InvalidBagType {
    switch (bag.getBagType()) {
      case S:
        return lockers.stream()
            .filter(locker -> locker.getAvailability() != 0)
            .findFirst()
            .orElseThrow(() -> new NoEmptyLockerException())
            .saveBag(bag);
      case M:
        return primaryLockerRobots.stream()
            .filter(r -> r.isNotFull())
            .findFirst()
            .orElseThrow(() -> new NoEmptyLockerException())
            .saveBag(bag);
      case L:
        return superLockerRobots.stream()
            .filter(r -> r.isNotFull())
            .findFirst()
            .orElseThrow(() -> new NoEmptyLockerException())
            .saveBag(bag);
      default:
        return null;
    }
  }

  public Bag getBag(Ticket ticket) throws InvalidTicket {
    switch (ticket.getBagType()) {
      case S:
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
      case M:
        return primaryLockerRobots.stream()
            .map(
                p -> {
                  try {
                    return p.getBag(ticket);
                  } catch (InvalidTicket invalidTicket) {
                    return null;
                  }
                }
            ).filter(b -> b != null)
            .findFirst()
            .orElseThrow(() -> new InvalidTicket());
      case L:
        return superLockerRobots.stream()
            .map(
                p -> {
                  try {
                    return p.getBag(ticket);
                  } catch (InvalidTicket invalidTicket) {
                    return null;
                  }
                }
            ).filter(b -> b != null)
            .findFirst()
            .orElseThrow(() -> new InvalidTicket());
      default:
        return null;
    }
  }
}
