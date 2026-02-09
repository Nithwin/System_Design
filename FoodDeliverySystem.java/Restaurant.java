import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    int restaurantId;
    String name;
    List<Order> receivedOrders = new ArrayList<>();

    public Restaurant(int restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }
}
