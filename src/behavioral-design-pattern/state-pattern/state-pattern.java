class OrderContext{
    private OrderState currentState;

    public OrderContext() {
        this.currentState = new OrderPlacedState();
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public void nextState() {
        currentState.next(this);
    }

    public void cancelOrder() {
        currentState.cancel(this);
    }

    public String getCurrentState() {
        return currentState.getStateName();
    }
}

interface OrderState {
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getStateName();
}


class OrderPlacedState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared.");
    }

    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName() {
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
        System.out.println("Order has been shipped.");
    }

    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName() {
        return "PREPARING";
    }
}

class OutForDeliveryState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }

    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order is already delivered. No further state.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel. Order is already delivered.");
    }

    public String getStateName() {
        return "DELIVERED";
    }
}

class CancelledState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order is cancelled. No further state.");
    }

    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    public String getStateName() {
        return "CANCELLED";
    }
}

class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        System.out.println("Current State: " + order.getCurrentState());

        order.nextState(); // Move to Preparing
        System.out.println("Current State: " + order.getCurrentState());

        order.nextState(); // Move to Out for Delivery
        System.out.println("Current State: " + order.getCurrentState());

        order.cancelOrder(); // Attempt to cancel (should fail)
        System.out.println("Current State: " + order.getCurrentState());

        order.nextState(); // Move to Delivered
        System.out.println("Current State: " + order.getCurrentState());

        order.cancelOrder(); // Attempt to cancel (should fail)
        System.out.println("Current State: " + order.getCurrentState());
    }
}