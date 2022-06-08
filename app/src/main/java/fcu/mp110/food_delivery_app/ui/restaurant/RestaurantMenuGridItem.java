package fcu.mp110.food_delivery_app.ui.restaurant;

import android.widget.TextView;

public class RestaurantMenuGridItem {

    private String menuImgURI;
    private String key;
    private String restaurantKey;
    private String price;
    private String mark;
    private String dishName;
    private String ingredient;

    public RestaurantMenuGridItem() {

    }

    public String getRestaurantKey() {
        return restaurantKey;
    }

    public void setRestaurantKey(String restaurantKey) {
        this.restaurantKey = restaurantKey;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMenuImgURI() {
        return menuImgURI;
    }

    public String getKey() {
        return key;
    }

    public String getPrice() {
        return price;
    }

    public String getDishName() {
        return dishName;
    }
    //    public RestaurantMenuGridItem(int imgResId, String tvPrice, String tvMark, String tvDishName,
//                                  String tvIngredient) {
//        this.imgResId = imgResId;
//        this.tvPrice = tvPrice;
//        this.tvMark = tvMark;
//        this.tvDishName = tvDishName;
//        this.tvIngredient = tvIngredient;
//    }


//    public int getImgResId() {
//        return imgResId;
//    }
//
//    public void setImgResId(int imgResId) {
//        this.imgResId = imgResId;
//    }
//
//    public String getTvPrice() {
//        return tvPrice;
//    }
//
//    public void setTvPrice(String tvPrice) {
//        this.tvPrice = tvPrice;
//    }
//
//    public String getTvMark() {
//        return tvMark;
//    }
//
//    public void setTvMark(String tvMark) {
//        this.tvMark = tvMark;
//    }
//
//    public String getTvDishName() {
//        return tvDishName;
//    }
//
//    public void setTvDishName(String tvDishName) {
//        this.tvDishName = tvDishName;
//    }
//
//    public String getTvIngredient() {
//        return tvIngredient;
//    }
//
//    public void setTvIngredient(String tvIngredient) {
//        this.tvIngredient = tvIngredient;
//    }
//

}
