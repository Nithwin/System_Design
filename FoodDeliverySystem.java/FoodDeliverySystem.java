import java.util.*;

public class FoodDeliverySystem {

    static Scanner sc = new Scanner(System.in);

    static List<Customer> customers = new ArrayList<>();
    static List<Restaurant> restaurants = new ArrayList<>();
    static List<DeliveryPartner> partners = new ArrayList<>();
    static List<Order> allOrders = new ArrayList<>();

    static int orderCounter = 1;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== FOOD DELIVERY SYSTEM ===");
            System.out.println("1. Customer Register");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> registerCustomer();
                case 2 -> customerLogin();
                case 3 -> adminMenu();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void registerCustomer() {
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();

        customers.add(new Customer(id, name, pass));
        System.out.println("Customer registered successfully!");
    }

    static void customerLogin() {
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
        System.out.print("Password: ");
        String pass = sc.next();

        for (Customer c : customers) {
            if (c.login(id, pass)) {
                customerMenu(c);
                return;
            }
        }
        System.out.println("Login Failed!");
    }

    static void customerMenu(Customer c) {
        while (true) {
            System.out.println("\n1. Place Order");
            System.out.println("2. View Order History");
            System.out.println("3. Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> placeOrder(c);
                case 2 -> c.orderHistory.forEach(System.out::println);
                case 3 -> { return; }
            }
        }
    }

    static void placeOrder(Customer c) {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println("Available Restaurants:");
        for (Restaurant r : restaurants)
            System.out.println(r.restaurantId + " - " + r.name);

        System.out.print("Restaurant ID: ");
        int rid = sc.nextInt();

        Restaurant res = null;
        for (Restaurant r : restaurants)
            if (r.restaurantId == rid) res = r;

        if (res == null) return;

        System.out.print("Food Item: ");
        String item = sc.next();
        System.out.print("Price: ");
        double price = sc.nextDouble();

        Order o = new Order(orderCounter++, c, res, item, price);

        assignDeliveryPartner(o);

        c.orderHistory.add(o);
        res.receivedOrders.add(o);
        allOrders.add(o);

        System.out.println("Order placed successfully!");
    }

    static void assignDeliveryPartner(Order o) {
        for (DeliveryPartner p : partners) {
            if (p.available) {
                o.partner = p;
                p.available = false;
                return;
            }
        }
        System.out.println("No delivery partner available currently.");
    }

    
    static void adminMenu() {
        System.out.print("Admin Password: ");
        String pass = sc.next();

        if (!pass.equals("admin123")) {
            System.out.println("Invalid Admin Password");
            return;
        }

        while (true) {
            System.out.println("\n1. Add Restaurant");
            System.out.println("2. Add Delivery Partner");
            System.out.println("3. View All Orders");
            System.out.println("4. Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addRestaurant();
                case 2 -> addDeliveryPartner();
                case 3 -> allOrders.forEach(System.out::println);
                case 4 -> { return; }
            }
        }
    }

    static void addRestaurant() {
        System.out.print("Restaurant ID: ");
        int id = sc.nextInt();
        System.out.print("Name: ");
        String name = sc.next();

        restaurants.add(new Restaurant(id, name));
        System.out.println("Restaurant added!");
    }

    static void addDeliveryPartner() {
        System.out.print("Partner ID: ");
        int id = sc.nextInt();
        System.out.print("Name: ");
        String name = sc.next();

        partners.add(new DeliveryPartner(id, name));
        System.out.println("Delivery partner added!");
    }
}
