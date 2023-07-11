package com.example.sutfood.model.resturant;

import com.example.sutfood.model.Memory;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.LocalDateTime;

public class Order extends FoodList {
    private DeliveryStatus status;
    public final LocalDateTime time;
    private String restaurantID;

    {
        status = DeliveryStatus.ORDERING;
    }

    public Order(String name, String id) {
        super(name, id);
        this.time = LocalDateTime.now();
    }

    public Order(String name, String id, LocalDateTime time) {
        super(name, id);
        this.time = time;
    }

    public int getTotalPrice() {
        if (size() == 0) return 0;

        int totalPrice = 0;
        for (Food food : this) {
            totalPrice += food.getPrice();
        }
        return totalPrice;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }



    public Restaurant getRestaurant() {
        return Memory.getRestaurant(restaurantID);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurantID = restaurant.getId();
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String toString() {
        String self = "";
        self += this.getId() + "  " + this.getName() + "\n";
        for (Food food : this)
            self += "\t" + food.toString() + "\n";
        self += "\n";
        return self;
    }
}
