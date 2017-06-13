package utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import cart.Cart;

public class ResourceUtils {
	public static Cart getCartFromResourceName(String nameJsonFileResource) {
		Cart cart = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			ClassLoader classLoader = ResourceUtils.class.getClassLoader();
			File inputBuyedItems = new File(classLoader.getResource(nameJsonFileResource).getFile());
			cart = mapper.readValue(inputBuyedItems, Cart.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cart;
	}
}
