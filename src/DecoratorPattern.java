// ============================================================
// DECORATOR PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Decorator Pattern in one file
// ============================================================

// ------------------------------------------------------------
// Meal.java - Interface for all meals
// ------------------------------------------------------------
interface Meal {
    void prepare();
    double getPrice();
    String getDescription();
    int getCookingTime();
}

// ------------------------------------------------------------
// Burger.java - Basic meal (Component to be decorated)
// ------------------------------------------------------------
class Burger implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    public Burger() {
        this.name = "Basic Burger";
        this.price = 8.99;
        this.cookingTime = 10;
    }
    
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger...");
        System.out.println("Grilling beef patty");
    }
    
    @Override
    public double getPrice() {
        return this.price;
    }
    
    @Override
    public String getDescription() {
        return this.name;
    }
    
    @Override
    public int getCookingTime() {
        return this.cookingTime;
    }
}

// ------------------------------------------------------------
// MealDecorator.java - Abstract base class for all decorators
// This is the core of the Decorator Pattern
// Wraps a Meal object and adds functionality
// ------------------------------------------------------------
abstract class MealDecorator implements Meal {
    // The meal being decorated (wrapped)
    protected Meal wrappedMeal;
    
    // Constructor - takes a Meal object to wrap
    // All concrete decorators must call this constructor
    public MealDecorator(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }
        this.wrappedMeal = meal;
    }
    
    // Default implementation - delegates to wrapped meal
    // Concrete decorators override these to add functionality
    @Override
    public void prepare() {
        wrappedMeal.prepare();
    }
    
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice();
    }
    
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription();
    }
    
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime();
    }
}

// ------------------------------------------------------------
// CheeseDecorator.java - Adds cheese to a meal
// ------------------------------------------------------------
class CheeseDecorator extends MealDecorator {
    private static final double CHEESE_PRICE = 1.50;
    
    // Constructor - wraps a meal object
    public CheeseDecorator(Meal meal) {
        super(meal); // Call parent constructor
        System.out.println("Decorator: Adding Cheese (+$" + CHEESE_PRICE + ")");
    }
    
    // Override prepare method to add cheese preparation step
    @Override
    public void prepare() {
        wrappedMeal.prepare(); // Call wrapped meal's prepare first
        System.out.println("Adding extra cheese topping...");
    }
    
    // Override getPrice to add cheese cost to wrapped meal's price
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + CHEESE_PRICE;
    }
    
    // Override getDescription to append cheese to description
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Extra Cheese";
    }
    
    // Cooking time slightly increases (1 minute for melting cheese)
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime() + 1;
    }
}

// ------------------------------------------------------------
// BaconDecorator.java - Adds bacon to a meal
// ------------------------------------------------------------
class BaconDecorator extends MealDecorator {
    private static final double BACON_PRICE = 2.50;
    
    // Constructor - wraps a meal object
    public BaconDecorator(Meal meal) {
        super(meal); // Call parent constructor
        System.out.println("Decorator: Adding Bacon (+$" + BACON_PRICE + ")");
    }
    
    // Override prepare method to add bacon preparation step
    @Override
    public void prepare() {
        wrappedMeal.prepare(); // Call wrapped meal's prepare first
        System.out.println("Adding crispy bacon strips...");
    }
    
    // Override getPrice to add bacon cost to wrapped meal's price
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + BACON_PRICE;
    }
    
    // Override getDescription to append bacon to description
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Bacon";
    }
    
    // Cooking time increases (2 minutes for cooking bacon)
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime() + 2;
    }
}

// ------------------------------------------------------------
// SauceDecorator.java - Adds special sauce to a meal
// ------------------------------------------------------------
class SauceDecorator extends MealDecorator {
    private static final double SAUCE_PRICE = 1.00;
    
    // Constructor - wraps a meal object
    public SauceDecorator(Meal meal) {
        super(meal); // Call parent constructor
        System.out.println("Decorator: Adding Special Sauce (+$" + SAUCE_PRICE + ")");
    }
    
    // Override prepare method to add sauce preparation step
    @Override
    public void prepare() {
        wrappedMeal.prepare(); // Call wrapped meal's prepare first
        System.out.println("Drizzling special house sauce...");
    }
    
    // Override getPrice to add sauce cost to wrapped meal's price
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + SAUCE_PRICE;
    }
    
    // Override getDescription to append sauce to description
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Special Sauce";
    }
    
    // Cooking time doesn't change (sauce is added after cooking)
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime();
    }
}

// ------------------------------------------------------------
// Demo class to test Decorator Pattern
// ------------------------------------------------------------
class DecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("DECORATOR PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Start with a basic burger
        System.out.println("Step 1: Creating base burger");
        System.out.println("-".repeat(60));
        Meal myBurger = new Burger();
        System.out.println(myBurger.getDescription() + " = $" + myBurger.getPrice());
        System.out.println();
        
        // Add cheese decorator
        System.out.println("Step 2: Adding cheese");
        System.out.println("-".repeat(60));
        myBurger = new CheeseDecorator(myBurger);
        System.out.println(myBurger.getDescription() + " = $" + myBurger.getPrice());
        System.out.println();
        
        // Add bacon decorator
        System.out.println("Step 3: Adding bacon");
        System.out.println("-".repeat(60));
        myBurger = new BaconDecorator(myBurger);
        System.out.println(myBurger.getDescription() + " = $" + myBurger.getPrice());
        System.out.println();
        
        // Add sauce decorator
        System.out.println("Step 4: Adding special sauce");
        System.out.println("-".repeat(60));
        myBurger = new SauceDecorator(myBurger);
        System.out.println(myBurger.getDescription() + " = $" + myBurger.getPrice());
        System.out.println();
        
        // Final preparation
        System.out.println("Final meal preparation:");
        System.out.println("=".repeat(60));
        myBurger.prepare();
        System.out.println();
        System.out.println("Final Price: $" + myBurger.getPrice());
        System.out.println("Final Cooking Time: " + myBurger.getCookingTime() + " minutes");
        System.out.println();
        
        // Visual representation of decorator wrapping
        System.out.println("Decorator Structure:");
        System.out.println("-".repeat(60));
        System.out.println("SauceDecorator (wraps)");
        System.out.println("  └─ BaconDecorator (wraps)");
        System.out.println("      └─ CheeseDecorator (wraps)");
        System.out.println("          └─ Basic Burger");
        System.out.println();
        
        System.out.println("=".repeat(60));
        System.out.println("Decorator Pattern successfully added features dynamically!");
        System.out.println("=".repeat(60));
    }
}
