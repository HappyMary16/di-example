package org.example.service;

import org.example.db.ParkingDb;
import org.example.model.Car;

import java.time.Instant;

/**
 * Працює зі статистичними даними, такими як:
 * <pre>
 *  - кількість автомобілів на парковці
 *  - час перебування автомобіля на парковці
 *  </pre>
 */
public class StatisticService {

    private final ParkingDb parkingDb;

    /**
     * Конструктор класу.
     */
    public StatisticService(ParkingDb parkingDb) {
        this.parkingDb = parkingDb;
    }

    /**
     * Рахує кількість автомобілів на парковці в даний момент часу.
     *
     * @return кількість автомобілів на парковці
     */
    public int getAmountOfCars() {
        return parkingDb.getAmountOfCars();
    }

    /**
     * Рахує час перебування автомобіля на парковці в секундах.
     *
     * @param carNumber номер автомобіля, для якого рахується час перебування на парковці
     * @return час перебування автомобіля на парковці в секундах
     */
    public long getParkingDuration(String carNumber) {
        Car car = parkingDb.getCar(carNumber);
        if (car == null) {
            return 0;
        }

        return Instant.now().getEpochSecond() - car.getParkingTime().getEpochSecond();
    }
}
