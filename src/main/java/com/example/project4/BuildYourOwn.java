/**
 * BuildYourOwn.java defines the BuildYourOwn subclass of Pizza
 */

package com.example.project4;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza
{
    /**
     * Constructor to create a new instance of a BuildYourOwn Pizza object.
     * @param toppings list of customized, user-chosen toppings
     * @param size size of pizza
     * @param sauce chosen sauce
     * @param extraSauce boolean for extra sauce or not
     * @param extraCheese boolean for extra cheese or not
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Size size, Sauce sauce,
                        boolean extraSauce, boolean extraCheese)
    {
        super(toppings, size, sauce);
        this.extraCheese = extraCheese;
        this.extraSauce = extraSauce;
    }

    /**
     * Implementation of abstract price method in pizza to
     * calculate the price of a buildYourOwn pizza.
     * @return price
     */
    @Override
    public double price()
    {
        double price = 0.0;
        if (this.toppings.size() < 3)
        {
            return 0.0;
        }
        if (this.size == Size.SMALL)
        {
            price+= 8.99;
        }
        else if (this.size == Size.MEDIUM)
        {
            price+= 10.99;
        }
        else if (this.size == Size.LARGE)
        {
            price+= 12.99;
        }
        if (this.extraCheese)
        {
            price+=1;
        }
        if (this.extraSauce)
        {
            price+=1;
        }
        if (this.toppings.size() > 3)
        {
            price += ((this.toppings.size()-3) * 1.49);
        }
        return price;
    }
}
