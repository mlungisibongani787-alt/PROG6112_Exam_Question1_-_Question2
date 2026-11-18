package za.ac.iie.question1;

import java.util.Scanner;

public class Main {
    private static final String[] PRODUCT_NAMES = {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    };

    public static void main(String[] args) {
        int[][] monthlySales = new int[PRODUCT_NAMES.length][3];
        try (Scanner scanner = new Scanner(System.in)) {
            for (int product = 0; product < PRODUCT_NAMES.length; product++) {
                for (int month = 0; month < 3; month++) {
                    monthlySales[product][month] = readSales(scanner, product, month);
                }
            }
            Product products = new Product(PRODUCT_NAMES, monthlySales);
            new PrintProduct().PrintReport(products);
        }
    }

    private static int readSales(Scanner scanner, int product, int month) {
        while (true) {
            System.out.print("Enter number of sales for month " + (month + 1)
                    + " for " + PRODUCT_NAMES[product] + " mattresses: ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= 0) return value;
                System.out.println("Please enter zero or a positive whole number.");
            } else {
                System.out.println("Please enter a valid whole number.");
                scanner.next();
            }
        }
    }
}
