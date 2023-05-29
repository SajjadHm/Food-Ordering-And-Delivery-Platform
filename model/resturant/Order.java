package model.resturant;

public class Order extends FoodList {
    private DeliveryStatus status;

    {
        status = DeliveryStatus.ORDERING;
    }

    public Order(String name, String id) {
        super(name, id);
    }

    public int getTotalPrice() {
        if (size() == 0) return 0;

        int totalPrice = 0;
        for (Food food : this) {
            totalPrice += food.getPrice();
        }
        return totalPrice;
    }

    public String getTime() {
        // filling later ! //
        return null;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
