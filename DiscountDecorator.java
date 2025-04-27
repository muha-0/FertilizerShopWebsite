public abstract class DiscountDecorator extends Order {
    protected Order wrappedOrder;

    public DiscountDecorator(Order order) {
        super(order.orderId, order.customerId, order.address, order.items);
        this.wrappedOrder = order;
    }

    public abstract double getFinalPrice();
}