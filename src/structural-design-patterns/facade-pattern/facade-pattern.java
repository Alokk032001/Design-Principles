// Service class responsible for handling payments
class PaymentService {
    public void makePayment(String accountId, double amount) {
        System.out.println("Payment of ₹" + amount + " successful for account " + accountId);
    }
}

// Service class responsible for reserving seats
class SeatReservationService {
    public void reserveSeat(String movieId, String seatNumber) {
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}

// Service class responsible for sending notifications
class NotificationService {
    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

// Service class for managing loyalty/reward points
class LoyaltyPointsService {
    public void addPoints(String accountId, int points) {
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

// Service class for generating movie tickets
class TicketService {
    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}

// ========== The MovieBookingFacade class  ==============
class MovieBookingFacade {
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;

    public MovieBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    public void bookMovie(String accountId, String userEmail, String movieId, String seatNumber, double amount) {
        // Step 1: Make payment
        paymentService.makePayment(accountId, amount);

        // Step 2: Reserve seat
        seatReservationService.reserveSeat(movieId, seatNumber);

        // Step 3: Generate ticket
        ticketService.generateTicket(movieId, seatNumber);

        // Step 4: Add loyalty points
        loyaltyPointsService.addPoints(accountId, (int) amount / 10); // Example: 1 point for every ₹10 spent

        // Step 5: Send booking confirmation
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully!");
    }
}

class Main {
    public static void main(String[] args) {
        MovieBookingFacade bookingFacade = new MovieBookingFacade();
        bookingFacade.bookMovie("user123", "movie456", "A10", "user@example.com", 500);
    }
}
