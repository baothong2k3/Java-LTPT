/**
 * @ (#) RestaurantHandler.java      2/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.handler;

import fit.onTapTH.entity.Address;
import fit.onTapTH.entity.Grades;
import fit.onTapTH.entity.Restaurant;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

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
 * @date: 2/16/2024
 */
public class StrAPI_RestaurantHandler {
    //Cách 1
    public static Restaurant readFromJson(String fileJson) {
        Restaurant restaurant = null;
        Address address = null;
        List<Grades> listgrades = new ArrayList<>();

        String keyName = "";

        try (
                JsonParser parser = Json.createParser(new FileReader(fileJson))
        ) {

            while (parser.hasNext()) {
                // Khoi tao event
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            //Khoi tao Object
                            JsonObject jsonObject = parser.getObject();

                            // Khoi tao JsonArray cho thuoc tinh array coord
                            JsonArray jsonArray_coord = jsonObject.getJsonArray("coord");
                            List<Double> coords = new ArrayList<>();
                            // Duyet cac phan tu jsonarray
                            jsonArray_coord.forEach(item -> {
                                JsonNumber jsonNumber = (JsonNumber) item; // Chuyen JsonValue sang JsonNumber de chuyen doi sang Double
                                Double coord = jsonNumber.doubleValue();
                                coords.add(coord);
                            });

                            // Set cac thuoc tinh class Address
                            address.setBuilding(jsonObject.getString("building"));
                            address.setCoord(coords);
                            address.setStreet(jsonObject.getString("street"));
                            address.setZipcode(jsonObject.getString("zipcode"));
                        } else {
                            restaurant = new Restaurant();
                        }
                        break;

                    case START_ARRAY:
                        JsonArray jsonArray = parser.getArray();
                        jsonArray.forEach(item -> {
                            // De get cac thuoc tinh ta phai chuyen JsonValue sang JsonObject
                            JsonObject jsonObject = (JsonObject) item;

                            //Setup thuoc tinh LocalDate
                            JsonObject jObj_date = jsonObject.getJsonObject("date");
                            LocalDate date = LocalDate.of(jObj_date.getInt("year"), jObj_date.getInt("month"), jObj_date.getInt("day"));

                            // Set cac thuoc tinh class Grades
                            Grades grades = new Grades();
                            grades.setDate(date);
                            grades.setGrade(jsonObject.getString("grade"));
                            grades.setScore(jsonObject.getInt("score"));

                            listgrades.add(grades);
                        });
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        String stringValue = parser.getString();
                        switch (keyName) {
                            case "restaurant_id":
                                restaurant.setRestaurant_id(stringValue);
                                break;
                            case "borough":
                                restaurant.setBorough(stringValue);
                                break;
                            case "cuisine":
                                restaurant.setCuisine(stringValue);
                                break;
                            case "name":
                                restaurant.setName(stringValue);
                                break;
                        }
                        break;
                    case VALUE_NUMBER:
                        break;
                    case VALUE_FALSE:
                        if (keyName.equals("is_closed")) {
                            restaurant.setIs_closed(false);
                        }
                        break;
                    case END_OBJECT:
                        restaurant.setAddress(address);
                        restaurant.setGrades(listgrades);
                        break;
                    case END_ARRAY:
                        break;
                    default:
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return restaurant;
    }

//Cách 2 -> cách chưa fix ddc Date
//    public static Restaurant readFromJson(String fileName) {
//        Restaurant restaurant = null;
//        Address address = null;
//        Grades grades = null;
//        LocalDate localDate = null;
//        List<Double> coords = null;
//        List<Grades> listgrades = null;
//        String keyName = "";
//        int year = 2023, month = 1, day = 1;
//        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
//            while (parser.hasNext()) {
//                JsonParser.Event event = parser.next();
//                switch (event) {
//
//                    case START_OBJECT:
//                        if (keyName.equals("address")) {
//                            address = new Address();
//                        } else if (keyName.equals("grades") || keyName.equals("score")) {
//                            grades = new Grades();
//                        } else if(keyName.equals("date")) {
//                            System.out.println(year);
//                            System.out.println(month);
//                            System.out.println(day);
//                            localDate = LocalDate.of(year, month, day);
//                        } else{
//                            restaurant = new Restaurant();
//                        }
//                        break;
//                    case START_ARRAY:
//                        if (keyName.equals("grades")) {
//                            listgrades = new ArrayList<>();
//                        } else if (keyName.equals("coord")) {
//                            coords = new ArrayList<>();
//                        }
//                        break;
//                    case KEY_NAME:
//                        keyName = parser.getString();
//                        System.out.println(keyName);
//
//                        break;
//                    case VALUE_STRING:
//                        String stringValue = parser.getString();
//                        switch (keyName) {
//                            case "restaurant_id":
//                                restaurant.setRestaurant_id(stringValue);
//                                break;
//                            case "borough":
//                                restaurant.setBorough(stringValue);
//                                break;
//                            case "cuisine":
//                                restaurant.setCuisine(stringValue);
//                                break;
//                            case "name":
//                                restaurant.setName(stringValue);break;
//                            case "building":
//                                address.setBuilding(stringValue);break;
//                            case "street":
//                                address.setStreet(stringValue);break;
//                            case "zipcode":
//                                address.setZipcode(stringValue);break;
//                            case "grade":
//                                grades.setGrade(stringValue); break;
//                        }
//                        break;
//                    case VALUE_NUMBER:
//                        if(keyName.equals("coord")) {
//                            coords.add(parser.getBigDecimal().doubleValue());
//                        } else if(keyName.equals("score")) {
//                            grades.setScore(parser.getInt());
//                        } else if(keyName.equals("year")) {
//                                year = parser.getInt();
//                        } else if(keyName.equals("month")) {
//                            month = parser.getInt();
//                        } else if(keyName.equals("day")) {
//                            day = parser.getInt();
//                        }
//
//
//                        break;
//
//                    case VALUE_FALSE:
//                        restaurant.setIs_closed(false);
//                        break;
//                    case END_OBJECT:
//                        if(keyName.equals("zipcode")) {
//                            restaurant.setAddress(address);
//                        }  else if(keyName.equals("day")) {
//                            grades.setDate(localDate);
//                        } else if (keyName.equals("score") && restaurant.getGrades()==null) {
//                            listgrades.add(grades);
//                        }
//                        break;
//                    case END_ARRAY:
//                        if(keyName.equals("coord")) {
//                            address.setCoord(coords);
//                        } else if(keyName.equals("score")) {
//                            restaurant.setGrades(listgrades);
//                        }
//                        break;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return restaurant;
//    }

    public static List<Restaurant> findCuisine(String cuisine) {
        Restaurant restaurant = null;
        String keyName = "";
        List<Grades> listGrades = new ArrayList<>();
        Grades grades = new Grades();
        Address address = new Address();
        List<Restaurant> listFindRestaurants = new ArrayList<>();

        try (JsonParser parser = Json.createParser(new FileReader("fileJson/restaurant_full.json"))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            listGrades = new ArrayList<>(); // Khoi tao gia dinh de tranh ghi de du lieu, dam bao ghi tren index moi
                            JsonArray jsonArray = parser.getArray();

                            for (JsonValue item : jsonArray) {
                                grades = new Grades(); // Khoi tao gia dinh de tranh ghi de du lieu, dam bao ghi tren index moi
                                JsonObject jsonObject = (JsonObject) item;
                                
                                JsonObject job_date = jsonObject.getJsonObject("date");
                                LocalDate date = LocalDate.of(job_date.getInt("year"), job_date.getInt("month"), job_date.getInt("day"));

                                grades.setDate(date);
                                grades.setGrade(jsonObject.getString("grade"));
                                grades.setScore(jsonObject.getInt("score"));
                                listGrades.add(grades);
                            }
//                            cach duoi khi khoi tao gia su trong lamda khong duoc
//                            jsonArray.forEach(item -> {
//                                grades = new Grades();
//                                JsonObject jsonObject = (JsonObject) item;
//                                JsonObject job_date = jsonObject.getJsonObject("date");
//                                LocalDate date = LocalDate.of(job_date.getInt("year"), job_date.getInt("month"), job_date.getInt("day"));
//
//                                grades.setDate(date);
//                                grades.setGrade(jsonObject.getString("grade"));
//                                grades.setScore(jsonObject.getInt("score"));
//                                listGrades.add(grades);
//                            });
                        } else {
                            listFindRestaurants = new ArrayList<>();
                        }
                        break;
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            JsonObject jsonObject = parser.getObject();
                            address.setBuilding(jsonObject.getString("building"));
                            // set coord
                            List<Double> coords = new ArrayList<>();
                            JsonArray jsonArray_coord = jsonObject.getJsonArray("coord");
                            jsonArray_coord.forEach(item -> {
                                JsonNumber jsonNumber = (JsonNumber) item;
                                Double coord = jsonNumber.doubleValue();
                                coords.add(coord);
                            });
                            address.setCoord(coords);
                            address.setStreet(jsonObject.getString("street"));
                            address.setZipcode(jsonObject.getString("zipcode"));
                        } else {
                            restaurant = new Restaurant();
                        }
                        break;

                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        String stringValue = parser.getString();
                        switch (keyName) {
                            case "restaurant_id":
                                restaurant.setRestaurant_id(stringValue);
                                break;
                            case "name":
                                restaurant.setName(stringValue);
                                break;
                            case "borough":
                                restaurant.setBorough(stringValue);
                                break;
                            case "cuisine":
                                restaurant.setCuisine(stringValue);
                                break;
                            case "building":
                                address.setBuilding(stringValue);
                                break;
                        }
                        break;
                    case VALUE_NUMBER:
                        break;
                    case VALUE_FALSE:
                        if (keyName.equals("is_closed")) {
                            restaurant.setIs_closed(false);
                        }
                        break;
                    case VALUE_TRUE:
                        if (keyName.equals("is_closed")) {
                            restaurant.setIs_closed(true);
                        }
                        break;
                    case END_OBJECT:
                        restaurant.setAddress(address);
                        restaurant.setGrades(listGrades);
                        if (restaurant.getCuisine().equals(cuisine)) {
                            listFindRestaurants.add(restaurant);
                        }
                        break;
                    case END_ARRAY:
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return listFindRestaurants;
    }

