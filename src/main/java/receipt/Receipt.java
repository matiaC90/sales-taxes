package receipt;

import java.math.BigDecimal;
import java.util.Collection;

public class Receipt {
	private Collection<Order> orders;
	private BigDecimal totalTaxes;
	private BigDecimal total;

	public Receipt(Collection<Order> orders, BigDecimal taxes, BigDecimal total) {
		this.orders = orders;
		this.totalTaxes = taxes;
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

}
