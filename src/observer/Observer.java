// ============================================================
// FILE: src/observer/Observer.java
// ============================================================
package observer;

/**
 * Observer interface - Observer Pattern
 * Defines contract for objects that want to be notified of order changes
 */
public interface Observer {
    /**
     * Called when the subject's state changes
     * @param orderStatus New order status
     * @param orderId ID of the order
     */
    void update(String orderStatus, int orderId);
    
    /**
     * Get the name of this observer
     * @return Observer name
     */
    String getObserverName();
}

// ============================================================
// FILE: src/observer/Order.java
// ============================================================
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Order class - Subject in Observer Pattern
 * Maintains list of observers and notifies them of state changes
 */
public class Order {
    private int orderId;
    private String customerName;
    private List<String> items;
    private String status;
    private List<Observer> observers;
    
    /**
     * Constructor - initializes order with ID and customer name
     * @param orderId Unique order ID
     * @param customerName Name of customer
     */
    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = "Created";
        this.observers = new ArrayList<>();
        System.out.println("Order #" + orderId + " created for " + customerName);
    }
    
    /**
     * Add an item to the order
     * @param item Item description
     */
    public void addItem(String item) {
        items.add(item);
        System.out.println("Item added to order: " + item);
    }
    
    /**
     * Attach an observer to this order
     * Observer will be notified of all status changes
     * @param observer Observer to attach
     */
    public void attach(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached: " + observer.getObserverName());
        }
    }
    
    /**
     * Detach an observer from this order
     * Observer will no longer receive notifications
     * @param observer Observer to detach
     */
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer detached: " + observer.getObserverName());
        }
    }
    
    /**
     * Notify all observers about status change
     * This is the key method of Observer Pattern
     */
    private void notifyObservers() {
        System.out.println("\n" + "~".repeat(60));
        System.out.println("NOTIFYING ALL OBSERVERS - Order #" + orderId + 
                          " status: " + status);
        System.out.println("~".repeat(60));
        
        for (Observer observer : observers) {
            observer.update(status, orderId);
        }
        
        System.out.println("~".repeat(60) + "\n");
    }
    
    /**
     * Change order status and notify all observers
     * @param newStatus New order status
     */
    public void setStatus(String newStatus) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Order #" + orderId + " status changing: " + 
                          status + " → " + newStatus);
        System.out.println("=".repeat(60));
        
        this.status = newStatus;
        notifyObservers();
    }
    
    // Getters
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public List<String> getItems() { return new ArrayList<>(items); }
    
    /**
     * Display order details
     */
    public void displayOrder() {
        System.out.println("\nOrder #" + orderId + " Details:");
        System.out.println("Customer: " + customerName);
        System.out.println("Items: " + String.join(", ", items));
        System.out.println("Status: " + status);
    }
}

// ============================================================
// FILE: src/observer/Kitchen.java
// ============================================================
package observer;

/**
 * Kitchen - Concrete Observer
 * Receives notifications about orders that need to be prepared
 */
public class Kitchen implements Observer {
    private String kitchenName;
    
    /**
     * Constructor
     * @param kitchenName Name of the kitchen
     */
    public Kitchen(String kitchenName) {
        this.kitchenName = kitchenName;
    }
    
    @Override
    public void update(String orderStatus, int orderId) {
        System.out.println("[KITCHEN - " + kitchenName + "] Received notification:");
        
        switch (orderStatus) {
            case "Confirmed":
                System.out.println("  → Order #" + orderId + 
                                 " confirmed. Adding to cooking queue...");
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

// ============================================================
// FILE: src/observer/Customer.java
// ============================================================
package observer;

/**
 * Customer - Concrete Observer
 * Receives notifications about their order status
 */
public class Customer implements Observer {
    private String customerName;
    private String phoneNumber;
    
    /**
     * Constructor
     * @param customerName Customer name
     * @param phoneNumber Customer phone number
     */
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

// ============================================================
// FILE: src/observer/Waiter.java
// ============================================================
package observer;

/**
 * Waiter - Concrete Observer
 * Receives notifications about orders to coordinate service
 */
public class Waiter implements Observer {
    private String waiterName;
    private int tableNumber;
    
    /**
     * Constructor
     * @param waiterName Waiter name
     * @param tableNumber Table number being served
     */
    public Waiter(String waiterName, int tableNumber) {
        this.waiterName = waiterName;
        this.tableNumber = tableNumber;
    }
    
    @Override
    public void update(String orderStatus, int orderId) {
        System.out.println("[WAITER - " + waiterName + " | Table " + 
                          tableNumber + "] Alert:");
        
        switch (orderStatus) {
            case "Confirmed":
                System.out.println("  → Order #" + orderId + " confirmed for Table " + 
                                 tableNumber);
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
                System.out.println("  → Order #" + orderId + " delivered to Table " + 
                                 tableNumber);
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
