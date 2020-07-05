public class Ticket {

  final private BagType bagType;

  public Ticket(BagType bagType) {
    this.bagType = bagType;
  }

  public BagType getBagType() {
    return bagType;
  }
}
