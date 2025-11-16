// ============================================================
// FILE: src/factory/Meal.java
// ============================================================
package factory;

/**
 * Meal interface - Product abstraction for Factory Pattern
 * Defines contract for all meal types in the restaurant system
 */
public interface Meal {
    void prepare();
    double getPrice();
    String getDescription();
    int getCookingTime();
}

// ============================================================
// FILE: src/factory/Pizza.java
// ============================================================
package factory;

/**
 * Pizza class - Concrete product implementation
 * Represents a Margherita Pizza in the system
 */
public class Pizza implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    public Pizza() {
        this.name = "Margherita Pizza";
        this.price = 12.99;
        this.cookingTime = 15;
    }
    
    @Override
    public void prepare() {
        System.out.println("Preparing Pizza...");
        System.out.println("Adding tomato sauce and mozzarella cheese");
        System.out.println("Baking in wood-fired oven at 450°F");
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

// ============================================================
// FILE: src/factory/Burger.java
// ============================================================
package factory;

/**
 * Burger class - Concrete product implementation
 * Represents a Classic Cheeseburger in the system
 */
public class Burger implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    public Burger() {
        this.name = "Classic Cheeseburger";
        this.price = 8.99;
        this.cookingTime = 10;
    }
    
    @Override
    public void prepare() {
        System.out.println("Preparing Burger...");
        System.out.println("Grilling beef patty to perfection");
        System.out.println("Adding lettuce, tomato, cheese, and special sauce");
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

// ============================================================
// FILE: src/factory/Salad.java
// ============================================================
package factory;

/**
 * Salad class - Concrete product implementation
 * Represents a Caesar Salad in the system
 */
public class Salad implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    public Salad() {
        this.name = "Caesar Salad";
        this.price = 6.99;
        this.cookingTime = 5;
    }
    
    @Override
    public void prepare() {
        System.out.println("Preparing Salad...");
        System.out.println("Washing fresh romaine lettuce");
        System.out.println("Adding croutons, parmesan cheese, and Caesar dressing");
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

// ============================================================
// FILE: src/factory/MealFactory.java
// ============================================================
package factory;

/**
 * MealFactory class - Factory Pattern implementation
 * Creates different meal objects based on string input
 * Encapsulates object creation logic
 */
public class MealFactory {
    
    /**
     * Factory method to create meals
     * @param mealType Type of meal to create (pizza, burger, salad)
     * @return Meal object
     * @throws IllegalArgumentException if meal type is unknown
     */
    public static Meal createMeal(String mealType) {
        if (mealType == null || mealType.isEmpty()) {
            throw new IllegalArgumentException("Meal type cannot be null or empty");
        }
        
        switch (mealType.toLowerCase()) {
            case "pizza":
                System.out.println("Factory: Creating Pizza...");
                return new Pizza();
                
            case "burger":
                System.out.println("Factory: Creating Burger...");
                return new Burger();
                
            case "salad":
                System.out.println("Factory: Creating Salad...");
                return new Salad();
                
            default:
                throw new IllegalArgumentException("Unknown meal type: " + mealType + 
                    ". Available types: Pizza, Burger, Salad");
        }
    }
    
    /**
     * Helper method to get available meal types
     * @return Array of available meal type names
     */
    public static String[] getAvailableMeals() {
        return new String[]{"Pizza", "Burger", "Salad"};
    }
}
