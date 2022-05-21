package edu.umb.cs681.hw16;

import java.util.ArrayList;

public class Car {
    private String model;
    private String make;
    private int mileage;
    private int year;
    private float price;
    private ArrayList<Car> cars;
    private int dominationCount = 0;

    public Car(String model, String make, int mileage, int year, float price) {
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void setDominationCount(int dominationCount) {
        this.dominationCount = dominationCount;
    }

    public void dominationCount() {
        int count = 0;
        for (Car c : cars)
            if (price >= c.getPrice() && this.year >= c.getYear() && this.mileage >= c.getMileage())
                count++;
        setDominationCount(count);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", mileage=" + mileage +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Honda", "Civic", 2017, 30, 10_000f));
        cars.add(new Car("Ford", "Fiesta", 2012, 20, 8_000f));
        cars.add(new Car("Honda", "Accord", 2010, 20, 5_000f));
        cars.add(new Car("Toyota", "Prius", 2002, 20, 1_000f));
        cars.add(new Car("Ford", "Focus", 2006, 20, 3_000f));
        cars.add(new Car("Toyota", "Camry", 2008, 20, 4_000f));
        cars.add(new Car("Honda", "Fit", 2004, 20, 2_000f));
        cars.add(new Car("Toyota", "Corolla", 2015, 20, 16_000f));
        cars.add(new Car("Honda", "CR-V", 2010, 20, 7_000f));

        Integer carMakeNum = cars
                .stream()
                .parallel()
                .map((Car car) -> car.getMake())
                .reduce(
                        0,
                        (result, make) -> ++result,
                        (finalResult, intermediateResult) -> finalResult + intermediateResult
                );

        System.out.println("Number of car makes: " + carMakeNum);

        Integer carModelNum = cars
                .stream()
                .parallel()
                .map((Car car) -> car.getModel())
                .reduce(
                        0,
                        (result, model) -> ++result,
                        (finalResult, intermediateResult) -> finalResult + intermediateResult
                );

        System.out.println("Number of car models: " + carModelNum);
    }
}
