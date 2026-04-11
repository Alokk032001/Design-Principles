package multithreading.locks.semaphores;

import java.util.concurrent.Semaphore;

public class HotStarPlusAccount {
    private final Semaphore deviceSlots;
    public HotStarPlusAccount(int maxDevices) {
        this.deviceSlots = new Semaphore(maxDevices);
    }

    public boolean login(String user) {
        try {
            if (deviceSlots.tryAcquire()) {
                System.out.println(user + " logged in successfully.");
                return true;
            } else {
                System.out.println(user + " failed to log in: Maximum device limit reached.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during login for " + user + ": " + e.getMessage());
            return false;
        }
    }

    public void logout(String user) {
        deviceSlots.release();
        System.out.println(user + " logged out successfully.");
    }
}

class Main {
    public static void main(String[] args) {
        HotStarPlusAccount account = new HotStarPlusAccount(2); // Max 2 devices

        // Simulate multiple users trying to log in
        Thread user1 = new Thread(() -> {
            if (account.login("User1")) {
                try { Thread.sleep(2000); } catch (InterruptedException e) {}
                account.logout("User1");
            }
        });

        Thread user2 = new Thread(() -> {
            if (account.login("User2")) {
                try { Thread.sleep(3000); } catch (InterruptedException e) {}
                account.logout("User2");
            }
        });

        Thread user3 = new Thread(() -> {
            if (account.login("User3")) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                account.logout("User3");
            }
        });

        user1.start();
        user2.start();
        user3.start();
    }
}