
class PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
    }
}

// Types of Dependency Injection (DI)

// 1. Constructor Injection
class OrderService {
    private final PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processOrder(int amount) {
        paymentGateway.processPayment(amount);
    }
}

// 2. Setter Injection
class UserService {
    private PaymentGateway paymentGateway;

    public void setPaymentGateway(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
        System.out.println(this.paymentGateway);
    }
}

// 3. Interface Injection
interface Service {
    void injectPayment(PaymentGateway payment);
}

class OrderServiceInterfaceInjection implements Service {
    private PaymentGateway paymentGateway;

    @Override
    public void injectPayment(PaymentGateway payment) {
        this.paymentGateway = payment;
    }

    public void execute() {
        paymentGateway.processPayment(100.0);
    }
}

// Best Practices for Implementing Dependency Injection in Java

interface NotificationService {
    void sendNotification(String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}

class UserServiceWithDI {
    private final NotificationService notificationService;

    public UserServiceWithDI(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String username) {
        // Registration logic
        System.out.println("User " + username + " registered successfully.");
        notificationService.sendNotification("Welcome " + username + "!");
    }
}

class Main {
    public static void main(String[] args) {
        NotificationService emailService = new EmailNotificationService();
        UserServiceWithDI userService = new UserServiceWithDI(emailService);
        userService.registerUser("Alice");
    }
}