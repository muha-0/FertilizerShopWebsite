public abstract class NotifyDecorator implements Notify {
    protected Notify wrappedNotify;

    public NotifyDecorator(Notify notify) {
        this.wrappedNotify = notify;
    }

    public abstract void send();
}