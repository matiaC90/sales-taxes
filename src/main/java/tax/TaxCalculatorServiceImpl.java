package tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

import product.Product;
import product.ProductType;

public class TaxCalculatorServiceImpl implements TaxCalculatorServiceInf {

	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static final BigDecimal BASIC_TAXES = new BigDecimal(10);
	private static final BigDecimal IMPORT_TAXES = new BigDecimal(5);
	private static final BigDecimal TWENTY = new BigDecimal(20);

	public BigDecimal calculateTaxes(Product product) {
		BigDecimal taxes = BigDecimal.ZERO;

		if (product.getType().equals(ProductType.OTHER)) {
			taxes = taxes.add(product.getPrice().multiply(BASIC_TAXES).divide(ONE_HUNDRED));
		}

		if (product.getIsImported()) {
			taxes = taxes.add(product.getPrice().multiply(IMPORT_TAXES).divide(ONE_HUNDRED));
		}

		return roundTaxes(taxes);
	}

	private BigDecimal roundTaxes(BigDecimal taxes) {
		return taxes.multiply(TWENTY).setScale(0, RoundingMode.UP).divide(TWENTY).setScale(2);
	}

}
