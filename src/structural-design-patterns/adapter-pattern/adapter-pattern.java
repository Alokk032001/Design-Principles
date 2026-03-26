interface paymentGateway {
    void pay(String orderId, double amount);
}

class PayU implements paymentGateway {
    public void pay(String orderId, double amount) {
        System.out.println("Processing PayPal payment for Order: " + orderId + ", Amount: " + amount);
    }
}

class Stripe {
    public void makePayment(String orderId, double amount) {
        System.out.println("Processing Stripe payment for Order: " + orderId + ", Amount: " + amount);
    }
}

class StripeAdapter implements paymentGateway{
    private Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }

    public void pay(String orderId, double amount) {
        stripe.makePayment(orderId, amount);
    }
}

class CheckoutService{
    private paymentGateway gateway;

    public CheckoutService(paymentGateway gateway) {
        this.gateway = gateway;
    }

    public void completeOrder(String orderId, double amount) {
        gateway.pay(orderId, amount);
    }
}

class Main {
    public static void main(String[] args) {
        CheckoutService checkout1 = new CheckoutService(new PayU());
        checkout1.completeOrder("ORD123", 250.0);

        // Using Stripe with an adapter
        Stripe stripe = new Stripe();
        paymentGateway stripeAdapter = new StripeAdapter(stripe);
        CheckoutService checkout2 = new CheckoutService(stripeAdapter);
        checkout2.completeOrder("ORD456", 500.0);
    }
}