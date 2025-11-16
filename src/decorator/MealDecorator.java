// ============================================================
// FILE: src/decorator/MealDecorator.java
// ============================================================
package decorator;

import factory.Meal;

/**
 * MealDecorator - Abstract base class for Decorator Pattern
 * Wraps a Meal object and delegates all operations to it
 * Concrete decorators extend this to add functionality
 */
public abstract class MealDecorator implements Meal {
    protected Meal wrappedMeal;
    
    /**
     * Constructor - takes a Meal object to wrap
     * @param meal The meal to be decorated
     * @throws IllegalArgumentException if meal is null
     */
    public MealDecorator(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }
        this.wrappedMeal = meal;
    }
    
    // Default implementations delegate to wrapped meal
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

// ============================================================
// FILE: src/decorator/CheeseDecorator.java
// ============================================================
package decorator;

import factory.Meal;

/**
 * CheeseDecorator - Concrete decorator that adds cheese to a meal
 * Adds $1.50 to price and 1 minute to cooking time
 */
public class CheeseDecorator extends MealDecorator {
    private static final double CHEESE_PRICE = 1.50;
    
    /**
     * Constructor - wraps a meal with cheese decoration
     * @param meal The meal to decorate
     */
    public CheeseDecorator(Meal meal) {
        super(meal);
        System.out.println("Decorator: Adding Cheese (+$" + CHEESE_PRICE + ")");
    }
    
    @Override
    public void prepare() {
        wrappedMeal.prepare();
        System.out.println("Adding extra cheese topping...");
    }
    
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + CHEESE_PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Extra Cheese";
    }
    
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime() + 1; // Extra minute for melting cheese
    }
}

// ============================================================
// FILE: src/decorator/BaconDecorator.java
// ============================================================
package decorator;

import factory.Meal;

/**
 * BaconDecorator - Concrete decorator that adds bacon to a meal
 * Adds $2.50 to price and 2 minutes to cooking time
 */
public class BaconDecorator extends MealDecorator {
    private static final double BACON_PRICE = 2.50;
    
    /**
     * Constructor - wraps a meal with bacon decoration
     * @param meal The meal to decorate
     */
    public BaconDecorator(Meal meal) {
        super(meal);
        System.out.println("Decorator: Adding Bacon (+$" + BACON_PRICE + ")");
    }
    
    @Override
    public void prepare() {
        wrappedMeal.prepare();
        System.out.println("Adding crispy bacon strips...");
    }
    
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + BACON_PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Bacon";
    }
    
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime() + 2; // Extra time for cooking bacon
    }
}

// ============================================================
// FILE: src/decorator/SauceDecorator.java
// ============================================================
package decorator;

import factory.Meal;

/**
 * SauceDecorator - Concrete decorator that adds special sauce to a meal
 * Adds $1.00 to price, no cooking time change (added after cooking)
 */
public class SauceDecorator extends MealDecorator {
    private static final double SAUCE_PRICE = 1.00;
    
    /**
     * Constructor - wraps a meal with sauce decoration
     * @param meal The meal to decorate
     */
    public SauceDecorator(Meal meal) {
        super(meal);
        System.out.println("Decorator: Adding Special Sauce (+$" + SAUCE_PRICE + ")");
    }
    
    @Override
    public void prepare() {
        wrappedMeal.prepare();
        System.out.println("Drizzling special house sauce...");
    }
    
    @Override
    public double getPrice() {
        return wrappedMeal.getPrice() + SAUCE_PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedMeal.getDescription() + ", with Special Sauce";
    }
    
    @Override
    public int getCookingTime() {
        return wrappedMeal.getCookingTime(); // No extra time, sauce added after cooking
    }
}
