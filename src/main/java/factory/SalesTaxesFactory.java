package factory;

import shopping.ShoppingServiceImpl;
import tax.TaxCalculatorServiceImpl;

public class SalesTaxesFactory {

	public static TaxCalculatorServiceImpl getTaxCalculator() {
		return new TaxCalculatorServiceImpl();
	}

	public static ShoppingServiceImpl getShoppingService() {
		return new ShoppingServiceImpl();
	}

}
