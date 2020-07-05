import exception.InvalidBagType;
import exception.InvalidTicket;
import exception.LockerException;
import exception.NoEmptyLockerException;
import java.util.HashMap;
import java.util.Map;

public class Locker {

  final private Integer capability;
  private final BagType bagType;
  private Map<Ticket, Bag> ticketBagMap = new HashMap<>();

  public Locker(Integer capability, BagType bagType) throws LockerException {
    if (capability <= 0) {
      throw new LockerException();
    }
    this.capability = capability;
    this.bagType = bagType;
  }


  public Ticket saveBag(Bag bag) throws NoEmptyLockerException, InvalidBagType {
    if (!bag.getBagType().equals(bagType)) {
      throw new InvalidBagType();
    }
    if (getAvailability() <= 0) {
      throw new NoEmptyLockerException();
    }
    Ticket ticket = new Ticket(bagType);
    ticketBagMap.put(ticket, bag);
    return ticket;
  }

  public Bag getBag(Ticket ticket) throws InvalidTicket {
    if (!ticket.getBagType().equals(bagType)) {
      throw new InvalidTicket();
    }
    Bag bag = ticketBagMap.get(ticket);
    if (bag != null) {
      return bag;
    }
    throw new InvalidTicket();
  }

  public BagType getBagType() {
    return bagType;
  }

  public Integer getAvailability() {
    return capability - ticketBagMap.size();
  }

  public Double getAvailabilityRate() {
    return getAvailability() / Double.valueOf(capability);
  }
}
