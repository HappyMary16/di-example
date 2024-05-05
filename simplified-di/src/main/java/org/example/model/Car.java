package org.example.model;

import java.time.Instant;

public class Car {

    private final String carNumber;
    private final Instant parkingTime;

    public Car(String carNumber, Instant parkingTime) {
        this.carNumber = carNumber;
        this.parkingTime = parkingTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public Instant getParkingTime() {
        return parkingTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                ", parkingTime=" + parkingTime +
                '}';
    }
}
