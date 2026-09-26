package class_problems;

import java.util.*;

class customer {
    private String name;

    public customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Room {
    protected String roomNumber;
    protected boolean available = true;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 150;
    }
}

class Suite extends Room {

    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 250;
    }
}

class Reservation {

    private Customer customer;
    private Room room;
    private String startDate;
    private String endDate;
    private int days;
    private boolean active;

    public Reservation(
            Customer customer,
            Room room,
            String startDate,
            String endDate,
            int days) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.active = true;
    }

    public double getPrice() {
        return room.calculatePrice(days);
    }

    public void cancel() {

        if (!active) {
            System.out.println("Reservation already cancelled.");
            return;
        }

        active = false;
        room.setAvailable(true);

        System.out.println(
                "Reservation for "
                        + customer.getName()
                        + ", "
                        + room.getRoomNumber()
                        + " cancelled successfully.");
    }

    public Room getRoom() {
        return room;
    }

    public boolean isActive() {
        return active;
    }
}

class HotelSystem {

    private ArrayList<Reservation> reservations =
            new ArrayList<>();

    public boolean checkAvailability(Room room) {

        for (Reservation r : reservations) {

            if (r.getRoom() == room &&
                    r.isActive()) {

                return false;
            }
        }

        return true;
    }

    public Reservation reserve(
            Customer customer,
            Room room,
            String startDate,
            String endDate,
            int days) {

        if (!checkAvailability(room)) {

            System.out.println(
                    room.getRoomNumber()
                            + " is not available.");

            return null;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate,
                        days);

        reservations.add(reservation);

        room.setAvailable(false);

        System.out.println(
                "Reservation confirmed for "
                        + customer.getName()
                        + ", "
                        + room.getRoomNumber()
                        + " ("
                        + startDate + "-" + endDate + ").");

        System.out.println(
                "Price: $" + reservation.getPrice());

        return reservation;
    }
}

public class HotelBookingSystem{

    public static void main(String[] args) {

        HotelSystem hotel = new HotelSystem();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard =
                new StandardRoom("Standard Room 101");

        Room deluxe =
                new DeluxeRoom("Deluxe Room 201");

        System.out.println(
                "Standard Room 101 is available.");

        Reservation r =
                hotel.reserve(
                        a,
                        standard,
                        "Jan 1",
                        "Jan 5",
                        4);

        hotel.reserve(
                b,
                standard,
                "Jan 3",
                "Jan 7",
                4);

        if (r != null) {
            r.cancel();
        }

        hotel.reserve(
                c,
                deluxe,
                "Feb 10",
                "Feb 12",
                2);
    }
}
