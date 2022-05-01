package fcu.mp110.food_delivery_app.ui.restaurant;

public class StoreItem {
    private int storeImgResId;
    private String star;
    private String no1;
    private String storeName;
    private String storeLabel;

    public StoreItem(int storeImgResId, String star, String no1, String storeName, String storeLabel) {
        this.storeImgResId = storeImgResId;
        this.star = star;
        this.no1 = no1;
        this.storeName = storeName;
        this.storeLabel = storeLabel;
    }

    public int getStoreImgResId() {
        return storeImgResId;
    }

    public String getStar() {
        return star;
    }

    public String getNo1() {
        return no1;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreLabel() {
        return storeLabel;
    }

    public void setStoreImgResId(int storeImgResId) {
        this.storeImgResId = storeImgResId;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public void setNo1(String no1) {
        this.no1 = no1;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreLabel(String storeLabel) {
        this.storeLabel = storeLabel;
    }
}
