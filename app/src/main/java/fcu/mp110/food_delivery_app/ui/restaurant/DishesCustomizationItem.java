package fcu.mp110.food_delivery_app.ui.restaurant;

import android.os.Parcel;

public class DishesCustomizationItem{
    private int imgResId;
    private String customization;
    private String price;
    private boolean checked;

    public DishesCustomizationItem(int imgResId, String customization, String price) {
        this.imgResId = imgResId;
        this.customization = customization;
        this.price = price;
        this.checked = false;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
