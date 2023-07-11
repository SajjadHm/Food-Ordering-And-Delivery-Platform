package model;

import model.resturant.Order;
import model.resturant.Restaurant;

public class Cart {


    private Restaurant restaurant;
    private  Order order;

    public Cart(Restaurant restaurant, Order order)
    {
        this.restaurant = restaurant;
        this.order = order;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
