/**
 * @ (#) ObjJson_RestaurantModel.java      2/17/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.model;

import fit.onTapTH.entity.Restaurant;
import fit.onTapTH.handler.ObjJson_RestaurantHandler;

import java.util.List;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 2/17/2024
 */
public class ObjJson_RestaurantModel {
    public static void main(String[] args) {
//        Restaurant restaurant = ObjJson_RestaurantHandler.readJsonRes("fileJson/restaurant.json");
//        System.out.println(restaurant);
        List<Restaurant> list = ObjJson_RestaurantHandler.findJsonRes("Japanese","fileJson/restaurant_full.json");
        list.forEach(System.out::println);

        ObjJson_RestaurantHandler.writeJson(list, "fileJson/ptssss.json");
    }
}
