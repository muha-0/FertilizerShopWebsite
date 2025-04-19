import java.util.*;

public class Main {
  public static void main(String[] args) {
    PaymentTypeProcessor visa = new VisaPaymentProcessor();
    PaymentTypeProcessor newPaypal = new PaypalPaymentProcessorAdaptor();

    PaymentProcessor guestVisa = new GuestPaymentProcessor(visa);
    PaymentProcessor subscribedPaypal = new SubscribedPaymentProcessor(newPaypal);

    guestVisa.pay(100);
    subscribedPaypal.pay(250);
  }
}

/**
 * 
 */

public interface PaymentTypeProcessor {
    public JSON pay(int amount);
}

public class VisaPaymentProcessor implements PaymentTypeProcessor{
  public JSON pay(int amount){
    // Call Api
    System.out.println("calling Visa API");
    return new JSON();
  }
}

public class PaypalPaymentProcessor implements PaymentTypeProcessor{
  public JSON pay(int amount){
    // Call Api
    System.out.println("calling Paypal API");
    return new JSON();
  }
}

public class NewPaypalService{
  //Different API returns XML data
  public XML pay(int amount){
    // Call New Api
    System.out.println("calling new Paypal API");
    return new XML();
  }
}

public class PaypalPaymentProcessorAdaptor implements PaymentTypeProcessor{
  private NewPaypalService service = new NewPaypalService();
  private JSON XMLtoJSON(XML data){
    // Do transformation
    return new JSON();
  }
  public JSON pay(int amount){
    // Call Api
    XML data = service.pay(amount);
    return XMLtoJSON(data);
  }
}

public abstract class PaymentProcessor{
  protected PaymentTypeProcessor processor;
  public PaymentProcessor(PaymentTypeProcessor processor){
    this.processor = processor;
  }
  public abstract JSON pay(int amount);
}

public class SubscribedPaymentProcessor extends PaymentProcessor{
  public SubscribedPaymentProcessor(PaymentTypeProcessor processor){
    super(processor);
  }
  public JSON pay(int amount){
    System.out.println("Sending Vouchers/Discounts/Free Delivery");
    return super.processor.pay(amount);
  }
}

public class GuestPaymentProcessor extends PaymentProcessor{
  public GuestPaymentProcessor(PaymentTypeProcessor processor){
    super(processor);
  }
  public JSON pay(int amount){
    System.out.println("Trying to attract him");
    return super.processor.pay(amount);
  }
}

public class JSON{
  String data;
  public JSON(){
    data = "Dummy Data";
  }
}

public class XML{
  String data;
  public XML(){
    data = "Dummy Data";
  }
}