package fcu.mp110.food_delivery_app.ui.store;

import java.util.ArrayList;

public class OrderClass {



    boolean deliveryaccept;
    String restaurant ;
    boolean storeaccept ;
    int totalPrice ;
    String username ;
    ArrayList<UserDetailClass> orderDetail;


    public ArrayList<UserDetailClass> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<UserDetailClass> orderDetail) {
        this.orderDetail = orderDetail;
    }


    public OrderClass(){

    }

    public OrderClass(boolean deliveryaccept, String restaurant, boolean storeaccept, int totalPrice, String username) {
        this.deliveryaccept = deliveryaccept;
        this.restaurant = restaurant;
        this.storeaccept = storeaccept;
        this.totalPrice = totalPrice;
        this.username = username;
    }

    public boolean isDdeliveryaccept() {
        return deliveryaccept;
    }

    public void setDdeliveryaccept(boolean ddeliveryaccept) {
        this.deliveryaccept = ddeliveryaccept;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isStoreaccept() {
        return storeaccept;
    }

    public void setStoreaccept(boolean storeaccept) {
        this.storeaccept = storeaccept;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




}
