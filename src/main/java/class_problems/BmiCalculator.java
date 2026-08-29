package class_problems;

import java.util.Scanner;

public class BmiCalculator {

    static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("Person | Height (m) | Weight (kg) | BMI  | Status");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            System.out.printf(
                    "%-6d | %-11.2f | %-12.2f | %-4.2f | %s%n",
                    (i + 1),
                    heights[i],
                    weights[i],
                    bmi,
                    getBmiStatus(bmi)
            );
        }

        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of people: ");
        int n = scanner.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter height for Person "
                    + (i + 1) + " in meters: ");
            heights[i] = scanner.nextDouble();

            System.out.print("Enter weight for Person "
                    + (i + 1) + " in kg: ");
            weights[i] = scanner.nextDouble();
        }

        printWellnessReport(heights, weights);

        scanner.close();
    }
}