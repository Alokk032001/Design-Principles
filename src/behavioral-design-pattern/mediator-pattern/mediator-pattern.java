// Mediator Interface

import java.util.ArrayList;
import java.util.List;

interface DocumentSessionMediator {
    void broadcastChange(String change, User sender);
    void join(User user);
}

class CollaborativeDocument implements DocumentSessionMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void broadcastChange(String change, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveChange(change);
            }
        }
    }

    @Override
    public void join(User user) {
        users.add(user);
        System.out.println(user.getName() + " joined the document session.");
    }
}

class User {
    protected String name;
    protected DocumentSessionMediator mediator;

    public User(String name, DocumentSessionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.join(this);
    }

    public String getName() {
        return name;
    }

    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        mediator.broadcastChange(change, this);
    }

    public void receiveChange(String change) {
        System.out.println(name + " received change: " + change);
    }
}

class Main {
    public static void main(String[] args) {
        DocumentSessionMediator mediator = new CollaborativeDocument();

        User alice = new User("Alice", mediator);
        User bob = new User("Bob", mediator);
        User charlie = new User("Charlie", mediator);

        alice.makeChange("Added introduction section.");
        bob.makeChange("Corrected typos in the introduction.");
        charlie.makeChange("Added conclusion section.");
    }
}

