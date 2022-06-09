package fcu.mp110.food_delivery_app.ui.review;

public class ReviewItem {

    private String imgResId;
    private String userName;
    private String date;
    private String review;
    public ReviewItem() {};
//    public ReviewItem(String s){}

    public ReviewItem(String imgResId, String userName, String date, String review) {
        this.imgResId = imgResId;
        this.userName = userName;
        this.date = date;
        this.review = review;
    }

    public String getImgResId() {
        return imgResId;
    }

    public void setImgResId(String imgResId) {
        this.imgResId = imgResId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
