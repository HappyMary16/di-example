### Структура створених обʼктів (з використанням патерну Інʼєкція залежностей - Dependency Injection)

    Головний клас                     Main
                                        |
    Керування контеустом           DIService
                                    /      \
    Сервіси              ParkingService   StatisticService
                                    \      /   
    Керування сховищем              ParkingDb
                                        |
    Сховище                      Map<String, Car>