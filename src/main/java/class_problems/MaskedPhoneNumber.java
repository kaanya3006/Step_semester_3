package class_problems;

public class MaskedPhoneNumber {

    static String maskPhoneNumber(String phone) {

        // Check length
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Check whether all characters are digits
        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);

        StringBuilder masked = new StringBuilder("XXXXXX");

        masked.insert(6, "-");

        masked.append(lastFour);

        return masked.toString();
    }

    public static void main(String[] args) {

        String phone = "9876543210";

        System.out.println(maskPhoneNumber(phone));
    }
}