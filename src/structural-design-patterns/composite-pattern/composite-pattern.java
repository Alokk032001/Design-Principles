import java.util.ArrayList;
import java.util.List;

interface CartItem {
    String getName();
    double getPrice();
}

class Product implements CartItem {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class ProductBundle implements CartItem {
    private String name;
    private List<CartItem> items;

    public ProductBundle(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    @Override
    public String getName() {
        return name + " (Bundle)";
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total * 0.9; // 10% discount for bundles
    }
}

class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Laptop", 1000);
        Product product2 = new Product("Mouse", 50);
        Product product3 = new Product("Keyboard", 80);
        Product product4 = new Product("book", 300);

        ProductBundle bundle1 = new ProductBundle("Office Set");
        bundle1.addItem(product1);
        bundle1.addItem(product2);
        bundle1.addItem(product3);

        List<CartItem> cart = new ArrayList<>();
        cart.add(product4);
        cart.add(bundle1);

        double totalPrice = 0;

        for(CartItem item: cart){
            System.out.println(item.getName() + " costs ₹" + item.getPrice());
            totalPrice += item.getPrice();
        }

        System.out.println("Total price: ₹" + totalPrice);
    }
}
