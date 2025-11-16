// ============================================================
// BUILDER PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Builder Pattern in one file
// ============================================================

import java.util.ArrayList;
import java.util.List;

// ------------------------------------------------------------
// Meal.java - Interface (needed for compatibility)
// ------------------------------------------------------------
interface Meal {
    void prepare();
    double getPrice();
    String getDescription();
    int getCookingTime();
}

// ------------------------------------------------------------
// CustomPizza.java - The product being built
// This is the complex object that Builder creates
// ------------------------------------------------------------
class CustomPizza implements Meal {
    // Pizza components
    private String dough;
    private String sauce;
    private List<String> toppings;
    private double basePrice = 10.99; // Base price for custom pizza
    
    // Constructor - package-private so only PizzaBuilder can create instances
    // Takes all required components as parameters
    CustomPizza(String dough, String sauce, List<String> toppings) {
        this.dough = dough;
        this.sauce = sauce;
        this.toppings = new ArrayList<>(toppings); // Create defensive copy
    }
    
    @Override
    public void prepare() {
        System.out.println("Preparing Custom Pizza:");
        System.out.println("- Dough: " + dough);
        System.out.println("- Sauce: " + sauce);
        System.out.println("- Toppings: " + String.join(", ", toppings));
    }
    
    @Override
    public double getPrice() {
        // Base price + $1.50 per topping
        return basePrice + (toppings.size() * 1.50);
    }
    
    @Override
    public String getDescription() {
        return "Custom Pizza with " + dough + ", " + sauce + 
               " and " + toppings.size() + " toppings";
    }
    
    @Override
    public int getCookingTime() {
        // Base time 15 minutes + 1 minute per topping
        return 15 + toppings.size();
    }
    
    // Getter methods for pizza components
    public String getDough() {
        return dough;
    }
    
    public String getSauce() {
        return sauce;
    }
    
    public List<String> getToppings() {
        return new ArrayList<>(toppings); // Return defensive copy
    }
}

// ------------------------------------------------------------
// PizzaBuilder.java - Builder class
// This is the core of the Builder Pattern
// Uses method chaining (fluent interface) for readable code
// ------------------------------------------------------------
class PizzaBuilder {
    // Fields to store pizza components during building
    private String dough;
    private String sauce;
    private List<String> toppings;
    
    // Constructor - initializes empty topping list
    public PizzaBuilder() {
        this.toppings = new ArrayList<>();
    }
    
    // Set the dough type
    // Returns 'this' to allow method chaining (fluent interface)
    public PizzaBuilder setDough(String dough) {
        this.dough = dough;
        System.out.println("Builder: Dough set to " + dough);
        return this; // Return this for method chaining
    }
    
    // Set the sauce type
    // Returns 'this' to allow method chaining
    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        System.out.println("Builder: Sauce set to " + sauce);
        return this; // Return this for method chaining
    }
    
    // Add a topping to the pizza
    // Can be called multiple times to add multiple toppings
    // Returns 'this' to allow method chaining
    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        System.out.println("Builder: Added topping - " + topping);
        return this; // Return this for method chaining
    }
    
    // Build the final Pizza object
    // Validates that required components are set
    // Returns a new CustomPizza object with all components
    // This is the final step in the building process
    public CustomPizza build() {
        // Validate that required fields are set before building
        if (dough == null || dough.isEmpty()) {
            throw new IllegalStateException("Dough must be set before building pizza");
        }
        if (sauce == null || sauce.isEmpty()) {
            throw new IllegalStateException("Sauce must be set before building pizza");
        }
        if (toppings.isEmpty()) {
            System.out.println("Warning: Building pizza with no toppings");
        }
        
        System.out.println("Builder: Pizza construction complete!");
        
        // Create and return the final pizza object
        return new CustomPizza(dough, sauce, toppings);
    }
}

// ------------------------------------------------------------
// Demo class to test Builder Pattern
// ------------------------------------------------------------
class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("BUILDER PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Example 1: Build a custom pizza with multiple toppings
        System.out.println("Building Custom Pizza #1:");
        System.out.println("-".repeat(60));
        
        CustomPizza pizza1 = new PizzaBuilder()
                .setDough("Thin Crust")              // Step 1: Set dough
                .setSauce("Tomato Sauce")            // Step 2: Set sauce
                .addTopping("Mozzarella")            // Step 3: Add cheese
                .addTopping("Pepperoni")             // Step 4: Add pepperoni
                .addTopping("Mushrooms")             // Step 5: Add mushrooms
                .addTopping("Olives")                // Step 6: Add olives
                .build();                            // Final: Build the pizza
        
        System.out.println();
        pizza1.prepare();
        System.out.println("Price: $" + pizza1.getPrice());
        System.out.println("Cooking time: " + pizza1.getCookingTime() + " minutes");
        System.out.println();
        
        // Example 2: Build a different pizza with different ingredients
        System.out.println("-".repeat(60));
        System.out.println("Building Custom Pizza #2:");
        System.out.println("-".repeat(60));
        
        CustomPizza pizza2 = new PizzaBuilder()
                .setDough("Thick Crust")
                .setSauce("BBQ Sauce")
                .addTopping("Chicken")
                .addTopping("Bacon")
                .addTopping("Onions")
                .build();
        
        System.out.println();
        pizza2.prepare();
        System.out.println("Price: $" + pizza2.getPrice());
        System.out.println("Cooking time: " + pizza2.getCookingTime() + " minutes");
        System.out.println();
        
        System.out.println("=".repeat(60));
        System.out.println("Builder Pattern successfully created complex pizza objects!");
        System.out.println("=".repeat(60));
    }
}
