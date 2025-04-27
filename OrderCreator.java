import java.util.List;

public abstract class OrderCreator {
    public abstract Order createOrder(int orderId, int customerId, String address, List<Item> items);
}