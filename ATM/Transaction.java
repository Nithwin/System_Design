 import java.time.LocalDateTime;
 class Transaction {
    int fromacc;
    int toacc;
    String type;
    double amount;
    LocalDateTime date;
  
    public Transaction(int fromacc,int toacc,String type,double amount){
        this.fromacc=fromacc;
        this.toacc=toacc;
        this.type=type;
        this.amount=amount;
        this.date=LocalDateTime.now();
    }
    public String toString(){
         return type+" | From: "+fromacc+" | To: "+toacc+" | Amount: "+amount+" | Date: "+date;
    }

    
}
