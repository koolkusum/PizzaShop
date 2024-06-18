/**
 * Deluxe.java defines the Deluxe subclass of Pizza
 */

package com.example.project4;

import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza
{
    /**
     * Constructor to create a new instance of a Deluxe pizza object.
     * @param size size of pizza
     * @param extraCheese boolean for extra cheese or not
     * @param extraSauce boolean for extra sauce or not
     */
    public Deluxe (Size size, boolean extraCheese, boolean extraSauce)
    {
        super(new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION,
                Topping.BLACK_OLIVE, Topping.MUSHROOM)), size, Sauce.TOMATO);
        this.extraCheese = extraCheese;
        this.extraSauce = extraSauce;

    }

    /**
     * Implementation of abstract price method in pizza to
     * calculate the price of a Deluxe pizza.
     * @return price
     */
    @Override
    public double price()
    {
        double price = 0;
        if (this.size == Size.SMALL)
        {
            price+= 14.99;
        }
        else if (this.size == Size.MEDIUM)
        {
            price+= 16.99;
        }
        else if (this.size == Size.LARGE)
        {
            price+= 18.99;
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