    public static void toFileJson(List<Restaurant> result, String fileName) {

        try (JsonGenerator gen = Json.createGenerator(new FileWriter(fileName))) {
            gen.writeStartArray();
            result.forEach(res -> {
                gen.writeStartObject()
                        .write("restaurant_id", res.getRestaurant_id())
                        .write("is_closed", res.isIs_closed())
                        .write("name", res.getName());
                //Address
                Address address = res.getAddress();
                if (address != null) {

                    gen.writeKey("address")
                            .writeStartObject()
                            .write("building", address.getBuilding());

                    //coord
                    List<Double> coords = address.getCoord();
                    if (coords != null) {
                        gen.writeKey("coord")
                                .writeStartArray();
                        coords.forEach(item -> {
                            gen.write(item);
                        });
                        gen.writeEnd();
                    }
                    gen.write("street", address.getStreet())
                            .write("zipcode", address.getZipcode())
                            .writeEnd();
                }

                gen.write("borough", res.getBorough())
                        .write("cuisine", res.getCuisine());

                //Grades
                List<Grades> grades = res.getGrades();
                if (grades != null) {
                    gen.writeKey("grades")
                            .writeStartArray();
                    grades.forEach(item -> {
                        gen.writeStartObject()
                                .writeKey("date")
                                .writeStartObject()
                                .write("year", item.getDate().getYear())
                                .write("month", item.getDate().getMonth().getValue())
                                .write("day", item.getDate().getDayOfMonth())
                                .writeEnd()
                                .write("grade", item.getGrade())
                                .write("score", item.getScore())
                                .writeEnd();
                    });
                    gen.writeEnd();
                }

                gen.writeEnd();
            });
            gen.writeEnd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
