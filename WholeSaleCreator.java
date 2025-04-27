import java.util.List;

public class WholeSaleCreator extends OrderCreator {
    public Order createOrder(int orderId, int customerId, String address, List<Item> items) {
        return new WholeSaleOrder(orderId, customerId, address, items);
    }
}