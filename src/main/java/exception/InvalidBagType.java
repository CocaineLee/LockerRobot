package exception;

public class InvalidBagType extends Exception {

  public InvalidBagType() {
    super("Fail to save, wrong bag type");
  }
}
