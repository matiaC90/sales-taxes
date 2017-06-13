package tax;

import java.math.BigDecimal;

import product.Product;

public interface TaxCalculatorServiceInf {
	BigDecimal calculateTaxes(Product item);
}
