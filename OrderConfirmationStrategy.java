public class OrderConfirmationStrategy implements MessageStrategy {
    @Override
    public String generateMessage() {
        return "Your order has been placed successfully.";
    }
}