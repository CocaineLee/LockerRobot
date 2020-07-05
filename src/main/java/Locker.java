import exception.LockerException;
import java.util.HashMap;
import java.util.Map;

public class Locker {

  private final Integer capability;
  private final BagType bagType;
  private Map<Ticket, Bag> ticketBagMap = new HashMap<>();

  public Locker(Integer capability, BagType bagType) throws LockerException {
    if (capability <= 0) {
      throw new LockerException();
    }
    this.capability = capability;
    this.bagType = bagType;
  }


  public Ticket saveBag(Bag bag) {
    Ticket ticket = new Ticket(BagType.S);
    ticketBagMap.put(ticket, bag);
    return ticket;
  }
}
