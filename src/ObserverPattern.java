// ============================================================
// OBSERVER PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Observer Pattern in one file
// ============================================================

import java.util.ArrayList;
import java.util.List;

// ------------------------------------------------------------
// Observer.java - Interface for all observers
// This is the core of Observer Pattern
// ------------------------------------------------------------
interface Observer {
    // Called when the subject's state changes
    // Receives the order status update message
    void update(String orderStatus, int orderId);
    
    // Get the name of this observer
    String getObserverName();
}

// ------------------------------------------------------------
// Order.java - Subject class (Observable)
// Maintains list of observers and notifies them of state changes
// ------------------------------------------------------------
class Order {
    private int orderId;
    private String customerName;
    private List<String> items;
    private String status;
    private List<Observer> observers;
    
    // Constructor - initializes order with ID and customer name
    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = "Created";
        this.observers = new ArrayList<>();
        System.out.println("Order #" + orderId + " created for " + customerName);
    }
    
    // Add an item to the order
    public void addItem(String item) {
        items.add(item);
        System.out.println("Item added to order: " + item);
    }
    
    // Attach an observer to this order
    // Observer will be notified of all status changes
    public void attach(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached: " + observer.getObserverName());
        }
    }
    
    // Detach an observer from this order
    // Observer will no longer receive notifications
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer detached: " + observer.getObserverName());
        }
    }
    
    // Notify all observers about status change
    // This is the key method of Observer Pattern
    private void notifyObservers() {
        System.out.println("\n" + "~".repeat(60));
        System.out.println("NOTIFYING ALL OBSERVERS - Order #" + orderId + " status: " + status);
        System.out.println("~".repeat(60));
        
        for (Observer observer : observers) {
            observer.update(status, orderId);
        }
        
        System.out.println("~".repeat(60) + "\n");
    }
    
    // Change order status and notify all observers
    public void setStatus(String newStatus) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Order #" + orderId + " status changing: " + status + " → " + newStatus);
        System.out.println("=".repeat(60));
        
        this.status = newStatus;
        notifyObservers(); // Automatically notify all observers
    }
    
    // Getters
    public int getOrderId() {
        return orderId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public List<String> getItems() {
        return new ArrayList<>(items); // Return defensive copy
    }
    
    // Display order details
    public void displayOrder() {
        System.out.println("\nOrder #" + orderId + " Details:");
        System.out.println("Customer: " + customerName);
        System.out.println("Items: " + String.join(", ", items));
        System.out.println("Status: " + status);
    }
}

// ------------------------------------------------------------
// Kitchen.java - Concrete Observer #1
// Receives notifications about orders that need to be prepared
// ------------------------------------------------------------
class Kitchen implements Observer {
    private String kitchenName;
    
    // Constructor
    public Kitchen(String kitchenName) {
        this.kitchenName = kitchenName;
    }
    
    @Override
    public void update(String orderStatus, int orderId) {
        System.out.println("[KITCHEN - " + kitchenName + "] Received notification:");
        
        switch (orderStatus) {
            case "Confirmed":
                System.out.println("  → Order #" + orderId + " confirmed. Adding to cooking queue...");
                System.out.println("  → Preparing ingredients for order #" + orderId);
                break;
            case "Preparing":
                System.out.println("  → Order #" + orderId + " is now being prepared");
                System.out.println("  → Chefs are cooking the meals...");
                break;
            case "Ready":
                System.out.println("  → Order #" + orderId + " is ready for pickup!");
                System.out.println("  → Moving order to pickup counter...");
                break;
            case "Delivered":
                System.out.println("  → Order #" + orderId + " has been delivered");
                System.out.println("  → Kitchen can clear this order from queue");
                break;
            default:
                System.out.println("  → Order #" + orderId + " status: " + orderStatus);
        }
    }
    
    @Override
    public String getObserverName() {
        return "Kitchen (" + kitchenName + ")";
    }
}

// ------------------------------------------------------------
// Customer.java - Concrete Observer #2
// Receives notifications about their order status
// ------------------------------------------------------------
class Customer implements Observer {
    private String customerName;
    private String phoneNumber;
    
