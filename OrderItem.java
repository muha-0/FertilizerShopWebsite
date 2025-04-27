import lombok.Data;

@Data
public class OrderItem {
    private final String itemId;
    private final String orderId;
    private final int quantity;
    private final OrderItemType type;
}
