import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidBagType;
import exception.LockerException;
import exception.NoEmptyLockerException;
import exception.SuperLockerRobotException;
import java.util.Arrays;
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

  @Test
  public void should_save_L_bag_in_locker2_when_use_SuperLockerRobot_save_given_locker1_is50_locker2_is_25()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(2, BagType.L);
    Locker locker2 = new Locker(4, BagType.L);
    Bag bag1 = new Bag(BagType.L);
    Bag bag2 = new Bag(BagType.L);
    Bag bag3 = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(locker1, locker2));
    superLockerRobot.saveBag(bag1);
    superLockerRobot.saveBag(bag2);

    Ticket ticket = superLockerRobot.saveBag(bag3);

    assertNotNull(ticket);
    assertEquals(2, locker2.getAvailability());
  }

//  @Test
//  public void should_save_m_bag_in_locker2_when_use_PrimaryLockerRobot_save_given_locker1_is_full_locker2_is_not_full()
//      throws LockerException, PrimaryLockerRobotException, NoEmptyLockerException, InvalidBagType {
//    Locker locker1 = new Locker(1, BagType.M);
//    Locker locker2 = new Locker(2, BagType.M);
//    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
//    Bag bag1 = new Bag(BagType.M);
//    primaryLockerRobot.saveBag(bag1);
//    Bag bag2 = new Bag(BagType.M);
//
//    Ticket ticket = primaryLockerRobot.saveBag(bag2);
//
//    assertNotNull(ticket);
//    assertEquals(BagType.M, ticket.getBagType());
//    assertEquals(1, locker2.getCapability());
//  }
//
//  @Test
//  public void should_fail_to_save_when_use_PrimaryLockerRobot_save_given_locker_is_full()
//      throws LockerException, PrimaryLockerRobotException, NoEmptyLockerException, InvalidBagType {
//    Locker locker1 = new Locker(1, BagType.M);
//    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
//    Bag bag1 = new Bag(BagType.M);
//    primaryLockerRobot.saveBag(bag1);
//    Bag bag2 = new Bag(BagType.M);
//
//    NoEmptyLockerException noEmptyLockerException = assertThrows(NoEmptyLockerException.class, () -> primaryLockerRobot.saveBag(bag2));
//    assertEquals("Fail to save, locker is full", noEmptyLockerException.getMessage());
//  }
//
//  @Test
//  public void should_fail_to_save_when_use_PrimaryLockerRobot_save_given_locker_is_not_full_bag_is_not_m()
//      throws LockerException, PrimaryLockerRobotException {
//    Locker locker1 = new Locker(1, BagType.M);
//    Bag bag = new Bag(BagType.S);
//    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
//
//    InvalidBagType invalidBagType = assertThrows(InvalidBagType.class, () -> primaryLockerRobot.saveBag(bag));
//    assertEquals("Fail to save, wrong bag type", invalidBagType.getMessage());
//  }

}
