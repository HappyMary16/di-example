package org.example;

import org.example.model.Car;
import org.example.service.DIService;
import org.example.service.ParkingService;
import org.example.service.StatisticService;

import java.time.Instant;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("З використанням патерну Інʼєкція залежностей (DI - Dependency Injection)");
        System.out.println("--------------------------");

        DIService diService = new DIService();

        Car car = new Car("AA1111AA", Instant.now());

        System.out.println("Паркуємо автомобіль (в ParkingService): "
                                   + diService.getBean(ParkingService.class).parkCar(car));
        System.out.println("Припарковані автомобілі (з ParkingService): "
                                   + diService.getBean(ParkingService.class).getAllCars());
        System.out.println("Кількість автомобілів на парковці (з StatisticService): "
                                   + diService.getBean(StatisticService.class).getAmountOfCars());
    }
}