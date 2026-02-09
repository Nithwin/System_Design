import java.time.LocalDateTime;

public class Order {
    int orderId;
    Customer customer;
    Restaurant restaurant;
    DeliveryPartner partner;
    String item;
    double price;
    LocalDateTime date;

    public Order(int orderId, Customer customer, Restaurant restaurant, String item, double price) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.item = item;
        this.price = price;
        this.date = LocalDateTime.now();
    }

    public String toString() {
        return "OrderID: " + orderId +
               ", Customer: " + customer.name +
               ", Restaurant: " + restaurant.name +
               ", Item: " + item +
               ", Price: ₹" + price +
               ", Delivery Partner: " + 
               (partner == null ? "Not Assigned" : partner.name) +
               ", Date: " + date;
    }
}
