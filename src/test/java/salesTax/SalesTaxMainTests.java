package salesTax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import cart.BuyedProductResource;
import cart.Cart;
import factory.SalesTaxesFactory;
import junit.framework.TestCase;
import product.ProductType;
import receipt.Order;
import receipt.Receipt;
import utils.ResourceUtils;

/**
 * Unit test for simple App.
 */
public class SalesTaxMainTests extends TestCase {

	public void testCorrectlyReadBuyedProductInCartOnResourceFile() {
		Cart cart = ResourceUtils.getCartFromResourceName(("input1.json"));
		BuyedProductResource firstBuyedProduct = cart.getBuyedProducts().iterator().next();
		assertFalse(firstBuyedProduct.getProduct().getIsImported());
		assertEquals(firstBuyedProduct.getProduct().getName(), "Book");
		assertEquals(firstBuyedProduct.getQuantity(), 1);
		assertEquals(firstBuyedProduct.getProduct().getPrice(),
				new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP));
		assertEquals(firstBuyedProduct.getProduct().getType(), ProductType.BOOK);

	}

	public void testOrderWithExemption() {
		Cart cart = ResourceUtils.getCartFromResourceName(("input1.json"));
		ArrayList<BuyedProductResource> listBuyedProducts = new ArrayList<BuyedProductResource>(
				cart.getBuyedProducts());
		BuyedProductResource buyedProduct = listBuyedProducts.get(0);
		assertEquals(buyedProduct.getProduct().getType(), ProductType.BOOK);
		assertEquals(buyedProduct.getProduct().getPrice(), new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP));
		BigDecimal noTaxes = SalesTaxesFactory.getTaxCalculator().calculateTaxes(buyedProduct.getProduct());
		assertEquals(noTaxes, new BigDecimal(0).setScale(2, RoundingMode.HALF_UP));

	}

	public void testOrderWithBasicTaxes() {
		Cart cart = ResourceUtils.getCartFromResourceName(("input1.json"));
		ArrayList<BuyedProductResource> listBuyedProducts = new ArrayList<BuyedProductResource>(
				cart.getBuyedProducts());
		BuyedProductResource buyedProduct = listBuyedProducts.get(1);
		assertEquals(buyedProduct.getProduct().getName(), "Music Cd");
		assertEquals(buyedProduct.getProduct().getType(), ProductType.OTHER);
		assertEquals(buyedProduct.getProduct().getPrice(), new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP));
		BigDecimal basicTaxes = SalesTaxesFactory.getTaxCalculator().calculateTaxes(buyedProduct.getProduct());
		assertEquals(basicTaxes, new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP));

	}

	public void testOrderWithExemptionButImportTaxes() {
		Cart cart = ResourceUtils.getCartFromResourceName(("input2.json"));
		ArrayList<BuyedProductResource> listBuyedProducts = new ArrayList<BuyedProductResource>(
				cart.getBuyedProducts());
		BuyedProductResource buyedProduct = listBuyedProducts.get(0);
		assertEquals(buyedProduct.getProduct().getName(), "imported box of chocolates");
		assertEquals(buyedProduct.getProduct().getType(), ProductType.FOOD);
		assertEquals(buyedProduct.getProduct().getPrice(), new BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP));
		BigDecimal onlyImportTaxes = SalesTaxesFactory.getTaxCalculator().calculateTaxes(buyedProduct.getProduct());
		assertEquals(onlyImportTaxes, new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP));

	}

	public void testOrderWithBasicAndImportTaxes() {
		Cart cart = ResourceUtils.getCartFromResourceName(("input2.json"));
		ArrayList<BuyedProductResource> listBuyedProducts = new ArrayList<BuyedProductResource>(
				cart.getBuyedProducts());
		BuyedProductResource buyedProduct = listBuyedProducts.get(1);
		assertEquals(buyedProduct.getProduct().getName(), "imported bottle of perfume");
		assertEquals(buyedProduct.getProduct().getType(), ProductType.OTHER);
		assertEquals(buyedProduct.getProduct().getPrice(), new BigDecimal(47.50).setScale(2, RoundingMode.HALF_UP));
		BigDecimal basicAndImportTaxes = SalesTaxesFactory.getTaxCalculator().calculateTaxes(buyedProduct.getProduct());
		assertEquals(basicAndImportTaxes, new BigDecimal(7.15).setScale(2, RoundingMode.HALF_UP));

	}
	
	
	public void testSingleOrderInReceiptForThreeResourceFileInput() {
		Receipt receipt = SalesTaxesFactory.getShoppingService()
				.purchaseProducts(ResourceUtils.getCartFromResourceName(("input1.json")));
		ArrayList<Order> listOrders = new ArrayList<Order>(
				receipt.getOrders());
		Order order = listOrders.get(1);
		assertEquals(order.getProduct().getName(), "Music Cd");
		assertEquals(order.getProduct().getType(), ProductType.OTHER);
		assertEquals(order.getProduct().getPrice(), new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP));
		assertEquals(order.getTaxes(), new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP));
		BigDecimal quantityBuyed = new BigDecimal(order.getQuantity());
		BigDecimal price = order.getProduct().getPrice().add(order.getTaxes()).multiply(quantityBuyed);
		assertEquals(order.getTotal(), price.setScale(2, RoundingMode.HALF_UP));
		
		

	}

	public void testFinalReceiptForThreeResourceFileInput() {
		Receipt receipt = SalesTaxesFactory.getShoppingService()
				.purchaseProducts(ResourceUtils.getCartFromResourceName(("input1.json")));
		assertEquals(receipt.getTotal(), new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP));
		assertEquals(receipt.getTotalTaxes(), new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP));
		
	}

}
