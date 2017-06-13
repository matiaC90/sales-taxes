package shopping;

import cart.Cart;
import receipt.Receipt;

public interface ShoppingServiceInf {
	public Receipt purchaseProducts(Cart cart);

	public void printReceipt(Receipt receipt);
}
