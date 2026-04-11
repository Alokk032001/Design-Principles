import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class StockData {
    private double price = 100.0;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void updatePrice(double newPrice) {
        rwLock.writeLock().lock();
        try {
            price = newPrice;
            System.out.println("Price updated to: " + price);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public double getPrice() {
        rwLock.readLock().lock();
        try {
            System.out.println("Price read: " + price);
            return price;
        } finally {
            rwLock.readLock().unlock();
        }
    }

}

class Main {
    public static void main(String[] args) {
        StockData stockData = new StockData();

        // Simulate multiple readers
        for (int i = 0; i < 5; i++) {
            new Thread(stockData::getPrice).start();
        }

        // Simulate a writer
        new Thread(() -> stockData.updatePrice(105.0)).start();
    }
}