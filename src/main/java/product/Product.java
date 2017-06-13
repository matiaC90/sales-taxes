package product;

import java.math.BigDecimal;

public class Product {
	private BigDecimal price;
	private String name;
	private ProductType type;
	private Boolean isImported;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsImported() {
		return isImported;
	}

}
