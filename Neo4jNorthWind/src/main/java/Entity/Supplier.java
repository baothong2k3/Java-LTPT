package entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Supplier {
	private String id;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String fax;
	private String phone;	
	private String homePage;
	
	private List<Product> products;
	
}
