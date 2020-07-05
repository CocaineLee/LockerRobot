import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidBagType;
import exception.InvalidTicket;
import exception.LockerException;
import exception.NoEmptyLockerException;
import org.junit.jupiter.api.Test;

//- Given：有一个locker When：配置这个locker的容量小于等于0 Then：得到报错信息，初始化Locker失败，Locker容量不能为0。
//- Given：有一个S locker ， 以及一个S Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为S。
//- Given：有一个M locker ， 以及一个M Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为M。
//- Given：有一个L locker ， 以及一个L Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为L。
//- Given：有一个L locker没容量 ， 以及一个L Bag When：使用该Locker存该包 Then：存包失败，该Locker已满。
//- Given：有一个L locker, 以及L 的ticket When：使用该Ticket取包 Then：取Bag成功，得到对应的Bag。
//- Given：有一个L locker, 以及M 的ticket When：使用该Ticket取包 Then：取Bag失败，得到报错信息，取包失败，请使用正确的Ticket。
//- Given：有一个L locker, 以及L 的invalid ticket When：使用该Ticket取包 Then：取Bag失败，得到报错信息，取包失败，请使用正确的Ticket。
public class LockerTest {

  @Test
  public void should_throw_error_when_locker_is_config_given_capability_is_0() {
    LockerException lockerException = assertThrows(LockerException.class, () -> new Locker(0, BagType.S));
    assertEquals("Initialization of locker failed, the capability can't be 0", lockerException.getMessage());
  }

  @Test
  public void should_save_successfully_and_get_S_ticket_when_save_S_bag_given_Locker_is_S()
      throws LockerException, NoEmptyLockerException, InvalidBagType {
    Locker locker = new Locker(2, BagType.S);
    Bag bag = new Bag(BagType.S);

    Ticket ticket = locker.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(BagType.S, ticket.getBagType());
  }

  @Test
  public void should_save_successfully_and_get_M_ticket_when_save_M_bag_given_Locker_is_M()
      throws LockerException, NoEmptyLockerException, InvalidBagType {
    Locker locker = new Locker(2, BagType.M);
    Bag bag = new Bag(BagType.M);

    Ticket ticket = locker.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(BagType.M, ticket.getBagType());
  }

  @Test
  public void should_save_successfully_and_get_L_ticket_when_save_L_bag_given_Locker_is_L()
      throws LockerException, NoEmptyLockerException, InvalidBagType {
    Locker locker = new Locker(2, BagType.L);
    Bag bag = new Bag(BagType.L);

    Ticket ticket = locker.saveBag(bag);

    assertNotNull(ticket);
    assertEquals(BagType.L, ticket.getBagType());
  }

  @Test
  public void should_fail_to_save_when_save_L_bag_given_Locker_is_L_but_full() throws LockerException, NoEmptyLockerException, InvalidBagType {
    Locker locker = new Locker(1, BagType.L);
    Bag bag1 = new Bag(BagType.L);
    Bag bag2 = new Bag(BagType.L);
    locker.saveBag(bag1);

    NoEmptyLockerException noEmptyLockerException = assertThrows(NoEmptyLockerException.class, () -> locker.saveBag(bag2));
    assertEquals("Fail to save, locker is full", noEmptyLockerException.getMessage());
  }

  @Test
  public void should_fail_to_save_when_save_L_bag_given_Locker_is_M() throws LockerException {
    Locker locker = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.M);

    InvalidBagType invalidBagType = assertThrows(InvalidBagType.class, () -> locker.saveBag(bag));
    assertEquals("Fail to save, wrong bag type", invalidBagType.getMessage());
  }

  @Test
  public void should_get_L_bag_successfully_when_get_bag_from_L_Locker_given_ticket_is_L()
      throws NoEmptyLockerException, LockerException, InvalidTicket, InvalidBagType {
    Locker locker = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.L);
    Ticket ticket = locker.saveBag(bag);

    Bag myBag = locker.getBag(ticket);

    assertEquals(bag, myBag);
  }

  @Test
  public void should_fail_to_get_L_bag_when_get_bag_from_M_Locker_given_ticket_is_L() throws NoEmptyLockerException, LockerException, InvalidBagType {
    Locker locker = new Locker(1, BagType.M);
    Bag bag = new Bag(BagType.M);
    locker.saveBag(bag);
    Ticket wrongTicket = new Ticket(BagType.L);

    InvalidTicket invalidTicket = assertThrows(InvalidTicket.class, () -> locker.getBag(wrongTicket));

    assertEquals("Fail to get the bag, invalid ticket.", invalidTicket.getMessage());
  }

  @Test
  public void should_fail_to_get_L_bag_when_get_bag_from_L_Locker_given_ticket_is_L_but_invalid()
      throws NoEmptyLockerException, LockerException, InvalidBagType {
    Locker locker = new Locker(1, BagType.L);
    Bag bag = new Bag(BagType.L);
    locker.saveBag(bag);
    Ticket wrongTicket = new Ticket(BagType.L);

    InvalidTicket invalidTicket = assertThrows(InvalidTicket.class, () -> locker.getBag(wrongTicket));

    assertEquals("Fail to get the bag, invalid ticket.", invalidTicket.getMessage());
  }

}
