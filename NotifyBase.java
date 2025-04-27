public class NotifyBase implements Notify {
    private MessageStrategy strategy;

    public NotifyBase(MessageStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void send() {
        System.out.println("Base content: " + strategy.generateMessage());
    }
}