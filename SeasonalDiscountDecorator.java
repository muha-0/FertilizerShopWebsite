public class SeasonalDiscountDecorator extends DiscountDecorator {
    public SeasonalDiscountDecorator(Order order) {
        super(order);
    }

    @Override
    public double getFinalPrice() {
        return wrappedOrder.getFinalPrice() - DiscountRates.seasonalFixedDiscount;
    }
}