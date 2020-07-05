import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.LockerException;
import org.junit.jupiter.api.Test;

//- Given：有一个locker When：配置这个locker的容量小于等于0 Then：得到报错信息，初始化Locker失败，Locker容量不能为0。
//- Given：有一个S locker ， 以及一个S Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为S。
//- Given：有一个M locker ， 以及一个M Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为M。
//- Given：有一个L locker ， 以及一个L Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为L。
//- Given：有一个L locker, 以及L 的ticket When：使用该Ticket取包 Then：取Bag成功，得到对应的Bag。
//- Given：有一个L locker, 以及M 的ticket When：使用该Ticket取包 Then：取Bag失败，得到报错信息，取包失败，请使用正确的Ticket。
public class LockerTest {

  @Test
  public void should_throw_error_when_locker_is_config_given_capability_is_0() {
    LockerException lockerException = assertThrows(LockerException.class, () -> new Locker(0,BagType.S));
    assertEquals("Initialization of locker failed, the capability can't be 0", lockerException.getMessage());
  }

  @Test
  public void should_save_successfully_and_get_S_ticket_when_save_S_bag_given_Locker_is_S() throws LockerException {
    Locker locker = new Locker(2, BagType.S);
    Bag bag = new Bag(BagType.S);

    Ticket ticket = locker.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(BagType.S, ticket.getBagType());
  }
}
