package fit.dev.sin.entity;

import com.google.gson.annotations.SerializedName;

public class Customer {
    // customerID
    @SerializedName("customerID")
    private String customerID;
    @SerializedName("contactName")
    private String contactName;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;

    public Customer(String customerID, String contactName, String companyName, String address, String city) {
        this.customerID = customerID;
        this.contactName = contactName;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", contactName='" + contactName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
