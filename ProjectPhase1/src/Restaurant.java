import java.util.ArrayList;

public class Restaurant
{
    String name;
    int id;
    String location;
    String foodType;
    ArrayList<Food> menu = new ArrayList<>();
    ArrayList<Orders> openOrders = new ArrayList<>();
    ArrayList<Orders> completedOrders = new ArrayList<>();


}
