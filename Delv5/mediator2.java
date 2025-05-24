interface UserMediator {
    User loginUser(String email, String password);
}

class AuthenticationMediator implements UserMediator {
    private UserRepo userRepo;

    public AuthenticationMediator(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.userRepo.setMediator(this); // optional if repo needs back ref
    }

    @Override
    public User loginUser(String email, String password) {
        System.out.println("[Mediator] Delegating login to UserRepo...");
        return userRepo.loginUser(email, password);
    }
}

class UserRepo {
    private UserMediator mediator; // optional back-reference

    public void setMediator(UserMediator mediator) {
        this.mediator = mediator;
    }

    public User loginUser(String email, String password) {
        // Simulate DB access
        System.out.println("[UserRepo] Checking credentials...");
        if (email.equals("user@example.com") && password.equals("1234")) {
            return new User("John Doe", email);
        }
        return null;
    }
}

class LoginPage {
    private UserMediator mediator;

    public LoginPage(UserMediator mediator) {
        this.mediator = mediator;
    }

    public void attemptLogin(String email, String password) {
        User user = mediator.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }
}

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepo repo = new UserRepo();
        AuthenticationMediator mediator = new AuthenticationMediator(repo);
        LoginPage loginPage = new LoginPage(mediator);

        loginPage.attemptLogin("user@example.com", "1234");
        loginPage.attemptLogin("wrong@example.com", "wrong");
    }
}
