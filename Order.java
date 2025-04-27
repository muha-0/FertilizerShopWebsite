import java.util.List;

public abstract class Order {
    protected int orderId;
    protected int customerId;
    protected String address;
    protected List<Item> items;
    protected double basePrice;

    public Order(int orderId, int customerId, String address, List<Item> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.address = address;
        this.items = items;
        this.basePrice = calculateBasePrice();
    }

    protected double calculateBasePrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public abstract double getFinalPrice();
}