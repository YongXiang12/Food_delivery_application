package fcu.mp110.food_delivery_app.ui.restaurant;


public class RestaurantItem {
    private String key;
    private String restaurantImgURI;
    private String restaurantName;
    private String restaurantLabel;
    private String restaurantScore;
    private String restaurantCommentNum;
    private String deliveryTime;
    public RestaurantItem() {

    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getRestaurantCommentNum() {
        return restaurantCommentNum;
    }

    public String getRestaurantScore() {
        return restaurantScore;
    }

    public void setRestaurantScore(String restaurantScore) {
        this.restaurantScore = restaurantScore;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRestaurantImgURI() {
        return restaurantImgURI;
    }

    public void setRestaurantImgURI(String restaurantImgURI) {
        this.restaurantImgURI = restaurantImgURI;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLabel() {
        return restaurantLabel;
    }

    public void setRestaurantLabel(String restaurantLabel) {
        this.restaurantLabel = restaurantLabel;
    }

    public void setStoreLabel(String storeLabel) {
        this.restaurantLabel = storeLabel;
    }
//        public StoreItem(String storeName, String storeLabel) {
//        this.storeName = storeName;
//        this.storeLabel = storeLabel;
//    }
//    public StoreItem(int storeImgResId, String star, String no1, String storeName, String storeLabel) {
//        this.storeImgResId = storeImgResId;
//        this.star = star;
//        this.no1 = no1;
//        this.storeName = storeName;
//        this.storeLabel = storeLabel;
//    }



}
