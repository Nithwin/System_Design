import java.util.*;
class Customer {
    int accno;
    String name;
    int pin;
    double balance;
    List<Transaction> transactions;

    public Customer(int accno,String name,int pin,double balance){
        this.accno=accno;
        this.name=name;
        this.pin=pin;
        this.balance=balance;
        transactions=new ArrayList<>();
    }

    boolean authenticate(int enteredpin){
       return pin==enteredpin;
    }
    void addTransaction(Transaction t){
        transactions.add(t);
    }
    void viewTransactions(){
        if(transactions.isEmpty()){
            System.out.println("No Transactions found");
            return;
        }
        for(Transaction t:transactions){
            System.out.println(t);
        }
    }
}
