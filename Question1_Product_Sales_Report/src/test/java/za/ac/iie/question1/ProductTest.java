package za.ac.iie.question1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product createProduct() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{10, 20, 30}, {5, 10, 15}, {8, 12, 20}};
        return new Product(names, sales);
    }

    @Test
    void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(130, createProduct().GetTotalProductsSold());
    }

    @Test
    void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(130.0 / 9, createProduct().GetAverageProductSold(), 0.0001);
    }
}
