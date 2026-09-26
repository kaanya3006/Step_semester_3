package class_problems;

import java.util.*;

abstract class Vehicle {
    protected String id;
    protected boolean available = true;

    public Vehicle(String id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    public SUV(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    public Truck(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public double getCharge() {
        return vehicle.calculateCharge(days);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class RentalSystem {

    private ArrayList<Rental> rentals = new ArrayList<>();

    public void rentVehicle(Customer customer, Vehicle vehicle, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId() +
                    " is currently unavailable.");
            return;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);

        System.out.println(vehicle.getId() +
                " rented successfully by " +
                customer.getName());

        System.out.println("Rental charge: $" +
                rental.getCharge());
    }

    public void returnVehicle(Vehicle vehicle) {

        for (Rental rental : rentals) {
            if (rental.getVehicle() == vehicle) {

                vehicle.setAvailable(true);

                System.out.println(vehicle.getId() +
                        " returned by " +
                        rental.getCustomer().getName());

                rentals.remove(rental);
                return;
            }
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        RentalSystem system = new RentalSystem();

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        system.rentVehicle(c1, sedan, 3);

        system.rentVehicle(c2, sedan, 2);

        system.returnVehicle(sedan);

        system.rentVehicle(c3, suv, 5);
    }
}
