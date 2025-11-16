// ============================================================
// FILE: src/builder/CustomPizza.java
// ============================================================
package builder;

import factory.Meal;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomPizza class - Complex product for Builder Pattern
 * Represents a customizable pizza with various components
 */
public class CustomPizza implements Meal {
    private final String dough;
    private final String sauce;
    private final List<String> toppings;
    private final double basePrice = 10.99;
    
    /**
     * Package-private constructor - only PizzaBuilder can create instances
     * @param dough Type of dough
     * @param sauce Type of sauce
     * @param toppings List of toppings
     */
    CustomPizza(String dough, String sauce, List<String> toppings) {
        this.dough = dough;
        this.sauce = sauce;
        this.toppings = new ArrayList<>(toppings); // Defensive copy
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
    
    // Getter methods
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

// ============================================================
// FILE: src/builder/PizzaBuilder.java
// ============================================================
package builder;

import java.util.ArrayList;
import java.util.List;

/**
 * PizzaBuilder class - Builder Pattern implementation
 * Constructs CustomPizza objects using fluent interface
 * Provides method chaining for readable code
 */
public class PizzaBuilder {
    private String dough;
    private String sauce;
    private List<String> toppings;
    
    /**
     * Constructor - initializes empty topping list
     */
    public PizzaBuilder() {
        this.toppings = new ArrayList<>();
    }
    
    /**
     * Set the dough type
     * @param dough Type of dough (e.g., "Thin Crust", "Thick Crust")
     * @return this builder for method chaining
     */
    public PizzaBuilder setDough(String dough) {
        this.dough = dough;
        System.out.println("Builder: Dough set to " + dough);
        return this;
    }
    
    /**
     * Set the sauce type
     * @param sauce Type of sauce (e.g., "Tomato Sauce", "BBQ Sauce")
     * @return this builder for method chaining
     */
    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        System.out.println("Builder: Sauce set to " + sauce);
        return this;
    }
    
    /**
     * Add a topping to the pizza
     * Can be called multiple times
     * @param topping Topping to add (e.g., "Pepperoni", "Mushrooms")
     * @return this builder for method chaining
     */
    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        System.out.println("Builder: Added topping - " + topping);
        return this;
    }
    
    /**
     * Build the final CustomPizza object
     * Validates that required components are set
     * @return CustomPizza instance
     * @throws IllegalStateException if required fields not set
     */
    public CustomPizza build() {
        // Validate required fields
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
