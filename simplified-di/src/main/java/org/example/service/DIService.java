package org.example.service;

import org.example.db.ParkingDb;

import java.util.HashMap;
import java.util.Map;

public class DIService {

    Map<Class, Object> beans;

    public DIService() {
        this.beans = new HashMap<>();
        initContext();
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    private void initContext() {
        ParkingDb parkingDb = new ParkingDb();
        beans.put(parkingDb.getClass(), parkingDb);

        Integer parkingSize = 10;
        beans.put(parkingSize.getClass(), parkingSize);

        ParkingService parkingService = new ParkingService(getBean(Integer.class), getBean(ParkingDb.class));
        beans.put(parkingService.getClass(), parkingService);

        StatisticService statisticService = new StatisticService(getBean(ParkingDb.class));
        beans.put(statisticService.getClass(), statisticService);
    }
}
