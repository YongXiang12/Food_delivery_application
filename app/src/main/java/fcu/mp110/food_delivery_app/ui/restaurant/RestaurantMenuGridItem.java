package fcu.mp110.food_delivery_app.ui.restaurant;

import android.widget.TextView;

public class RestaurantMenuGridItem {

    private int imgResId;
    private String tvPrice;
    private String tvMark;
    private String tvDishName;
    private String tvIngredient;

    public RestaurantMenuGridItem(int imgResId, String tvPrice, String tvMark, String tvDishName,
                                  String tvIngredient) {
        this.imgResId = imgResId;
        this.tvPrice = tvPrice;
        this.tvMark = tvMark;
        this.tvDishName = tvDishName;
        this.tvIngredient = tvIngredient;
    }


    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(String tvPrice) {
        this.tvPrice = tvPrice;
    }

    public String getTvMark() {
        return tvMark;
    }

    public void setTvMark(String tvMark) {
        this.tvMark = tvMark;
    }

    public String getTvDishName() {
        return tvDishName;
    }

    public void setTvDishName(String tvDishName) {
        this.tvDishName = tvDishName;
    }

    public String getTvIngredient() {
        return tvIngredient;
    }

    public void setTvIngredient(String tvIngredient) {
        this.tvIngredient = tvIngredient;
    }


}
