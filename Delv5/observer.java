import java.util.*;

public interface AlertListener {
    void update();
}

public class EmailAlertListener implements AlertListener {
    private final String email;

    public EmailAlertListener(String email) {
        this.email = email;
    }

    @Override
    public void update() {
        System.out.println("Sending low stock alert to: " + email);
    }
}

public class StockAlert {
    private final List<AlertListener> subscribers = new ArrayList<>();

    public boolean subscribe(AlertListener listener) {
        System.out.println("Subscribed: " + listener);
        return subscribers.add(listener);
    }

    public boolean unsubscribe(AlertListener listener) {
        System.out.println("Unsubscribed: " + listener);
        return subscribers.remove(listener);
    }

    public void alert() {
        System.out.println("📉 Stock is low! Alerting all subscribers...");
        for (AlertListener listener : subscribers) {
            listener.update();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StockAlert stockAlert = new StockAlert();

        AlertListener supplier1 = new EmailAlertListener("supplier1@example.com");
        AlertListener supplier2 = new EmailAlertListener("supplier2@example.com");

        stockAlert.subscribe(supplier1);
        stockAlert.subscribe(supplier2);

        stockAlert.alert(); // Triggers alerts to all subscribed listeners

        stockAlert.unsubscribe(supplier1);

        System.out.println("\nOne supplier unsubscribed. Triggering again...\n");
        stockAlert.alert();
    }
}
