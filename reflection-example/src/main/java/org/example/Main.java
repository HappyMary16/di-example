package org.example;

import org.example.di.DIContext;
import org.example.model.Car;
import org.example.service.ParkingService;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        DIContext diContext = new DIContext();

        System.out.println("Контекст до ініціалізації: " + diContext.getAllBeanClassNames());

        diContext.init(Main.class.getPackageName());

        System.out.println("Контекст після ініціалізації: " + diContext.getAllBeanClassNames());

        ParkingService parkingService = diContext.getBean(ParkingService.class);

        Car car = new Car("AA1111AA", Instant.now());
        System.out.println("Машина припаркована: " + parkingService.parkCar(car));
    }
}