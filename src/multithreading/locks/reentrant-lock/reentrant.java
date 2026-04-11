import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats = 2;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookSeat(String user) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(user + " booked a seat. Remaining: " + (availableSeats - 1));
                availableSeats--;
            } else {
                System.out.println(user + " tried to book a seat, but none are available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        Thread user1 = new Thread(() -> bookingSystem.bookSeat("User1"));
        Thread user2 = new Thread(() -> bookingSystem.bookSeat("User2"));
        Thread user3 = new Thread(() -> bookingSystem.bookSeat("User3"));
        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}