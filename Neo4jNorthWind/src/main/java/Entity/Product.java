package entity;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class Product {
	@SerializedName("productID")
	private String id;
	@SerializedName("productName")
	private String name;
	private double unitPrice;
	private int unitsInStock;
	private boolean discontinued;
	private String quantityPerUnit;
	
	@ToString.Exclude
	private Category category;

	public Product(String id, String name, double price, int unitsInStock, boolean discontinued,
			String quantityPerUnit) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = price;
		this.unitsInStock = unitsInStock;
		this.discontinued = discontinued;
		this.quantityPerUnit = quantityPerUnit;
	}
	
}
