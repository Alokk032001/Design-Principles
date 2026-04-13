

// Shared resource: CoffeeMachine acts as a buffer of size 1

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CoffeeMachine{
    private boolean hasCoffee = false;

    public synchronized void produce() throws InterruptedException {
        while (hasCoffee) {
            wait(); // Wait until the coffee is consumed
        }
        System.out.println("Produced a cup of coffee.");
        hasCoffee = true;
        notifyAll(); // Notify the consumer that coffee is produced
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasCoffee) {
            wait(); // Wait until coffee is produced
        }
        System.out.println("Consumed a cup of coffee.");
        hasCoffee = false;
        notifyAll(); // Notify the producer that coffee is consumed
    }
}

//BlockingQueue — The Production Way
class BlockingQueueExample {
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5); // Buffer of size 1

    Runnable producer = () -> {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i); // Blocks if the queue is full
                System.out.println("Produced: " + i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    };

    Runnable consumer = () -> {
        try {
            for (int i = 0; i < 10; i++) {
                int item = queue.take(); // Blocks if the queue is empty
                System.out.println("Consumed: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    };
}

class Main {
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    machine.produce();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    machine.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.shutdown();
    }
}