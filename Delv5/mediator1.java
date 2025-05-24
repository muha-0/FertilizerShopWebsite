import java.util.*;

// Mediator interface
interface Mediator {
    void sendMessage(String message, Employee sender);
}

// Abstract colleague
abstract class Employee {
    protected Mediator mediator;
    protected String name;

    public Employee(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);
}

class Manager extends Employee {

    public Manager(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("[Manager] " + name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("[Manager] " + name + " received: " + message);
    }
}

class Staff extends Employee {

    public Staff(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("[Staff] " + name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("[Staff] " + name + " received: " + message);
    }
}

class CommunicationMediator implements Mediator {

    private List<Employee> employees = new ArrayList<>();

    public void register(Employee e) {
        employees.add(e);
    }

    @Override
    public void sendMessage(String message, Employee sender) {
        for (Employee e : employees) {
            if (e != sender) {
                e.receiveMessage(message);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CommunicationMediator mediator = new CommunicationMediator();

        Manager manager = new Manager("Alice", mediator);
        Staff staff1 = new Staff("Bob", mediator);
        Staff staff2 = new Staff("Charlie", mediator);

        mediator.register(manager);
        mediator.register(staff1);
        mediator.register(staff2);

        manager.sendMessage("Prepare the monthly report.");
        staff1.sendMessage("Report will be ready by EOD.");
    }
}
