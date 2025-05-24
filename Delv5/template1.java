// Abstract class: Template
abstract class OrderCreator {

    // Template method
    public final void processOrder() {
        validateOrder();
        applyDiscount();
        sendConfirmation();
        createOrder();
    }

    // Common steps that can be overridden
    protected void validateOrder() {
        System.out.println("Validating order...");
    }

    protected void applyDiscount() {
        System.out.println("Applying default discount...");
    }

    protected void sendConfirmation() {
        System.out.println("Sending confirmation email...");
    }

    // Abstract method to be implemented by subclasses
    protected abstract Order createOrder();
}

// Concrete implementation for online orders
class OnlineOrderCreator extends OrderCreator {

    @Override
    protected void validateOrder() {
        System.out.println("Validating online order (user login, payment)...");
    }

    @Override
    protected void applyDiscount() {
        System.out.println("Applying online discount rules...");
    }

    @Override
    protected void sendConfirmation() {
        System.out.println("Sending email and SMS for online order...");
    }

    @Override
    protected Order createOrder() {
        System.out.println("Creating online order object...");
        return new Order("Online");
    }
}

// Concrete implementation for in-store orders
class InStoreOrderCreator extends OrderCreator {

    @Override
    protected void validateOrder() {
        System.out.println("Validating in-store order (stock, cashier ID)...");
    }

    @Override
    protected void applyDiscount() {
        System.out.println("Applying in-store discount coupons...");
    }

    @Override
    protected void sendConfirmation() {
        System.out.println("Printing in-store receipt...");
    }

    @Override
    protected Order createOrder() {
        System.out.println("Creating in-store order object...");
        return new Order("InStore");
    }
}

// Sample Order class
class Order {
    private String type;

    public Order(String type) {
        this.type = type;
        System.out.println("Order of type '" + type + "' created.");
    }
}
