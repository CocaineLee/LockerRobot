import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.LockerException;
import exception.SuperLockerRobotException;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class SuperLockerRobotTest {

  @Test
  public void should_fail_to_config_SuperLockerRobotTest_when_construct_given_locker_is_not_L() throws LockerException {
    Locker locker = new Locker(1, BagType.S);
    SuperLockerRobotException superLockerRobotException = assertThrows(SuperLockerRobotException.class,
        () -> new SuperLockerRobot(Collections.singletonList(locker)));
    assertEquals("Initialization of SuperLockerRobot failed, locker must be L", superLockerRobotException.getMessage());
  }
}
