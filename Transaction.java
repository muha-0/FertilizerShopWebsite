// Abstract Factory Class
public abstract class TransactionCreator {

    // Factory Methods
    public abstract VisaTransaction createVisaTransaction();
    public abstract CashTransaction createCashTransaction();
}

// Concrete Factory Classes
public class ConfirmedTransactionCreator extends TransactionCreator {

    @Override
    public VisaTransaction createVisaTransaction() {
        return new ConfirmedVisaTransaction();
    }

    @Override
    public CashTransaction createCashTransaction() {
        return new ConfirmedCashTransaction();
    }
}

public class RefundedTransactionCreator extends TransactionCreator {

    @Override
    public VisaTransaction createVisaTransaction() {
        return new RefundedVisaTransaction();
    }

    @Override
    public CashTransaction createCashTransaction() {
        return new RefundedCashTransaction();
    }
}

// Interfaces for Transactions
public interface VisaTransaction {
    void doStuff();
}

public interface CashTransaction {
    void doStuff();
}

// Concrete Transaction Classes
public class ConfirmedVisaTransaction implements VisaTransaction {

    @Override
    public void doStuff() {
        System.out.println("Processing confirmed Visa transaction.");
    }
}

public class RefundedVisaTransaction implements VisaTransaction {

    @Override
    public void doStuff() {
        System.out.println("Processing refunded Visa transaction.");
    }
}

public class ConfirmedCashTransaction implements CashTransaction {

    @Override
    public void doStuff() {
        System.out.println("Processing confirmed Cash transaction.");
    }
}

public class RefundedCashTransaction implements CashTransaction {

    @Override
    public void doStuff() {
        System.out.println("Processing refunded Cash transaction.");
    }
}

