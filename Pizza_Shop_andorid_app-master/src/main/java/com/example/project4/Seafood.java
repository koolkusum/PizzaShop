/**
 * Seafood.java defines the Seafood subclass of Pizza
 */
package com.example.project4;

import java.util.ArrayList;
import java.util.Arrays;

public class Seafood extends Pizza
{
    /**
     * Constructor to create a new instance of a Seafood pizza object.
     * @param size size of pizza
     * @param extraCheese boolean for extra cheese or not
     * @param extraSauce boolean for extra sauce or not
     */
    public Seafood(Size size, boolean extraCheese, boolean extraSauce)
    {
        super (new ArrayList<>(Arrays.asList(Topping.SQUID, Topping.SHRIMP,
                Topping.CRAB_MEATS)), size, Sauce.ALFREDO);
        this.extraCheese = extraCheese;
        this.extraSauce = extraSauce;
    }

    /**
     * Implementation of abstract price method in pizza to
     * calculate the price of a Seafood pizza.
     * @return price
     */
    @Override
    public double price()
    {
        double price = 0;
        if (this.size == Size.SMALL)
        {
            price+= 17.99;
        }
        else if (this.size == Size.MEDIUM)
        {
            price+= 19.99;
        }
        else if (this.size == Size.LARGE)
        {
            price+= 21.99;
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
