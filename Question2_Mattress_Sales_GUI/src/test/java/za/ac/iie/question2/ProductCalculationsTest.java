package za.ac.iie.question2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductCalculationsTest {
    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500.0);
        assertEquals(525.0, calculations.GetVat(), 0.001);
    }

    @Test
    void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500.0);
        assertEquals(4200.0, calculations.GetTotal(), 0.001);
    }
}
