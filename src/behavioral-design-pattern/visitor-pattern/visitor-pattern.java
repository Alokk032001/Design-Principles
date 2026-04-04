interface Item {
    void accept(ItemVisitor visitor);
}

class PhysicalProduct implements Item{
    String name;
    double price;

    public PhysicalProduct(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

class DigitalProduct implements Item{
    String name;
    double price;

    public DigitalProduct(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

class GiftCard implements Item{
    String code;
    double value;

    public GiftCard(String code, double value){
        this.code = code;
        this.value = value;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

interface ItemVisitor {
    void visit(PhysicalProduct product);
    void visit(DigitalProduct product);
    void visit(GiftCard giftCard);
}

class InvoiceVisitor implements ItemVisitor {
    private double totalAmount = 0.0;

    public void visit(PhysicalProduct product) {
        totalAmount += product.price;
        System.out.println("Added physical product: " + product.name + " - $" + product.price);
    }

    public void visit(DigitalProduct product) {
        totalAmount += product.price;
        System.out.println("Added digital product: " + product.name + " - $" + product.price);
    }

    public void visit(GiftCard giftCard) {
        totalAmount += giftCard.value;
        System.out.println("Added gift card: " + giftCard.code + " - $" + giftCard.value);
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class ShippingCostVisitor implements ItemVisitor {
    private double totalShippingCost = 0.0;

    public void visit(PhysicalProduct product) {
        totalShippingCost += 5.0; // Flat shipping cost for physical products
        System.out.println("Calculated shipping for physical product: " + product.name + " - $5.0");
    }

    public void visit(DigitalProduct product) {
        // No shipping cost for digital products
        System.out.println("No shipping cost for digital product: " + product.name);
    }

    public void visit(GiftCard giftCard) {
        // No shipping cost for gift cards
        System.out.println("No shipping cost for gift card: " + giftCard.code);
    }

    public double getTotalShippingCost() {
        return totalShippingCost;
    }
}

class Main {
    public static void main(String[] args) {
        Item[] items = {
            new PhysicalProduct("Laptop", 999.99),
            new DigitalProduct("E-book", 19.99),
            new GiftCard("GIFT123", 50.00)
        };

        InvoiceVisitor invoiceVisitor = new InvoiceVisitor();
        ShippingCostVisitor shippingCostVisitor = new ShippingCostVisitor();

        for (Item item : items) {
            item.accept(invoiceVisitor);
            item.accept(shippingCostVisitor);
        }

        System.out.println("Total Invoice Amount: $" + invoiceVisitor.getTotalAmount());
        System.out.println("Total Shipping Cost: $" + shippingCostVisitor.getTotalShippingCost());
    }
}
