public class DeliveryPartner {
    int partnerId;
    String name;
    boolean available = true;

    public DeliveryPartner(int partnerId, String name) {
        this.partnerId = partnerId;
        this.name = name;
    }
}
