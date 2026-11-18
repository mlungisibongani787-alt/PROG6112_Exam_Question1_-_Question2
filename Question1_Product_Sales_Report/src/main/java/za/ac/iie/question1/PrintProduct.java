package za.ac.iie.question1;

public class PrintProduct {
    public void PrintTotalProductSales(IProducts product) {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageProductSales(IProducts product) {
        System.out.printf("AVERAGE PRODUCT SALES: %.2f%n", product.GetAverageProductSold());
    }

    public void PrintMostPopularProduct(IProducts product) {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void PrintReport(IProducts product) {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("****************************************");
        PrintTotalProductSales(product);
        PrintAverageProductSales(product);
        PrintMostPopularProduct(product);
    }
}
