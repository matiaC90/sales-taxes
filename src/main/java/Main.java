import factory.SalesTaxesFactory;
import receipt.Receipt;
import utils.ResourceUtils;

public class Main {

	public static void main(String[] args) {

		System.out.println("Output 1");
		launchGivenByNameResource("input1.json");
		System.out.println("Fine Output 1");
		System.out.println("\n");
		System.out.println("Output 2");
		launchGivenByNameResource("input2.json");
		System.out.println("Fine Output 2");
		System.out.println("\n");
		System.out.println("Output 3");
		launchGivenByNameResource("input3.json");
		System.out.println("Fine Output 3");

	}

	public static void launchGivenByNameResource(String nameJsonFileResource) {
		Receipt receipt = SalesTaxesFactory.getShoppingService()
				.purchaseProducts(ResourceUtils.getCartFromResourceName((nameJsonFileResource)));
		SalesTaxesFactory.getShoppingService().printReceipt(receipt);
	}

}
