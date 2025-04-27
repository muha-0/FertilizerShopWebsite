import java.util.HashMap;
import java.util.Map;

public class OrderItemFactory {
    private static final Map<String, OrderItemType> orderItemTypes = new HashMap<>();

    public static OrderItemType getOrderItemType(String productId, String size) {
        String key = productId + "-" + size;
        OrderItemType type = orderItemTypes.get(key);
        if (type == null) {
            type = new OrderItemType(productId, size);
            orderItemTypes.put(key, type);
        }
        return type;
    }
}
