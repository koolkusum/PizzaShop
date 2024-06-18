/**
 * OrderManager.java is a data singleton class that shares current
 * order and store order information between controllers.
 */

package com.example.project4;

import java.util.ArrayList;
import java.util.Iterator;

public class OrderManager
{
    private Order currentOrder;
    private StoreOrders storeOrders = new StoreOrders(new ArrayList<Order>());

    private static OrderManager instance;

    /**
     * Constructor creates a new instance of current order based
     * on next available order number.
     */
    private OrderManager()
    {
        this.currentOrder = new Order(storeOrders.getNextAvailableOrderNum(), new ArrayList<>());
    }

    /**
     * Getter method returns current order as an Order object
     * @return current order
     */
    public Order getCurrentOrder()
    {
        return currentOrder;
    }

    /**
     * Setter method to update current order to a new Order object
     * @param newOrder new current order
     */
    public void setCurrentOrder(Order newOrder)
    {
        this.currentOrder = newOrder;
    }

    /**
     * Public helper method to get current instance of an OrderManager object
     * @return instance of OrderManager
     */
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Public helper method to add a Pizza object to current order
     * @param pizza to be added
     */
    public void addToOrder(Pizza pizza)
    {
        ArrayList<Pizza> list = this.getCurrentOrder().getPizzas();
        list.add(pizza);
        this.getCurrentOrder().setPizzas(list);
    }

    /**
     * Public helper method to remove a Pizza object from current order
     * @param pizza to be removed
     */
    public void removeFromOrder(Pizza pizza)
    {
        ArrayList<Pizza> list = currentOrder.getPizzas();
        list.remove(pizza);
        currentOrder.setPizzas(list);
    }

    /**
     * Public helper method to remove an Order object from storeOrders
     * @param order to be removed
     */
    public void removeFromStoreOrder(Order order)
    {
        ArrayList<Order> list = storeOrders.getUserOrders();
        Iterator<Order> iterator = list.iterator();

        while (iterator.hasNext()) {
            Order currentOrder = iterator.next();
            if (currentOrder.equals(order)) {
                iterator.remove();
            }
        }
        storeOrders.setUserOrders(list);
    }

    /**
     * Getter method returns StoreOrders object
     * @return storeOrders
     */
    public StoreOrders getStoreOrders()
    {
        return storeOrders;
    }
}
