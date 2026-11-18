package za.ac.iie.question1;

/** Stores product names and their sales for three months. */
public class Product implements IProducts {
    private final String[] productNames;
    private final int[][] monthlySales;

    public Product(String[] productNames, int[][] monthlySales) {
        if (productNames == null || monthlySales == null || productNames.length == 0
                || productNames.length != monthlySales.length) {
            throw new IllegalArgumentException("Product names and sales must contain the same products.");
        }
        this.productNames = productNames.clone();
        this.monthlySales = new int[monthlySales.length][3];
        for (int i = 0; i < monthlySales.length; i++) {
            if (monthlySales[i] == null || monthlySales[i].length != 3) {
                throw new IllegalArgumentException("Each product must have sales for three months.");
            }
            for (int j = 0; j < 3; j++) {
                if (monthlySales[i][j] < 0) {
                    throw new IllegalArgumentException("Sales cannot be negative.");
                }
                this.monthlySales[i][j] = monthlySales[i][j];
            }
        }
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] product : monthlySales) {
            for (int sales : product) {
                total += sales;
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        // The sample report averages all product sales entries across three months.
        return (double) GetTotalProductsSold() / (productNames.length * 3);
    }

    @Override
    public String GetMostPopularProduct() {
        int bestIndex = 0;
        int highestTotal = -1;
        for (int i = 0; i < monthlySales.length; i++) {
            int productTotal = 0;
            for (int sales : monthlySales[i]) {
                productTotal += sales;
            }
            if (productTotal > highestTotal) {
                highestTotal = productTotal;
                bestIndex = i;
            }
        }
        return productNames[bestIndex] + " (" + highestTotal + ")";
    }
}
