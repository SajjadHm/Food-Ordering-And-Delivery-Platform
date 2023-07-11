package model;

import model.resturant.Order;
import model.resturant.Restaurant;

public class Cart {


    private String restaurantID;
    private  Order order;

    public Cart(Restaurant restaurant, Order order)
    {
        this.restaurantID = restaurant.getId();
        this.order = order;
    }

    public Restaurant getRestaurant() {
        return Memory.getRestaurant(restaurantID);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurantID = restaurant.getId();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
