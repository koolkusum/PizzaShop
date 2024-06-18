/**
 * PizzaMaker.java is a factory method class that is called
 * in Controller classes to make new instances of Pizza objects.
 */
package com.example.project4;

import java.util.ArrayList;

public class PizzaMaker
{
    /**
     * Public method to create new instances of Pizza objects based on
     * type of pizza passed in as a string parameter
     * @param pizzaType type of Pizza as a String
     * @return new instance of a Pizza
     */
    public static Pizza createPizza(String pizzaType) //use this to make default pizzas and then getters/setters to change
    {
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe(Size.SMALL, false, false);
            case "Supreme" -> new Supreme(Size.SMALL, false, false);
            case "Meatzza" -> new Meatzza(Size.SMALL, false, false);
            case "Seafood" -> new Seafood(Size.SMALL, false, false);
            case "Pepperoni" -> new Pepperoni(Size.SMALL, false, false);
            case "Build Your Own" -> new BuildYourOwn(new ArrayList<Topping>(), Size.SMALL, Sauce.TOMATO, false, false);
            default -> null;
        };
    }
}
