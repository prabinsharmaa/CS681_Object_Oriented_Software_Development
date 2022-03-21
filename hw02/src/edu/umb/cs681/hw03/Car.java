package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.umb.cs681.hw02.Car;

import java.util.List;
import java.util.Comparator;


public class Car {

    private String make, model;
    private int mileage, year;
    private int price;
    private int dominationCount;

    public Car(String make, String model, int mileage, int year, int price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car car : cars) {
            if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                    && (car.getYear() <= this.getYear())) {
                this.dominationCount++;
            }
        }
        this.dominationCount--;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    @Override
    public String toString() {

        return this.make +" "+ this.model+" "+ this.mileage+" "+this.year+" "+this.price;
    }

    public static void main(String[] args) {

        List<Car> carList = new ArrayList<Car>();
        carList.add(new Car("Toyota", "X", 90, 2018, 70000));
		carList.add(new Car("Tesla", "Y", 100, 2019, 55000));
		carList.add(new Car("Ford", "Mustang", 90, 2020, 45000));
		carList.add(new Car("Honda", "A7", 25, 2020, 74000));

        Integer minPrice = carList.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0,(result, carPrice) -> {
                    if (result == 0)
                        return carPrice;
                    else if (carPrice < result)
                        return carPrice;
                    else
                        return result;
                });
        System.out.println("Minimum price in the given cars list is " + minPrice);

        Integer maxPrice = carList.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0,(result, carPrice) -> {
                    if (result == 0)
                        return carPrice;
                    else if (carPrice > result)
                        return carPrice;
                    else
                        return result;
                });
        System.out.println("Maximum price in the given cars list is " + maxPrice);

        System.out.print("Average price of all cars: ");
        var avPrice = carList.stream()
                .map(Car::getPrice)
                .reduce(new int[2], (result, price) -> {
                    var r = (result[1] * result[0] + price) / ++result[0];
                    return new int[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(avPrice);

    }
}