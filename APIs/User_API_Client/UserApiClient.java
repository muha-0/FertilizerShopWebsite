
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import com.google.gson.*;

public class UserApiClient {
    private static final String BASE_URL = "http://localhost:8080/users";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static void registerUser(User user) throws IOException, InterruptedException {
        String json = gson.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Register Response: " + response.body());
    }

    public static boolean loginUser(String email, String password_hash) throws IOException, InterruptedException {
        String json = String.format("""
                {
                  "email": "%s",
                  "password_hash": "%s"
                }
                """, email, password_hash);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Login Successful");
            return true;
        } else {
            System.out.println("Login Failed");
            return false;
        }
    }
}
