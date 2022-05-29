package fcu.mp110.food_delivery_app.ui.cart;

import java.util.List;

public interface IDrinkLoadListener {
    void onDrinkLoadSuccess(List<CartItem> cartItemList);
    void onDrinkLoadFailed(String message);
}
