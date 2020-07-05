## Tasking mind:
![x-mind](./LockerRobotTaskingMap.png)

## Tasking
### Locker
- Given：有一个locker When：配置这个locker的容量小于等于0 Then：得到报错信息，初始化Locker失败，Locker容量不能为0。
- Given：有一个S locker有容量 ， 以及一个S Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为S。
- Given：有一个M locker有容量 ， 以及一个M Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为M。
- Given：有一个L locker有容量 ， 以及一个L Bag When：使用该Locker存该包 Then：存包成功，得到Ticket且类型为L。
- Given：有一个L locker没容量 ， 以及一个L Bag When：使用该Locker存该包 Then：存包失败，该Locker已满。
- Given：有一个L locker有容量 ， 以及一个M Bag When：使用该Locker存该包 Then：存包失败，请将包存入正确的locker
- Given：有一个L locker有容量, 以及L 的ticket When：使用该Ticket取包 Then：取Bag成功，得到对应的Bag。
- Given：有一个L locker有容量, 以及M 的ticket When：使用该Ticket取包 Then：取Bag失败，得到报错信息，取包失败，请使用正确的Ticket。

### PrimaryLockerRobot
- Given: 有一个S Locker When：使用该locker配置 PrimaryLockerRobot Then： 初始化失败
- Given: 有一个管理 MLocker1（ 两个容量）和 MLocker2（三个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包成功，且存到 MLocker1 得到MTicket
- Given: 有一个管理 MLocker1（ 已满）和 MLocker2（三个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包成功，且存到 MLocker2 得到MTicket
- Given: 有一个管理 MLocker1（ 已满）和 MLocker2（已满）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存MBag Then： 存包失败，locker已满
- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot When：使用该 PrimaryLockerRobot 存LBag Then： 存包失败，请将包存入正确的locker
- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个合法的M Ticket When：在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包成功，等到包
- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个L Ticket When：使用在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包失败，请使用正确的Ticket
- Given: 有一个管理 MLocker1（ 一个容量）的PrimaryLockerRobot， 且有一个非法 Ticket When：使用在 PrimaryLockerRobot上使用该ticket 取MBag Then： 取包失败，请使用正确的Ticket

### SuperLockerRobot
- Given: 有一个S Locker When：使用该locker配置 SuperLockerRobot Then： 初始化失败
- Given: 有一个管理 LLocker1（ 4个容量,存2）和 LLocker2（4个容量,存1）的SuperLockerRobot When：使用该 SuperLockerRobot 存LBag Then： 存包成功，且存到 LLocker2 得到LTicket
- Given: 有一个管理 LLocker1（  2个容量,存1）和 LLocker2（2个容量,存1）的SuperLockerRobot When：使用该 SuperLockerRobot 存LBag Then： 存包成功，且存到 LLocker1 得到LTicket
- Given: 有一个管理 LLocker1（ 已满）和 LLocker2（已满）的SuperLockerRobot When：使用该 SuperLockerRobot 存LBag Then： 存包失败，locker已满
- Given: 有一个管理 LLocker1（ 一个容量）的SuperLockerRobot When：使用该 SuperLockerRobot 存MBag Then： 存包失败，请将包存入正确的locker
- Given: 有一个管理 LLocker1（ 一个容量）的SuperLockerRobot， 且有一个合法的LTicket When： 在SuperLockerRobot上使用该ticket 取LBag Then： 取包成功，等到包
- Given: 有一个管理 LLocker1（ 一个容量）的SuperLockerRobot， 且有一个M Ticket When：在 SuperLockerRobot上使用该ticket 取LBag Then： 取包失败，请使用正确的Ticket
- Given: 有一个管理 LLocker1（ 一个容量）的SuperLockerRobot， 且有一个非法 Ticket When：使用在 SuperLockerRobot上使用该ticket 取LBag Then： 取包失败，请使用正确的Ticket
