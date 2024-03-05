package Entity;

public class Product {
    // productID	productName	supplier	category	quantityPerUnit	unitPrice	unitsInStock	unitsOnOrder	reorderLevel	discontinued
    private int productID;
    private String productName;
    private Supplier supplier;
    private Category category;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;

    public Product() {
    }

    public Product(int productID, String productName, Supplier supplier, Category category, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, boolean discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", supplier=" + supplier +
                ", category=" + category +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", reorderLevel=" + reorderLevel +
                ", discontinued=" + discontinued +
                '}';
    }
}
