// =========== Component Interface ============
interface Pizza {
    String getDescription();

    double getCost();
}

// ============= Concrete Components: Base pizza ==============
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 150.00;
    }
}

class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.00;
    }
}

// ======================== Abstract Decorator ===========================
// ====== Implements Pizza and holds a reference to a Pizza object =======
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

// ============ Concrete Decorator: Adds Extra Cheese ================
class ExtraChesse extends PizzaDecorator {
    public ExtraChesse(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
}

// ============ Concrete Decorator: Adds Olives ================
class Olives extends PizzaDecorator {
    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0;
    }
}

class Main{
    public static void main(String[] args) {
        Pizza myPizza = new Olives(new ExtraChesse(new MargheritaPizza()));
        System.out.println("Total Cost: ₹" + myPizza.getCost());
    }
}