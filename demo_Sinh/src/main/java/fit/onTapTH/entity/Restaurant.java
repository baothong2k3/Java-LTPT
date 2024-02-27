/**
 * @ (#) Restaurant.java      2/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.entity;

import java.util.List;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 2/16/2024
 */
public class Restaurant {
    private String restaurant_id;
    private boolean is_closed;
    private String name;
    private Address address;
    private String borough;
    private String cuisine;
    private List<Grades> grades;

    public Restaurant() {

    }

    public Restaurant(String restaurant_id, boolean is_closed, String name,Address address, String borough, String cuisine, List<Grades> grades) {
        this.restaurant_id = restaurant_id;
        this.is_closed = is_closed;
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
        this.grades = grades;
        this.name = name;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id='" + restaurant_id + '\'' +
                ", is_closed=" + is_closed +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", borough='" + borough + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", grades=" + grades +
                '}';
    }
}
