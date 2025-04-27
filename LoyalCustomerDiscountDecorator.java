public class LoyalCustomerDiscountDecorator extends DiscountDecorator {
    public LoyalCustomerDiscountDecorator(Order order) {
        super(order);
    }

    @Override
    public double getFinalPrice() {
        return wrappedOrder.getFinalPrice() * (1 - DiscountRates.loyalCustomerRate);
    }
}