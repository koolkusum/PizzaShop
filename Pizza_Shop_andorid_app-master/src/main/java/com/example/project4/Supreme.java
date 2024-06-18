/**
 * Supreme.java defines the Supreme subclass of Pizza
 */
package com.example.project4;

import java.util.ArrayList;
import java.util.Arrays;

public class Supreme extends Pizza
{
    /**
     * Constructor to create a new instance of a Supreme pizza object.
     * @param size size of pizza
     * @param extraCheese boolean for extra cheese or not
     * @param extraSauce boolean for extra sauce or not
     */
    public Supreme (Size size, boolean extraCheese, boolean extraSauce)
    {
        super (new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                        Topping.PEPPERONI,
                        Topping.HAM, Topping.GREEN_PEPPER, Topping.ONION,
                        Topping.BLACK_OLIVE, Topping.ONION)),
                size, Sauce.TOMATO);
        this.extraCheese = extraCheese;
        this.extraSauce = extraSauce;
    }

    /**
     * Implementation of abstract price method in pizza to
     * calculate the price of a Supreme pizza.
     * @return price
     */
    @Override
    public double price()
    {
        double price = 0;
        if (this.size == Size.SMALL)
        {
            price+= 15.99;
        }
        else if (this.size == Size.MEDIUM)
        {
            price+= 17.99;
        }
        else if (this.size == Size.LARGE)
        {
            price+= 19.99;
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
