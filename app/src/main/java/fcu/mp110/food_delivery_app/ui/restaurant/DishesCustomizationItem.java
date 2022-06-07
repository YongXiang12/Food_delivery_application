package fcu.mp110.food_delivery_app.ui.restaurant;

public class DishesCustomizationItem{
    private int imgResId;
    private String customizationName;
    private String price;
    private boolean checked;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DishesCustomizationItem() {
    }

    public DishesCustomizationItem(int imgResId, String customizationName, String price) {
        this.imgResId = imgResId;
        this.customizationName = customizationName;
        this.price = price;
        this.checked = false;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
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
