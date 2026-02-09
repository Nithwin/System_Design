import java.util.*;

public class ATMSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();
    static List<Transaction> allTransactions = new ArrayList<>();
    static Admin admin = new Admin("admin", "1234");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== ATM SYSTEM =====");
            System.out.println("1. Customer Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> customerLogin();
                case 2 -> adminLogin();
                case 3 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void customerLogin() {
        System.out.print("Enter Account Number: ");
        int acc = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        Customer c = findCustomer(acc);

        if (c != null && c.authenticate(pin)) {
            customerMenu(c);
        } else {
            System.out.println("Authentication Failed!");
        }
    }

    static void customerMenu(Customer c) {
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Transfer Money");
            System.out.println("3. View Transaction History");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> System.out.println("Balance: ₹" + c.balance);
                case 2 -> transferMoney(c);
                case 3 -> c.viewTransactions();
                case 4 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void transferMoney(Customer sender) {
        System.out.print("Enter Receiver Account No: ");
        int toAcc = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        Customer receiver = findCustomer(toAcc);

        if (receiver == null || amount <= 0 || sender.balance < amount) {
            System.out.println("Transfer Failed!");
            return;
        }

        sender.balance -= amount;
        receiver.balance += amount;

        Transaction t = new Transaction(sender.accno, toAcc,  "Transfer",amount);
        sender.addTransaction(t);
        receiver.addTransaction(t);
        allTransactions.add(t);

        System.out.println("Transfer Successful!");
    }

    static void adminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = sc.next();
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();

        if (admin.login(id, pass)) {
            adminMenu();
        } else {
            System.out.println("Admin Login Failed!");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. View All Transactions");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addCustomer();
                case 2 -> viewCustomers();
                case 3 -> allTransactions.forEach(System.out::println);
                case 4 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    static void addCustomer() {
        System.out.print("Account Number: ");
        int acc = sc.nextInt();
        System.out.print("Customer Name: ");
        String name = sc.next();
        System.out.print("PIN: ");
        int pin = sc.nextInt();
        System.out.print("Initial Balance: ");
        double bal = sc.nextDouble();

        customers.add(new Customer(acc, name, pin, bal));
        System.out.println("Customer Added Successfully!");
    }

    static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c.accno + " | " + c.name + " | ₹" + c.balance);
        }
    }

    static Customer findCustomer(int acc) {
        for (Customer c : customers) {
            if (c.accno == acc)
                return c;
        }
        return null;
    }
}
