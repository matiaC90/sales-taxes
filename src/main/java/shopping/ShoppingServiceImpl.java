package shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import cart.BuyedProductResource;
import cart.Cart;
import factory.SalesTaxesFactory;
import product.Product;
import receipt.Order;
import receipt.Receipt;

public class ShoppingServiceImpl implements ShoppingServiceInf {

	public Receipt purchaseProducts(Cart cart) {

		Collection<Order> orders = new ArrayList<Order>();
		BigDecimal totalTaxes = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		for (BuyedProductResource buyedItem : cart.getBuyedProducts()) {
			Order order = makeOrder(buyedItem.getProduct(), buyedItem.getQuantity());
			orders.add(order);
			totalTaxes = totalTaxes.add(order.getTaxes());
			total = total.add(order.getTotal());
		}

		return new Receipt(orders, totalTaxes, total);

	}

	private Order makeOrder(Product product, int quantity) {

		BigDecimal quantityBuyed = new BigDecimal(quantity);
		BigDecimal taxes = SalesTaxesFactory.getTaxCalculator().calculateTaxes(product).multiply(quantityBuyed);
		BigDecimal total = product.getPrice().multiply(quantityBuyed).add(taxes);
		return new Order(product, taxes, total, quantity);

	}

	public void printReceipt(Receipt receipt) {
		for (Order purchase : receipt.getOrders()) {
			System.out.println(String.format("%1$-1s %2$-10s %3$10s", purchase.getQuantity(),
					purchase.getProduct().getName() + ":", purchase.getTotal()));
		}

		System.out.println(String.format("%1$-10s %2$10s", "Sales Taxes:", receipt.getTotalTaxes()));
		System.out.println(String.format("%1$-10s %2$10s", "Total:", receipt.getTotal()));

	}

}
