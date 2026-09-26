package assignment_problems;

import java.util.*;

abstract class Seat {
    protected String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {

    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 400;
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

class Show {

    private String time;

    private ArrayList<Seat> bookedSeats =
            new ArrayList<>();

    public Show(String time) {
        this.time = time;
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat);
    }

    public boolean bookSeat(Seat seat) {

        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat);
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat);
    }

    public String getTime() {
        return time;
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private ArrayList<Seat> seats;
    private boolean cancelled = false;

    public Booking(
            Customer customer,
            Show show,
            ArrayList<Seat> seats) {

        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    public double getTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel(boolean beforeShow) {

        if (!beforeShow) {
            System.out.println(
                    "Cannot cancel: show has already started.");
            return;
        }

        if (cancelled) {
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(
                customer.getName()
                        + "'s booking cancelled.");

        System.out.println(
                "Seats released.");
    }
}

class BookingSystem {

    public Booking book(
            Customer customer,
            Show show,
            Seat[] seats) {

        if (seats.length > 6) {
            System.out.println(
                    "Cannot book more than 6 seats.");
            return null;
        }

        for (Seat seat : seats) {

            if (!show.isAvailable(seat)) {
                System.out.println(
                        "Seat "
                                + seat.getSeatNumber()
                                + " is already booked for this show.");
                return null;
            }
        }

        ArrayList<Seat> selected =
                new ArrayList<>();

        for (Seat seat : seats) {
            show.bookSeat(seat);
            selected.add(seat);
        }

        Booking booking =
                new Booking(
                        customer,
                        show,
                        selected);

        System.out.println(
                "Booking confirmed for "
                        + customer.getName());

        System.out.printf(
                "Total: ₹%.2f%n",
                booking.getTotal());

        return booking;
    }
}

public class CampusPremiereTicketCounter{

    public static void main(String[] args) {

        BookingSystem system =
                new BookingSystem();

        Show show =
                new Show("7 PM");

        Customer asha =
                new Customer("Asha");

        Customer ravi =
                new Customer("Ravi");

        Customer neha =
                new Customer("Neha");

        Seat a1 =
                new RegularSeat("A1");

        Seat a2 =
                new RegularSeat("A2");

        Seat f5 =
                new PremiumSeat("F5");

        Seat r1 =
                new ReclinerSeat("R1");

        Booking ashaBooking =
                system.book(
                        asha,
                        show,
                        new Seat[]{a1, a2, f5});

        system.book(
                ravi,
                show,
                new Seat[]{a2});

        Booking raviBooking =
                system.book(
                        ravi,
                        show,
                        new Seat[]{r1});

        ashaBooking.cancel(true);

        system.book(
                neha,
                show,
                new Seat[]{a2});
    }
}
