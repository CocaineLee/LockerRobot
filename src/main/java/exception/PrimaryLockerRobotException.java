package exception;

public class PrimaryLockerRobotException extends Exception {

  public PrimaryLockerRobotException() {
    super("Initialization of PrimaryLockerRobot failed, locker must be M");
  }
}
