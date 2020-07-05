import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidBagType;
import exception.LockerException;
import exception.LockerRobotManagerException;
import exception.NoEmptyLockerException;
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
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(locker2));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker3));

    LockerRobotManagerException lockerRobotManagerException = assertThrows(LockerRobotManagerException.class,
        () -> new LockerRobotManager(Collections.singletonList(locker1), asList(primaryLockerRobot),
            Collections.singletonList(superLockerRobot)));

    assertEquals("Initialization of LockerRobotManager failed, locker must be S", lockerRobotManagerException.getMessage());
  }

  @Test
  public void should_save_in_Locker_and_get_ticketS_when_save_in_LockerRobotManager_given_manage_locker_and_PrimaryLockerRobot()
      throws LockerException, PrimaryLockerRobotException, LockerRobotManagerException, NoEmptyLockerException, InvalidBagType {
    Locker locker1 = new Locker(1, BagType.S);
    Locker locker2 = new Locker(1, BagType.M);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(locker2));
    LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker1), Collections.singletonList(primaryLockerRobot),
        null);
    Bag bag = new Bag(BagType.S);

    Ticket ticket = lockerRobotManager.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(0, locker1.getAvailability());
  }

  @Test
  public void should_save_in_PrimaryLockerRobot_and_get_ticketM_when_save_in_LockerRobotManager_given_manage_locker_and_PrimaryLockerRobot()
      throws LockerException, PrimaryLockerRobotException, LockerRobotManagerException, NoEmptyLockerException, InvalidBagType {
    Locker locker1 = new Locker(1, BagType.S);
    Locker locker2 = new Locker(1, BagType.M);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(locker2));
    LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker1), Collections.singletonList(primaryLockerRobot),
        null);
    Bag bag = new Bag(BagType.M);

    Ticket ticket = lockerRobotManager.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(0, locker2.getAvailability());
  }

  @Test
  public void should_save_in_SuperLockerRobot_and_get_ticketL_when_save_in_LockerRobotManager_given_manage_locker_and_SuperLockerRobot()
      throws LockerException, LockerRobotManagerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.S);
    Locker locker2 = new Locker(1, BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker2));
    LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker1), null,
        Collections.singletonList(superLockerRobot));
    Bag bag = new Bag(BagType.L);

    Ticket ticket = lockerRobotManager.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(0, locker2.getAvailability());
  }

  @Test
  public void should_fail_to_save_in_SuperLockerRobot_when_save_in_LockerRobotManager_given_manage_locker_and_SuperLockerRobot_primaryLockerRobot_are_full()
      throws LockerException, LockerRobotManagerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException, PrimaryLockerRobotException {
    Locker locker1 = new Locker(1, BagType.S);
    Locker locker2 = new Locker(1, BagType.M);
    Locker locker3 = new Locker(1, BagType.L);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(locker2));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker3));
    LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker1), Collections.singletonList(primaryLockerRobot),
        Collections.singletonList(superLockerRobot));
    Bag bag1 = new Bag(BagType.S);
    Bag bag2 = new Bag(BagType.M);
    Bag bag3 = new Bag(BagType.L);
    lockerRobotManager.saveBag(bag1);
    lockerRobotManager.saveBag(bag2);
    lockerRobotManager.saveBag(bag3);
    Bag bag4 = new Bag(BagType.L);


    NoEmptyLockerException noEmptyLockerException = assertThrows(NoEmptyLockerException.class, () -> lockerRobotManager.saveBag(bag4));
    assertEquals("Fail to save, locker is full", noEmptyLockerException.getMessage());
  }
}
