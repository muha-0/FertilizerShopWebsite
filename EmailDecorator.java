public class EmailDecorator extends NotifyDecorator {
    public EmailDecorator(Notify notify) {
        super(notify);
    }

    @Override
    public void send() {
        wrappedNotify.send();
        System.out.println("Sending via Email...");
    }
}