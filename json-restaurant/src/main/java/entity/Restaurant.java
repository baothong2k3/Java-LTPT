package entity;

import java.util.List;

public class Restaurant {
    private String restaurant_id;
    private boolean isclosed;
    private String name;
    private Address address;
    private String borough;
    private String cuisine;
    private List<Grades> grades;

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public boolean isIsclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public Restaurant() {
    }

    public Restaurant(String restaurant_id, boolean isclosed, String name, Address address, String borough, String cuisine, List<Grades> grades) {
        this.restaurant_id = restaurant_id;
        this.isclosed = isclosed;
        this.name = name;
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id='" + restaurant_id + '\'' +
                ", isclosed=" + isclosed +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", borough='" + borough + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", grades=" + grades +
                '}';
    }
}
