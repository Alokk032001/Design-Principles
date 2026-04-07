
// Using Threads by Extending the Thread Class

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SMSThread extends Thread {
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("SMS sent!");
        } catch (InterruptedException e) {
            System.out.println("SMS thread interrupted.");
        }
    }
}

class EmailThread extends Thread {
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Email sent!");
        } catch (InterruptedException e) {
            System.out.println("Email thread interrupted.");
        }
    }
}

// Using Threads by Implementing the Runnable Interface
class SMSThreadRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("SMS sent!");
        } catch (InterruptedException e) {
            System.out.println("SMS thread interrupted.");
        }
    }
}

class EmailThreadRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Email sent!");
        } catch (InterruptedException e) {
            System.out.println("Email thread interrupted.");
        }
    }
}


// Using Callable and Future for threads that return results
class ETACalculationTask implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(4000);
        System.out.println("Calculation ETA using Callable.");
        return "ETA calculated: 15 minutes";
    }
}


class Main {
    public static void main(String[] args) {
        // Using Thread class
        SMSThread smsThread = new SMSThread();
        EmailThread emailThread = new EmailThread();
        smsThread.start();
        emailThread.start();
        try {
            smsThread.join();
            emailThread.join();
            System.out.println("All notifications sent!");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Using Runnable interface
        Thread smsRunnableThread = new Thread(new SMSThreadRunnable());
        Thread emailRunnableThread = new Thread(new EmailThreadRunnable());
        smsRunnableThread.start();
        emailRunnableThread.start();
        try {
            smsRunnableThread.join();
            emailRunnableThread.join();
            System.out.println("All notifications sent using Runnable!");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");         
        }

        // Using Callable and Future
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ETACalculationTask etaTask = new ETACalculationTask();
        Future<String> futureResult = executor.submit(etaTask);
        try {
            String result = futureResult.get(); // This will block until the result is available
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error while calculating ETA: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
