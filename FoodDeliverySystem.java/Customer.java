import java.util.ArrayList;
import java.util.List;

public class Customer {
    int customerId;
    String name;
    String password;
    List<Order> orderHistory = new ArrayList<>();

    public Customer(int customerId, String name, String password) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
    }

    public boolean login(int id, String pass) {
        return this.customerId == id && this.password.equals(pass);
    }
}
