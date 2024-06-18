/**
 * Pepperoni.java defines the Pepperoni subclass of Pizza
 */

package com.example.project4;

import java.util.ArrayList;
import java.util.Arrays;

public class Pepperoni extends Pizza
{
    /**
     * Constructor to create a new instance of a Pepperoni pizza object.
     * @param size size of pizza
     * @param extraCheese boolean for extra cheese or not
     * @param extraSauce boolean for extra sauce or not
     */
    public Pepperoni (Size size, boolean extraCheese, boolean extraSauce)
    {
        super (new ArrayList<>(Arrays.asList(Topping.PEPPERONI)), size, Sauce.TOMATO);
        this.extraCheese = extraCheese;
        this.extraSauce = extraSauce;
    }

    /**
     * Implementation of abstract price method in pizza to
     * calculate the price of a Pepperoni pizza.
     * @return price
     */
    @Override
    public double price()
    {
        double price = 0;
        if (this.size == Size.SMALL)
        {
            price+= 10.99;
        }
        else if (this.size == Size.MEDIUM)
        {
            price+= 12.99;
        }
        else if (this.size == Size.LARGE)
        {
            price+= 14.99;
        }
        if (this.extraCheese)
        {
            price+=1;
        }
        if (this.extraSauce)
        {
            price+=1;
        }
        return price;
    }
}
