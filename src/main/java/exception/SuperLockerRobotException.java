package exception;

public class SuperLockerRobotException extends Exception {

  public SuperLockerRobotException() {
    super("Initialization of SuperLockerRobot failed, locker must be L");
  }
}
