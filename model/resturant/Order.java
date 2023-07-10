package model.resturant;

import java.time.LocalDateTime;

public class Order extends FoodList {
    private DeliveryStatus status;
    public final LocalDateTime time;
    private Resturant restaurant;

    {
        status = DeliveryStatus.ORDERING;
    }

    public Order(String name, String id) {
        super(name, id);
        this.time = LocalDateTime.now();
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



    public Resturant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Resturant restaurant) {
        this.restaurant = restaurant;
    }
}
