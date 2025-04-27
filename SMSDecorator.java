public class SMSDecorator extends NotifyDecorator {
    public SMSDecorator(Notify notify) {
        super(notify);
    }

    @Override
    public void send() {
        wrappedNotify.send();
        System.out.println("Sending via SMS...");
    }
}