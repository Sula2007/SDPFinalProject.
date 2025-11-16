// ============================================================
// FACTORY PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Factory Pattern in one file
// ============================================================

// ------------------------------------------------------------
// Meal.java - Interface for all meal types
// ------------------------------------------------------------
interface Meal {
    // Prepare the meal
    void prepare();
    
    // Get the price of the meal
    double getPrice();
    
    // Get the description of the meal
    String getDescription();
    
    // Get cooking time in minutes
    int getCookingTime();
}

// ------------------------------------------------------------
// Pizza.java - Concrete meal implementation
// ------------------------------------------------------------
class Pizza implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    // Constructor - initializes pizza with default values
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

// ------------------------------------------------------------
// Burger.java - Concrete meal implementation
// ------------------------------------------------------------
class Burger implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    // Constructor - initializes burger with default values
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

// ------------------------------------------------------------
// Salad.java - Concrete meal implementation
// ------------------------------------------------------------
class Salad implements Meal {
    private String name;
    private double price;
    private int cookingTime;
    
    // Constructor - initializes salad with default values
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

// ------------------------------------------------------------
// MealFactory.java - Factory class that creates meals
// This is the core of the Factory Pattern
// ------------------------------------------------------------
class MealFactory {
    
    // Static factory method - creates different meal types based on input
    // Takes a string parameter to determine which meal to create
    // Returns a Meal interface object (can be Pizza, Burger, or Salad)
    public static Meal createMeal(String mealType) {
        
        // Validate input - check if mealType is null or empty
        if (mealType == null || mealType.isEmpty()) {
            throw new IllegalArgumentException("Meal type cannot be null or empty");
        }
        
        // Switch statement to determine which meal to create
        // Based on the input string, return appropriate meal object
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
                // If meal type is unknown, throw exception with helpful message
                throw new IllegalArgumentException("Unknown meal type: " + mealType + 
                    ". Available types: Pizza, Burger, Salad");
        }
    }
    
    // Optional helper method - returns list of available meal types
    public static String[] getAvailableMeals() {
        return new String[]{"Pizza", "Burger", "Salad"};
    }
}

// ------------------------------------------------------------
// Demo class to test Factory Pattern
// ------------------------------------------------------------
class FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("FACTORY PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Create Pizza using factory
        System.out.println("Creating Pizza:");
        Meal pizza = MealFactory.createMeal("Pizza");
        pizza.prepare();
        System.out.println("Price: $" + pizza.getPrice());
        System.out.println("Cooking time: " + pizza.getCookingTime() + " minutes");
        System.out.println();
        
        // Create Burger using factory
        System.out.println("Creating Burger:");
        Meal burger = MealFactory.createMeal("Burger");
        burger.prepare();
        System.out.println("Price: $" + burger.getPrice());
        System.out.println("Cooking time: " + burger.getCookingTime() + " minutes");
        System.out.println();
        
        // Create Salad using factory
        System.out.println("Creating Salad:");
        Meal salad = MealFactory.createMeal("Salad");
        salad.prepare();
        System.out.println("Price: $" + salad.getPrice());
        System.out.println("Cooking time: " + salad.getCookingTime() + " minutes");
        System.out.println();
        
        System.out.println("=".repeat(60));
        System.out.println("Factory Pattern successfully created 3 different meal types!");
        System.out.println("=".repeat(60));
    }
}
