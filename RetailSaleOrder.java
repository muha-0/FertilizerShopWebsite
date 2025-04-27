class RetailSaleOrder extends Order {
    public RetailSaleOrder(int orderId, int customerId, String address, java.util.List<Item> items) {
        super(orderId, customerId, address, items);
    }

    @Override
    public double getFinalPrice() {
        return basePrice;
    }
}