/**
 * @ (#) ObjJson_RestaurantHandler.java      2/17/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.handler;

import fit.onTapTH.entity.Address;
import fit.onTapTH.entity.Grades;
import fit.onTapTH.entity.Restaurant;
import jakarta.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 2/17/2024
 */
public class ObjJson_RestaurantHandler {
    public static Restaurant readJsonRes(String fileName) {
        Restaurant restaurant = new Restaurant();
        Address address = new Address();
        try (
                JsonReader reader = Json.createReader(new FileReader(fileName));
        ) {
            JsonObject jObj = reader.readObject();
            restaurant.setRestaurant_id(jObj.getString("restaurant_id"));
            restaurant.setIs_closed(jObj.getBoolean("is_closed"));
            restaurant.setName(jObj.getString("name"));

            // set address start
            JsonObject jobj_address = jObj.getJsonObject("address");
            address.setBuilding(jobj_address.getString("building"));
            //coord
            JsonArray jArray_coords = jobj_address.getJsonArray("coord");
            List<Double> coords = new ArrayList<>();
            jArray_coords.forEach(item -> {
                JsonNumber jsonNumber = (JsonNumber) item;
                coords.add(jsonNumber.doubleValue());
            });
            address.setCoord(coords);
            address.setStreet(jobj_address.getString("street"));
            address.setZipcode(jobj_address.getString("zipcode"));

            restaurant.setAddress(address);
            // set address end

            restaurant.setBorough(jObj.getString("borough"));
            restaurant.setCuisine(jObj.getString("cuisine"));

            //set Grade start
            JsonArray jobArray = jObj.getJsonArray("grades");
            List<Grades> grades = new ArrayList<>();
            jobArray.forEach(item -> {
                JsonObject job_grade = (JsonObject) item;
                JsonObject job_grade_date = job_grade.getJsonObject("date");
                LocalDate date = LocalDate.of(job_grade_date.getInt("year"), job_grade_date.getInt("month"), job_grade_date.getInt("day"));

                grades.add(new Grades(date, job_grade.getString("grade"), job_grade.getInt("score")));
            });

            restaurant.setGrades(grades);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return restaurant;
    }

    public static List<Restaurant> findJsonRes(String cuisine, String fileName) {
        Restaurant restaurant = new Restaurant();
        Address address = new Address();
        List<Restaurant> list = new ArrayList<Restaurant>();
        try (
                JsonReader reader = Json.createReader(new FileReader(fileName));
        ) {
            JsonArray jsonArray = reader.readArray();
            for (JsonValue ja : jsonArray) {
                JsonObject jObj = (JsonObject) ja;
                if (jObj.getString("cuisine").equals(cuisine)) {
                    restaurant = new Restaurant();
                    restaurant.setRestaurant_id(jObj.getString("restaurant_id"));
                    restaurant.setIs_closed(jObj.getBoolean("is_closed"));
                    restaurant.setName(jObj.getString("name"));

                    // set address start
                    address = new Address();
                    JsonObject jobj_address = jObj.getJsonObject("address");
                    address.setBuilding(jobj_address.getString("building"));
                    //coord
                    JsonArray jArray_coords = jobj_address.getJsonArray("coord");
                    List<Double> coords = new ArrayList<>();
                    jArray_coords.forEach(item -> {
                        JsonNumber jsonNumber = (JsonNumber) item;
                        coords.add(jsonNumber.doubleValue());
                    });
                    address.setCoord(coords);
                    address.setStreet(jobj_address.getString("street"));
                    address.setZipcode(jobj_address.getString("zipcode"));

                    restaurant.setAddress(address);
                    // set address end

                    restaurant.setBorough(jObj.getString("borough"));
                    restaurant.setCuisine(jObj.getString("cuisine"));

                    //set Grade start
                    JsonArray jobArray = jObj.getJsonArray("grades");
                    List<Grades> grades = new ArrayList<>();

                    jobArray.forEach(item -> {
                        JsonObject job_grade = (JsonObject) item;
                        JsonObject job_grade_date = job_grade.getJsonObject("date");
                        LocalDate date = LocalDate.of(job_grade_date.getInt("year"), job_grade_date.getInt("month"), job_grade_date.getInt("day"));

                        grades.add(new Grades(date, job_grade.getString("grade"), job_grade.getInt("score")));
                    });

                    restaurant.setGrades(grades);
                    // set Grade end

                    // theem vao list
                    list.add(restaurant);
                }


            }
            ;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void writeJson(List<Restaurant> result, String fileName) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
            JsonArrayBuilder aBuilder = Json.createArrayBuilder();
            JsonObjectBuilder bBuilder = Json.createObjectBuilder();

            result.forEach(res -> {
                //Address
                Address address = res.getAddress();
                    //Coord
                    List<Double> coords = address.getCoord();
                    JsonArrayBuilder aBuilder_coord = Json.createArrayBuilder();
                    coords.forEach(aBuilder_coord::add); // Add values vao trong mang coords
                    JsonArray jsa_coord = aBuilder_coord.build();

                JsonObject jsonAdress = bBuilder.add("building", address.getBuilding())
                        .add("coord", jsa_coord)
                        .add("street", address.getStreet())
                        .add("zipcode", address.getZipcode())
                        .build();

                // Grades
                List<Grades> grades = res.getGrades();
                JsonArrayBuilder aBuild_grades = Json.createArrayBuilder();

                grades.forEach(grade -> {
                    //Date Object
                    JsonObject jobj_date = bBuilder.add("year", grade.getDate().getYear())
                            .add("month", grade.getDate().getMonth().getValue())
                            .add("day", grade.getDate().getDayOfMonth())
                            .build();

                    //Grade Object
                    JsonObject jobj_grades = bBuilder.add("date", jobj_date)
                            .add("grade", grade.getGrade())
                            .add("score", grade.getScore())
                            .build();

                    aBuild_grades.add(jobj_grades);
                });

                JsonArray jsa_grades = aBuild_grades.build();



                //Restaurant Object
                JsonObject jsonObject = bBuilder.add("restaurant_id", res.getRestaurant_id())
                        .add("is_closed", res.isIs_closed())
                        .add("name", res.getName())
                        .add("address", jsonAdress)
                        .add("borough", res.getBorough())
                        .add("cuisine", res.getCuisine())
                        .add("grades", jsa_grades)
                        .build();

                aBuilder.add(jsonObject);
            });

            JsonArray jsonArray = aBuilder.build();
            writer.writeArray(jsonArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
