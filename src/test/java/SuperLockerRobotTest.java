import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidBagType;
import exception.InvalidTicket;
import exception.LockerException;
import exception.NoEmptyLockerException;
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

  @Test
  public void should_save_L_bag_in_locker2_when_use_SuperLockerRobot_save_given_locker1_is50_locker2_is_25()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(2, BagType.L);
    Locker locker2 = new Locker(4, BagType.L);
    Bag bag1 = new Bag(BagType.L);
    Bag bag2 = new Bag(BagType.L);
    Bag bag3 = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1, locker2));
    superLockerRobot.saveBag(bag1);
    superLockerRobot.saveBag(bag2);

    Ticket ticket = superLockerRobot.saveBag(bag3);

    assertNotNull(ticket);
    assertEquals(2, locker2.getAvailability());
  }

  @Test
  public void should_save_L_bag_in_locker1_when_use_SuperLockerRobot_save_given_locker1_is_50_locker2_is_50()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(2, BagType.L);
    Locker locker2 = new Locker(2, BagType.L);
    Bag bag1 = new Bag(BagType.L);
    Bag bag2 = new Bag(BagType.L);
    Bag bag3 = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1, locker2));
    superLockerRobot.saveBag(bag1);
    superLockerRobot.saveBag(bag2);

    Ticket ticket = superLockerRobot.saveBag(bag3);

    assertNotNull(ticket);
    assertEquals(0, locker1.getAvailability());
  }

  @Test
  public void should_fail_to_save_when_use_SuperLockerRobot_save_given_locker_is_full()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1));
    Bag bag1 = new Bag(BagType.L);
    superLockerRobot.saveBag(bag1);
    Bag bag2 = new Bag(BagType.L);

    NoEmptyLockerException noEmptyLockerException = assertThrows(NoEmptyLockerException.class, () -> superLockerRobot.saveBag(bag2));
    assertEquals("Fail to save, locker is full", noEmptyLockerException.getMessage());
  }

  @Test
  public void should_fail_to_save_when_use_SuperLockerRobot_save_given_locker_is_not_full_bag_is_not_L()
      throws LockerException, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.S);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1));

    InvalidBagType invalidBagType = assertThrows(InvalidBagType.class, () -> superLockerRobot.saveBag(bag));
    assertEquals("Fail to save, wrong bag type", invalidBagType.getMessage());
  }

  @Test
  public void should_get_L_bag_when_use_SuperLockerRobot_get_bag_given_ticket_is_L()
      throws LockerException, NoEmptyLockerException, InvalidBagType, InvalidTicket, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1));
    Ticket ticket = superLockerRobot.saveBag(bag);

    Bag myBag = superLockerRobot.getBag(ticket);

    assertEquals(bag, myBag);
  }

  @Test
  public void should_fail_to_get_bag_when_use_SuperLockerRobot_get_bag_given_ticket_is_M()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(locker1));
    superLockerRobot.saveBag(bag);
    Ticket ticket = new Ticket(BagType.M);

    InvalidTicket invalidTicket = assertThrows(InvalidTicket.class, () -> superLockerRobot.getBag(ticket));

    assertEquals("Fail to get the bag, invalid ticket.", invalidTicket.getMessage());
  }

  @Test
  public void should_fail_to_get_bag_when_use_SuperLockerRobot_get_bag_given_ticket_is_invalid()
      throws LockerException, NoEmptyLockerException, InvalidBagType, SuperLockerRobotException {
    Locker locker1 = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.L);
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(locker1));
    superLockerRobot.saveBag(bag);
    Ticket ticket = new Ticket(BagType.L);

    InvalidTicket invalidTicket = assertThrows(InvalidTicket.class, () -> superLockerRobot.getBag(ticket));

    assertEquals("Fail to get the bag, invalid ticket.", invalidTicket.getMessage());
  }
}
