public class QuantityDiscountDecorator extends DiscountDecorator {
    public QuantityDiscountDecorator(Order order) {
        super(order);
    }

    @Override
    public double getFinalPrice() {
        if (items.size() >= DiscountRates.quantityThreshold) {
            return wrappedOrder.getFinalPrice() - DiscountRates.quantityDiscount;
        } else {
            return wrappedOrder.getFinalPrice();
        }
    }
}