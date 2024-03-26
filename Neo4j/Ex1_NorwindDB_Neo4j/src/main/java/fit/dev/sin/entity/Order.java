package fit.dev.sin.entity;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Order {
    @SerializedName("orderID")
    private String orderID;
    @SerializedName("customerID")
    private Customer customerID;
    @SerializedName("shipPostalCode")

    private String shipPostalCode;
    @SerializedName("shipVia")

    private String shipVia;
    @SerializedName("shippedDate")

    private LocalDate shippedDate;
    @SerializedName("orderDate")

    private LocalDate orderDate;

    public Order(String orderID, Customer customerID, String shipPostalCode, String shipVia, LocalDate shippedDate, LocalDate orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.shipPostalCode = shipPostalCode;
        this.shipVia = shipVia;
        this.shippedDate = shippedDate;
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID=" + customerID +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipVia='" + shipVia + '\'' +
                ", shippedDate=" + shippedDate +
                ", orderDate=" + orderDate +
                '}';
    }
}
