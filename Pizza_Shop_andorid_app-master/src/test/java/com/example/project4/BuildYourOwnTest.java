package com.example.project4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest
{
    @Test
    void priceMinToppingsSmallPizza()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        BuildYourOwn pizza = new BuildYourOwn(toppings, Size.SMALL, Sauce.TOMATO, false, false);
        double price = pizza.price();
        assert price == 8.99;
    }

    @Test
    void priceMaxToppingsSmallPizza()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.PINEAPPLE);
        toppings.add(Topping.BLACK_OLIVE);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.BEEF);
        BuildYourOwn pizza = new BuildYourOwn(toppings, Size.SMALL, Sauce.TOMATO, false, false);
        double price = pizza.price();
        assert price == (8.99 + (4*1.49));
    }

    @Test
    void priceMediumPizza()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        BuildYourOwn pizza = new BuildYourOwn(toppings, Size.MEDIUM, Sauce.TOMATO, false, false);
        double price = pizza.price();
        assert price == 10.99;
    }
    @Test
    void priceLargePizza()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        BuildYourOwn pizza = new BuildYourOwn(toppings, Size.LARGE, Sauce.TOMATO, false, false);
        double price = pizza.price();
        assert price == 12.99;
    }

    @Test
    void priceExtrasOnSmallPizza()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.ONION);
        toppings.add(Topping.GREEN_PEPPER);
        BuildYourOwn pizza = new BuildYourOwn(toppings, Size.SMALL, Sauce.TOMATO, true, true);
        double price = pizza.price();
        assert price == 10.99;
    }
}