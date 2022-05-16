package fcu.mp110.food_delivery_app.ui.restaurant;

public class CategoriesItem {
    private int categoriesImgResId;
    private String categories;

    public CategoriesItem(int categoriesImgResId, String categories) {
        this.categoriesImgResId = categoriesImgResId;
        this.categories = categories;
    }


    public int getCategoriesImgResId() {
        return categoriesImgResId;
    }

    public String getCategories() {
        return categories;
    }
}
