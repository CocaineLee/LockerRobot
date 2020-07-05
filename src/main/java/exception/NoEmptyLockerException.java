package exception;

public class NoEmptyLockerException extends Exception {

  public NoEmptyLockerException() {
    super("Fail to save, locker is full");
  }
}
