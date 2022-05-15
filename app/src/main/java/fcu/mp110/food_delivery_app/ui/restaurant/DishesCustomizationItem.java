package fcu.mp110.food_delivery_app.ui.restaurant;

public class DishesCustomizationItem {
    private int imgResId;
    private String customization;
    private String price;

    public DishesCustomizationItem(int imgResId, String customization, String price) {
        this.imgResId = imgResId;
        this.customization = customization;
        this.price = price;
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

}
