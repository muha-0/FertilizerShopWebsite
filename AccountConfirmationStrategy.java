public class AccountConfirmationStrategy implements MessageStrategy {
    @Override
    public String generateMessage() {
        return "Your account has been successfully confirmed.";
    }
}