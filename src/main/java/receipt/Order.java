package receipt;

import java.math.BigDecimal;

import product.Product;

public class Order {
	private Product product;
	private int quantity;
	private BigDecimal taxes;
	private BigDecimal total;

	public Order(Product product, BigDecimal taxes, BigDecimal total, int quantity) {
		this.product = product;
		this.total = total;
		this.taxes = taxes;
		this.quantity = quantity;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

}
