package exception;

public class InvalidTicket extends Exception {

  public InvalidTicket() {
    super("Fail to get the bag, invalid ticket.");
  }
}
