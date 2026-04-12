package multithreading.locks.deadlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// Lock Ordering Example to Avoid Deadlocks
class LockOrderingSimple {
    static class Resource {
        int id;
        int value;

        public Resource(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Resource resourceA = new Resource(1, 100);
        Resource resourceB = new Resource(2, 200);

        Runnable task1 = () -> transfer(resourceA, resourceB, 50);
        Runnable task2 = () -> transfer(resourceB, resourceA, 30);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

    }

    public static void transfer(Resource a, Resource b, int amount) {
        Resource[] locks = (a.id < b.id) ? new Resource[] { a, b } : new Resource[] { b, a };

        synchronized (locks[0]) {
            System.out.println(Thread.currentThread().getName() + " locked Resource " + locks[0].id);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (locks[1]) {
                System.out.println(Thread.currentThread().getName() + " locked Resource " + locks[1].id);
                a.value -= amount;
                b.value += amount;
                System.out.println("Transferred " + amount + " from Resource " + a.id + " to Resource " + b.id);
            }
        }
    }
}

// try lock with timeout to avoid deadlocks
class TryLockWithTimeout {
    static class Resource {
        final int id;
        final ReentrantLock lock = new ReentrantLock();

        public Resource(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Resource resourceA = new Resource(1);
        Resource resourceB = new Resource(2);

        Runnable task1 = () -> transfer(resourceA, resourceB, 50);
        Runnable task2 = () -> transfer(resourceB, resourceA, 30);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }

    public static void transfer(Resource a, Resource b, int amount) {
        boolean acquiredA = false;
        boolean acquiredB = false;
        try {
            acquiredA = a.lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (acquiredA) {
                System.out.println(Thread.currentThread().getName() + " locked Resource " + a.id);
                Thread.sleep(50); // Simulate some work

                acquiredB = b.lock.tryLock(100, TimeUnit.MILLISECONDS);
                if (acquiredB) {
                    System.out.println(Thread.currentThread().getName() + " locked Resource " + b.id);
                    // Perform transfer logic here
                    System.out.println("Transferred " + amount + " from Resource " + a.id + " to Resource " + b.id);
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to lock Resource " + b.id);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to lock Resource " + a.id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquiredA) {
                a.lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked Resource " + a.id);
            }
            if (acquiredB) {
                b.lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked Resource " + b.id);
            }
        }

    }
}
