package class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    // Approach 1: Iterative
    static boolean isPalindromeIterative(String text) {

        int start = 0;
        int end = text.length() - 1;

        while (start < end) {

            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    // Approach 2: Recursive
    static boolean isPalindromeRecursive(String text) {

        return checkRecursive(text, 0, text.length() - 1);
    }

    static boolean checkRecursive(String text, int start, int end) {

        if (start >= end) {
            return true;
        }

        if (text.charAt(start) != text.charAt(end)) {
            return false;
        }

        return checkRecursive(text, start + 1, end - 1);
    }

    // Approach 3: Array reversal
    static boolean isPalindromeArrayReversal(String text) {

        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        String reversedText = new String(reversed);

        return text.equals(reversedText);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        System.out.println();

        System.out.println("Iterative: "
                + (iterative ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: "
                + (recursive ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: "
                + (arrayReversal ? "Palindrome" : "Not Palindrome"));

        scanner.close();
    }
}