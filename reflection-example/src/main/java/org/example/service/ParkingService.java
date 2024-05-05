package org.example.service;

import org.example.db.ParkingDb;
import org.example.di.ExampleComponent;
import org.example.model.Car;

/**
 * Відповідальний за всі дії, які можуть відбуватися на парковці:
 * <pre>
 * - паркування автомобілів
 * - видалення автомобілів з парковки
 * </pre>
 */
@ExampleComponent(priority = 2)
public class ParkingService {

    private final ParkingDb parkingDb;
    private final int parkingSize;

    /**
     * Конструктор класу.
     */
    public ParkingService(ParkingDb parkingDb) {
        this.parkingDb = parkingDb;
        this.parkingSize = 10;
    }

    /**
     * Додає автомобіль на парковку, якщо ще є вільне місце.
     *
     * @param car автомобіль, який треба припаркувати
     * @return true - якщо автомобіль вдалося припаркувати,
     * false - якщо на парковці не було місця для автомобіля
     */
    public boolean parkCar(Car car) {
        if (parkingDb.getAmountOfCars() == parkingSize) {
            return false;
        }

        parkingDb.addCar(car);
        return true;
    }

    /**
     * Видаляє автомобіль з парковки.
     *
     * @param carNumber номер автомобіля, який потрібно видалити
     */
    public void removeCar(String carNumber) {
        parkingDb.removeCar(carNumber);
    }
}
