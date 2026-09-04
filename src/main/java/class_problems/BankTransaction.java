package class_problems;

public class BankTransaction {

    static String normalizeReference(String raw) {

        // Remove leading and trailing spaces
        String reference = raw.trim();

        // Uppercase only first 3 characters
        String bankCode =
                reference.substring(0, 3).toUpperCase();

        String remaining =
                reference.substring(3);

        return bankCode + remaining;
    }

    static String validateAndFormat(String reference) {

        // Check length
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are digits
        for (int i = 3; i < reference.length(); i++) {

            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        String formattedDate =
                date.substring(0, 2) + "/" +
                        date.substring(2, 4) + "/" +
                        date.substring(4, 6);

        StringBuilder result = new StringBuilder();

        result.append("[")
                .append(bankCode)
                .append("] DATE: ")
                .append(formattedDate)
                .append(" | SEQ: ")
                .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        String raw = " hdf03022600042 ";

        String normalized =
                normalizeReference(raw);

        System.out.println(
                validateAndFormat(normalized)
        );
    }
}