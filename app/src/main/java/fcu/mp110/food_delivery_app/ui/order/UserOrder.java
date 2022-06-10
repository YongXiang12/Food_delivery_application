package fcu.mp110.food_delivery_app.ui.order;

import java.io.Serializable;
import java.util.List;

public class UserOrder implements Serializable {
    public String username;
    public String restaurant;
    public int totalPrice;
    public Boolean storeAccept;
    public Boolean delivererAccept;
    public List<Object> orderDetail;

    public UserOrder() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserOrder(String username, String restaurant, int totalPrice, Boolean storeAccept, Boolean delivererAccept, List<Object> orderDetail) {
        this.username = username;
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
        this.storeAccept = storeAccept;
        this.delivererAccept = delivererAccept;
        this.orderDetail = orderDetail;

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getStoreAccept() {
        return storeAccept;
    }

    public void setStoreAccept(Boolean storeAccept) {
        this.storeAccept = storeAccept;
    }

    public Boolean getDelivererAccept() {
        return delivererAccept;
    }

    public void setDelivererAccept(Boolean delivererAccept) {
        this.delivererAccept = delivererAccept;
    }

    public List<Object> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<Object> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
