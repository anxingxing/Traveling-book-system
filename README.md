# Traveling-book-system
数据库大作业-旅行预订系统
实验要求：
基于MySQL，设计并实现一个简单的旅行预订系统。该系统涉及的信息有航班、大巴班车、宾馆房间和客户数据等信息。其关系模式如下：
FLIGHTS (String flightNum, int price, int numSeats, int numAvail, String FromCity, String ArivCity)；
HOTELS(String location, int price, int numRooms, int numAvail)；
BUS(String location, int price, int numBus, int numAvail)；
CUSTOMERS(String custName,custID)；
RESERVATIONS(String custName, int resvType, String resvKey) 

为简单起见，对所实现的应用系统作下列假设：
1.	在给定的一个班机上，所有的座位价格也一样；flightNum是表FLIGHTS的一个主码（primary key）。
2.	在同一个地方的所有的宾馆房间价格也一样；location是表HOTELS的一个主码。
3.	在同一个地方的所有大巴车价格一样；location是表 BUS的一个主码。
4.	custName是表CUSTOMERS的一个主码。
5.	表RESERVATIONS包含着那些和客户预订的航班、大巴车或宾馆房间相应的条目，具体的说，resvType指出预订的类型（1为预订航班，2为预订宾馆房间，3为预订大巴车），而resvKey是表RESERVATIONS的一个主码。
6.	在表FLIGHTS中，numAvail表示指定航班上的还可以被预订的座位数。对于一个给定的航班（flightNum）,数据库一致性的条件之一是，表RESERVATIONS中所有预订该航班的条目数加上该航班的剩余座位数必须等于该航班上总的座位数。这个条件对于表BUS和表HOTELS同样适用。

应用系统应完成如下基本功能：
1．	航班，大巴车，宾馆房间和客户基础数据的入库，更新（表中的属性也可以根据你的需要添加）。
2．	预定航班，大巴车，宾馆房间。
3．	查询航班，大巴车，宾馆房间，客户和预订信息。
4．	查询某个客户的旅行线路。
5．	检查预定线路的完整性。
6．	其他任意你愿意加上的功能。
7．	通过高级程序语言（C/C++、Java，无限定）访问数据库，提供基本界面
