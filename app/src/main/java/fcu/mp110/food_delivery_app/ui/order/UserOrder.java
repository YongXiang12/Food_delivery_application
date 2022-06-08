package fcu.mp110.food_delivery_app.ui.order;

import java.util.List;

public class UserOrder {
    public String username;
    public String restaurant;
    public int totalPrice;
    public Boolean accept;
    public List<Object> orderDetail;

    public UserOrder() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserOrder(String username, String restaurant, int totalPrice, boolean accept, List<Object> detailUpdateDate) {
        this.username = username;
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
        this.accept = accept;
        this.orderDetail = detailUpdateDate;
    }
}
