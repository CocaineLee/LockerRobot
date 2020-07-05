import exception.LockerException;
import exception.NoEmptyLockerException;
import java.util.HashMap;
import java.util.Map;

public class Locker {

  private final Integer capability;
  private final BagType bagType;
  private Integer availability;
  private Map<Ticket, Bag> ticketBagMap = new HashMap<>();

  public Locker(Integer capability, BagType bagType) throws LockerException {
    if (capability <= 0) {
      throw new LockerException();
    }
    this.capability = capability;
    this.availability = capability;
    this.bagType = bagType;
  }


  public Ticket saveBag(Bag bag) throws NoEmptyLockerException {
    if (availability <= 0) {
      throw new NoEmptyLockerException();
    }
    Ticket ticket = new Ticket(bag.getBagType());
    availability--;
    ticketBagMap.put(ticket, bag);
    return ticket;
  }
}
