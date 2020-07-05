import static org.junit.jupiter.api.Assertions.*;

import exception.LockerException;
import exception.LockerRobotManagerException;
import exception.PrimaryLockerRobotException;
import exception.SuperLockerRobotException;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class LockerRobotManagerTest {
  @Test
  public void should_fail_to_config_LockerRobotManager_when_construct_given_locker_is_not_S()
      throws LockerException, PrimaryLockerRobotException, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.M);
    Locker locker2 = new Locker(1, BagType.M);
    Locker locker3 = new Locker(1, BagType.L);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(locker3));

    LockerRobotManagerException lockerRobotManagerException = assertThrows(LockerRobotManagerException.class,
        () -> new LockerRobotManager(Collections.singletonList(locker1), Arrays.asList(primaryLockerRobot), Arrays.asList(superLockerRobot)));

    assertEquals("Initialization of LockerRobotManager failed, locker must be S", lockerRobotManagerException.getMessage());
  }

}
