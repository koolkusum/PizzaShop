/**
 * StoreOrders.java defines the StoreOrders class which is used to track
 * user orders. It includes constructors, getters, setters, and other methods
 * to modify StoreOrders by adding or removing Order objects and incrementing order numbers.
 */
package com.example.project4;

import java.util.ArrayList;

public class StoreOrders
{

    private ArrayList<Order> userOrders;
    private int nextAvailableOrderNum = 0;

    /**
     * Getter method that returns an ArrayList of placed user orders for the store.
     * @return userOrders
     */
    public ArrayList<Order> getUserOrders() {
        return userOrders;
    }

    /**
     * Setter method to set userOrders attribute of a storeOrders object to
     * passed in ArrayList of orders.
     * @param userOrders new list of order objects
     */
    public void setUserOrders(ArrayList<Order> userOrders) {
        this.userOrders = userOrders;
    }

    /**
     * Constructor to create a new instance of a StoreOrders object
     * @param orders arraylist of placed orders for the store
     */
    public StoreOrders(ArrayList<Order> orders)
    {
        this.userOrders = orders;
        nextAvailableOrderNum++;
    }

    /**
     * Public helper method to add an Order object to the arraylist
     * attribute of a StoreOrders object.
     * @param order
     */
    public void addToStoreOrders(Order order)
    {
        ArrayList<Order> orders = getUserOrders();
        orders.add(order);
        setUserOrders(orders);
    }

    /**
     * Public helper method that returns unique next available order number
     * to track orders with
     * @return nextAvailableOrderNum
     */
    public int getNextAvailableOrderNum()
    {
        return this.nextAvailableOrderNum;
    }

    /**
     * Public helper method to update next available unique order
     * number whenever a current order is placed
     */
    public void incrementNextOrderNum()
    {
        this.nextAvailableOrderNum+= 1;
    }
}
