import java.io.*;
import java.util.*;

/**
 * 
 */
public class OrderRepo {

    /**
     * Default constructor
     */
    private OrderRepo() {
    }

    /**
     * 
     */
    private static volatile OrderRepo instance;

    /**
     * @return
     */
    public static OrderRepo getInstance() {
        OrderRepo result = instance;
        if(result == null){
            synchronized(OrderRepo.class){
                result = instance;
                if(result == null){
                    instance = result = new OrderRepo();
                }
            }
        }
        return result;
    }

    public void SaveOrder(Order order) {
        // TODO implement here
        return null;
    }

}
