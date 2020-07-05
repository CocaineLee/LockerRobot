import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidBagType;
import exception.LockerException;
import exception.NoEmptyLockerException;
import exception.PrimaryLockerRobotException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


//- Given: 有一个S Locker When：使用该locker配置 PrimaryLockerRobot Then： 初始化失败
//- Given: 有一个管理 MLocker1（ 两个容量）和 MLocker2（三个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包成功，且存到 MLocker1 得到MTicket
//- Given: 有一个管理 MLocker1（ 已满）和 MLocker2（三个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包成功，且存到 MLocker2 得到MTicket
//- Given: 有一个管理 MLocker1（ 已满）和 MLocker2（已满）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包失败，locker已满
//- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存LBag Then： 存包失败，请将包存入正确的locker
//- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个合法的M Ticket When：使用在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包成功，等到包
//- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个L Ticket When：使用在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包失败，请使用正确的Ticket
//- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个非法 Ticket When：使用在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包失败，请使用正确的Ticket
class PrimaryLockerRobotTest {

  @Test
  public void should_fail_to_config_PrimaryLockerRobot_when_construct_given_locker_is_not_M() throws LockerException {
    Locker locker = new Locker(1, BagType.S);
    PrimaryLockerRobotException primaryLockerRobotException = assertThrows(PrimaryLockerRobotException.class,
        () -> new PrimaryLockerRobot(Arrays.asList(locker)));
    assertEquals("Initialization of PrimaryLockerRobot failed, locker must be M", primaryLockerRobotException.getMessage());
  }

  @Test
  public void should_save_m_bag_in_locker1_when_use_PrimaryLockerRobot_save_given_locker1_locker2_are_not_full()
      throws LockerException, PrimaryLockerRobotException, NoEmptyLockerException, InvalidBagType {
    Locker locker1 = new Locker(1, BagType.M);
    Locker locker2 = new Locker(1, BagType.M);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
    Bag bag = new Bag(BagType.M);

    Ticket ticket = primaryLockerRobot.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(BagType.M, ticket.getBagType());
  }

  @Test
  public void should_save_m_bag_in_locker2_when_use_PrimaryLockerRobot_save_given_locker1_is_full_locker2_is_not_full()
      throws LockerException, PrimaryLockerRobotException, NoEmptyLockerException, InvalidBagType {
    Locker locker1 = new Locker(1, BagType.M);
    Locker locker2 = new Locker(2, BagType.M);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
    Bag bag1 = new Bag(BagType.M);
    primaryLockerRobot.saveBag(bag1);
    Bag bag2 = new Bag(BagType.M);

    Ticket ticket = primaryLockerRobot.saveBag(bag2);

    assertNotNull(ticket);
    assertEquals(BagType.M, ticket.getBagType());
    assertEquals(1, locker2.getCapability());
  }

  @Test
  public void should_fail_to_save_when_use_PrimaryLockerRobot_save_given_locker_is_full()
      throws LockerException, PrimaryLockerRobotException, NoEmptyLockerException, InvalidBagType {
    Locker locker1 = new Locker(1, BagType.M);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
    Bag bag1 = new Bag(BagType.M);
    primaryLockerRobot.saveBag(bag1);
    Bag bag2 = new Bag(BagType.M);

    NoEmptyLockerException noEmptyLockerException = assertThrows(NoEmptyLockerException.class, () -> primaryLockerRobot.saveBag(bag2));
    assertEquals("Fail to save, locker is full", noEmptyLockerException.getMessage());
  }

  @Test
  public void should_fail_to_save_when_use_PrimaryLockerRobot_save_given_locker_is_not_full_bag_is_not_m()
      throws LockerException, PrimaryLockerRobotException {
    Locker locker1 = new Locker(1, BagType.M);
    Bag bag = new Bag(BagType.S);
    PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1));

    InvalidBagType invalidBagType = assertThrows(InvalidBagType.class, () -> primaryLockerRobot.saveBag(bag));
    assertEquals("Fail to save, wrong bag type", invalidBagType.getMessage());
  }


}
