import exception.LockerException;

public class Locker {

  private final Integer capability;

  public Locker(Integer capability) throws LockerException {
    if (capability <= 0) {
      throw new LockerException();
    }
    this.capability = capability;
  }
}
