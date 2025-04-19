import java.util.*;

/**
 * 
 */
public interface LoginPage {
    public void display(DesktopData data);
}

public class DesktopLoginPage implements LoginPage{
  public void display(DesktopData data){
    System.out.println("Uses desktop needed data");
  }
}

public class MobileLoginPage{
  public void display(MobileData data){
    System.out.println("Uses mobile needed data");
  }
}

public class LoginPageAdaptor implements LoginPage{
  private MobileLoginPage mobileService = new MobileLoginPage();;
  private MobileData transform(DesktopData data){
    //Maybe multiply dimensions by a value or something
    return new MobileData(data.dimensions);
  }
  public void display(DesktopData data){
    MobileData transformedData = transform(data);
    mobileService.display(transformedData);
  }
}

public class MobileData{
  public String dimensions;
  MobileData(String dimensions){
    this.dimensions = dimensions;
  }
}

public class DesktopData{
  public String dimensions;
  DesktopData(String dimensions){
    this.dimensions = dimensions;
  }
}



