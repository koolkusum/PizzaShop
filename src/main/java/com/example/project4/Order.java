/**
 * Order.java defines an instance of the Order object class with necessary
 * constructors, getters and setters.
 */
package com.example.project4;

import java.util.ArrayList;

public class Order
{
    private int orderNumber; //current order number, make them all 0 at first
    private ArrayList<Pizza> pizzas;

    /**
     * Constructor to create a new instance of an Order object with
     * an order number and an arraylist of pizzas in the order
     * @param num order number
     * @param pizzaList arraylist of pizzas
     */
    public Order (int num, ArrayList<Pizza> pizzaList)
    {
        this.orderNumber = num;
        this.pizzas = pizzaList;
    }

    /**
     * Public helper method to get total order price
     * without tax applied.
     * @return totalPrice
     */
    public double totalPrice()
    {
        double totalPrice = 0.0;
        for (Pizza pizza: getPizzas())
        {
            totalPrice+= pizza.price();
        }
        return (totalPrice);
    }

    /**
     * Getter method to get order number
     * @return orderNumber
     */
    public int getOrderNumber()
    {
        return orderNumber;
    }

    /**
     * Getter method to get pizzas in order
     * @return pizzas
     */
    public ArrayList <Pizza> getPizzas()
    {
        return this.pizzas;
    }

    /**
     * Setter method to set list of pizzas in order to passed in
     * arraylist of pizzas.
     * @param pizzaList new list of pizzas in order.
     */
    public void setPizzas(ArrayList<Pizza> pizzaList)
    {
        this.pizzas = pizzaList;
    }

}
