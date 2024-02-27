/**
 * @ (#) RestaurantModel.java      2/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.model;

import fit.onTapTH.entity.Restaurant;
import fit.onTapTH.handler.StrAPI_RestaurantHandler;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 2/16/2024
 */
public class StrAPI_RestaurantModel {
    public static void main(String[] args) {
        Restaurant restaurant = StrAPI_RestaurantHandler.readFromJson("fileJson/restaurant.json");
        System.out.println(restaurant);

//        List<Restaurant> findRestaurant = StrAPI_RestaurantHandler.findCuisine("Japanese");
//        findRestaurant.forEach(System.out::println);
//        StrAPI_RestaurantHandler.toFileJson(findRestaurant, "fileJson/pts.json");
//        System.out.println("Done!!!");
    }
}
