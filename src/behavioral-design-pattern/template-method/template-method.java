abstract class NotificationSender {
    public final void send(String to, String message) {
        rateLimitCheck(to);
        validateRecipient(to);
        String formattedMessage = formatMessage(message);
        String composedMessage = composeMessage(formattedMessage);
        sendMessage(to, composedMessage);

        postSendAnalytics(to);
    }

    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for recipient: " + to);
    }

    private void validateRecipient(String to) {
        System.out.println("Validating recipient: " + to);
    }

    private String formatMessage(String message) {
        return "Formatted: " + message;
    }

    protected abstract String composeMessage(String formattedMessage);

    protected abstract void sendMessage(String to, String message);

    protected void postSendAnalytics(String to) {
        System.out.println("Logging analytics for recipient: " + to);
    }
}

class EmailNotificationSender extends NotificationSender {
    @Override
    protected String composeMessage(String formattedMessage) {
        return "Email Body: " + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending Email to " + to + ": " + message);
    }
}

class SMSNotificationSender extends NotificationSender {
    @Override
    protected String composeMessage(String formattedMessage) {
        return "SMS Content: " + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}

class Main {
    public static void main(String[] args) {
        NotificationSender emailSender = new EmailNotificationSender();
        emailSender.send("user@example.com", "Hello, this is an email notification!");

        NotificationSender smsSender = new SMSNotificationSender();
        smsSender.send("1234567890", "Hello, this is an SMS notification!");
    }
}   