public class WhatsAppDecorator extends NotifyDecorator {
    public WhatsAppDecorator(Notify notify) {
        super(notify);
    }

    @Override
    public void send() {
        wrappedNotify.send();
        System.out.println("Sending via WhatsApp...");
    }
}