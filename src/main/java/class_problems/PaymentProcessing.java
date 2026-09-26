package class_problems;

import java.util.*;

class Custom {
    private String name;

    public Custom (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return true;
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return false;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return true;
    }

    @Override
    public String getName() {
        return "Bank Transfer";
    }
}

enum OrderStatus {
    PENDING,
    PAID
}

class Order {

    private Customer customer;

    private ArrayList<OrderItem> items =
            new ArrayList<>();

    private OrderStatus status =
            OrderStatus.PENDING;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {
            System.out.println(
                    "Cannot process payment for an empty order.");
            return;
        }

        System.out.println(
                "Payment initiated via "
                        + paymentMethod.getName()
                        + " for Order "
                        + customer.getName());

        boolean success =
                paymentMethod.processPayment(getTotal());

        if (success) {

            status = OrderStatus.PAID;

            System.out.println(
                    "Payment for Order "
                            + customer.getName()
                            + " successful.");

        } else {

            System.out.println(
                    "Payment for Order "
                            + customer.getName()
                            + " failed.");
        }

        System.out.println(
                "Order status: " + status);
    }
}

public class PaymentProcessing{

    public static void main(String[] args) {

        Customer x =
                new Customer("X");

        Product a =
                new Product("Product A", 100);

        Product b =
                new Product("Product B", 50);

        Order orderX =
                new Order(x);

        System.out.println(
                "Order created for Customer X.");

        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);

        orderX.pay(
                new CreditCardPayment());


        Customer y =
                new Customer("Y");

        Order orderY =
                new Order(y);

        orderY.pay(
                new CreditCardPayment());


        Customer z =
                new Customer("Z");

        Product c =
                new Product("Product C", 200);

        Order orderZ =
                new Order(z);

        System.out.println(
                "Order created for Customer Z.");

        orderZ.addProduct(c, 1);

        orderZ.pay(
                new PayPalPayment());
    }
}