    // Constructor
    public Customer(String customerName, String phoneNumber) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void update(String orderStatus, int orderId) {
        System.out.println("[CUSTOMER - " + customerName + "] Notification received:");
        
        switch (orderStatus) {
            case "Confirmed":
                System.out.println("  → Your order #" + orderId + " has been confirmed!");
                System.out.println("  → We'll notify you when it's ready");
                System.out.println("  → SMS sent to: " + phoneNumber);
                break;
            case "Preparing":
                System.out.println("  → Your order #" + orderId + " is being prepared");
                System.out.println("  → Estimated time: 15-20 minutes");
                break;
            case "Ready":
                System.out.println("  → Great news! Your order #" + orderId + " is ready!");
                System.out.println("  → Please come to pickup counter");
                System.out.println("  → SMS sent to: " + phoneNumber);
                break;
            case "Delivered":
                System.out.println("  → Order #" + orderId + " delivered. Enjoy your meal!");
                System.out.println("  → Thank you for choosing our restaurant!");
                System.out.println("  → Please rate your experience");
                break;
            default:
                System.out.println("  → Order #" + orderId + " update: " + orderStatus);
        }
    }
    
    @Override
    public String getObserverName() {
        return "Customer (" + customerName + ")";
    }
}

// ------------------------------------------------------------
// Waiter.java - Concrete Observer #3
// Receives notifications about orders to coordinate service
// ------------------------------------------------------------
class Waiter implements Observer {
    private String waiterName;
    private int tableNumber;
    
    // Constructor
    public Waiter(String waiterName, int tableNumber) {
        this.waiterName = waiterName;
        this.tableNumber = tableNumber;
    }
    
    @Override
    public void update(String orderStatus, int orderId) {
        System.out.println("[WAITER - " + waiterName + " | Table " + tableNumber + "] Alert:");
        
        switch (orderStatus) {
            case "Confirmed":
                System.out.println("  → Order #" + orderId + " confirmed for Table " + tableNumber);
                System.out.println("  → Informing customer about wait time...");
                break;
            case "Preparing":
                System.out.println("  → Order #" + orderId + " is being prepared in kitchen");
                System.out.println("  → Will check on progress shortly...");
                break;
            case "Ready":
                System.out.println("  → Order #" + orderId + " is ready at pickup counter!");
                System.out.println("  → Picking up order to serve at Table " + tableNumber);
                break;
            case "Delivered":
                System.out.println("  → Order #" + orderId + " delivered to Table " + tableNumber);
                System.out.println("  → Checking if customer needs anything else...");
                break;
            default:
                System.out.println("  → Order #" + orderId + " status: " + orderStatus);
        }
    }
    
    @Override
    public String getObserverName() {
        return "Waiter (" + waiterName + " - Table " + tableNumber + ")";
    }
}

// ------------------------------------------------------------
// Demo class to test Observer Pattern
// ------------------------------------------------------------
class ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("OBSERVER PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Create an order
        System.out.println("Step 1: Creating order");
        System.out.println("-".repeat(60));
        Order order = new Order(1001, "Alice Johnson");
        order.addItem("Margherita Pizza");
        order.addItem("Caesar Salad");
        order.addItem("Coca Cola");
        order.displayOrder();
        System.out.println();
        
        // Create observers
        System.out.println("\nStep 2: Setting up observers");
        System.out.println("-".repeat(60));
        Observer kitchen = new Kitchen("Main Kitchen");
        Observer customer = new Customer("Alice Johnson", "+1-555-0123");
        Observer waiter = new Waiter("Bob Smith", 5);
        System.out.println();
        
        // Attach observers to the order
        System.out.println("\nStep 3: Attaching observers to order");
        System.out.println("-".repeat(60));
        order.attach(kitchen);
        order.attach(customer);
        order.attach(waiter);
        System.out.println();
        
        // Simulate order status changes
        // Each status change will notify all observers
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULATING ORDER WORKFLOW");
        System.out.println("=".repeat(60));
        
        // Status 1: Order confirmed
        order.setStatus("Confirmed");
        
        try {
            Thread.sleep(1000); // Pause for readability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Status 2: Order being prepared
        order.setStatus("Preparing");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Status 3: Order ready
        order.setStatus("Ready");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Status 4: Order delivered
        order.setStatus("Delivered");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Observer Pattern successfully demonstrated!");
        System.out.println("All 3 observers were automatically notified of each status change.");
        System.out.println("=".repeat(60));
    }
}
