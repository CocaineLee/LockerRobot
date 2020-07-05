package exception;

public class LockerException extends Exception {

  public LockerException() {
    super("Initialization of locker failed, the capability can't be 0");
  }
}
