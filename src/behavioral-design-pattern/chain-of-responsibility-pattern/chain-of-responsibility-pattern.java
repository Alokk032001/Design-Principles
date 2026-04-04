abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}

class GeneralSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("general")) {
            System.out.println("General Support: Handling general issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("General Support: No handler available for this issue.");
        }
    }
}

class TechnicalSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("technical")) {
            System.out.println("Technical Support: Handling technical issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Technical Support: No handler available for this issue.");
        }
    }
}

class BillingSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("billing")) {
            System.out.println("Billing Support: Handling billing issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Billing Support: No handler available for this issue.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        SupportHandler generalSupport = new GeneralSupportHandler();
        SupportHandler technicalSupport = new TechnicalSupportHandler();
        SupportHandler billingSupport = new BillingSupportHandler();

        generalSupport.setNextHandler(technicalSupport);
        technicalSupport.setNextHandler(billingSupport);

        generalSupport.handleRequest("general");
        generalSupport.handleRequest("technical");
        generalSupport.handleRequest("billing");
        generalSupport.handleRequest("unknown");
    }
}
