import static org.junit.jupiter.api.Assertions.*;

import exception.LockerException;
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
    PrimaryLockerRobotException primaryLockerRobotException = assertThrows(PrimaryLockerRobotException.class, () -> new PrimaryLockerRobot(Arrays.asList(locker)));
    assertEquals("Initialization of PrimaryLockerRobot failed, locker must be M", primaryLockerRobotException.getMessage());
  }


}
