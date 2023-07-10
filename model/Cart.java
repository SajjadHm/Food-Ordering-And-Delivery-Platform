package model;

import model.resturant.Order;
import model.resturant.Resturant;

public class Cart {


    private  Resturant restaurant;
    private  Order order;

    public Cart(Resturant restaurant, Order order)
    {
        this.restaurant = restaurant;
        this.order = order;
    }

    public Resturant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Resturant resturant) {
        this.restaurant = resturant;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
