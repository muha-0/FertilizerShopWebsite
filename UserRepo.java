
import java.io.*;
import java.util.*;

/**
 * 
 */
public class UserRepo {

    /**
     * Default constructor
     */
    private UserRepo() {
    }

    /**
     * 
     */
    private static volatile UserRepo instance;

    /**
     * @return
     */
    public static UserRepo getInstance() {
        UserRepo result = instance;
        if(result == null){
            synchronized(UserRepo.class){
                result = instance;
                if(result == null){
                    instance = result = new UserRepo();
                }
            }
        }
        return result;
    }


    public void SaveUser(User user) {
        // TODO implement here
        return null;
    }

}