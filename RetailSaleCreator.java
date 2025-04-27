import java.util.List;

public class RetailSaleCreator extends OrderCreator {
    public Order createOrder(int orderId, int customerId, String address, List<Item> items) {
        return new RetailSaleOrder(orderId, customerId, address, items);
    }
}