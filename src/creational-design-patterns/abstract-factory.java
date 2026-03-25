interface PaymentGateway {
    void processPayment(double amount);
}

interface Invoice {
    void generateInvoice();
}

// ========== India Implementations ==========
class RazorpayGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through Razorpay.");
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: " + amount);
    }
}

class GSTInvoice implements Invoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST invoice.");
    }
}

// ========== US Implementations ==========
class PayPalGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through PayPal.");
    }
}

class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via Stripe: " + amount);
    }
}

class USInvoice implements Invoice {
    @Override
    public void generateInvoice() {
        System.out.println("Generating US invoice for amount:");
    }
}

// ========== Abstract Factory ==========
interface RegionalFactory {
    PaymentGateway createPaymentGateway(String gatewayType);

    Invoice createInvoice();
}

// ========== Concrete Factories ==========
class IndiaFactory implements RegionalFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("razorpay")) {
            return new RazorpayGateway();
        } else if (gatewayType.equalsIgnoreCase("payu")) {
            return new PayUGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for India: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory implements RegionalFactory {
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("paypal")) {
            return new PayPalGateway();
        } else if (gatewayType.equalsIgnoreCase("stripe")) {
            return new StripeGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for US: " + gatewayType);
    }

    public Invoice createInvoice() {
        return new USInvoice();
    }
}


// ========== Checkout Service ==========
class CheckoutService{
    private PaymentGateway paymentGateway;
    private Invoice invoice;

    public CheckoutService(RegionalFactory factory, String gatewayType){
        this.paymentGateway=factory.createPaymentGateway(gatewayType);
        this.invoice=factory.createInvoice();

    }

    public void completeOrder(double amount){
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

class Main{
    public static void main(String[] args) {
        CheckoutService checkoutServiceInd = new CheckoutService(new IndiaFactory(),"razorpay");
        CheckoutService checkoutServiceUs = new CheckoutService(new USFactory(),"stripe");

        checkoutServiceInd.completeOrder(1000);
        checkoutServiceUs.completeOrder(2000);
    }
}