package Class_problems;

class PaymentAccount {

    void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends PaymentAccount {

    void payInInstallments(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class M5_Payments {

    static void processPayment(PaymentAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            HostelFeeAccount hostel =
                    (HostelFeeAccount) account;

            hostel.payInInstallments(amount);

        } else {

            account.pay(amount);
        }
    }

    public static void main(String[] args) {

        PaymentAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new PaymentAccount(),
                new PaymentAccount()
        };

        double[] amounts = {
                60000,
                60000,
                60000,
                60000
        };

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (int i = 0; i < accounts.length; i++) {

            processPayment(accounts[i], amounts[i]);

            if (accounts[i] instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println(
                "Hostel accounts processed: " + hostelCount +
                        " | Day-scholar accounts processed: " +
                        dayScholarCount
        );
    }
}
