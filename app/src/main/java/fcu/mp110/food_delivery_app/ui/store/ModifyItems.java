package fcu.mp110.food_delivery_app.ui.store;

public class ModifyItems {
    int img ;
    String name ;
    String price ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count ;

    public  ModifyItems(String n , int img , String price , int count ){
        this.name = n ;
        this.img = img ;
        this.price = price;
        this.count = count ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }







}
