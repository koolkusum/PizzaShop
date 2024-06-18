/**
 * Pizza.java defines the abstract Pizza super class along with
 * constructors and abstract methods to be implemented in child classes
 */
package com.example.project4;

import java.util.ArrayList;
public abstract class Pizza
{
    protected ArrayList<Topping> toppings; //Topping is a enum class
    protected Size size; //com.example.project4. is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Super constructor to make a new instance of a Pizza
     * @param pizzaToppings arraylist of toppings on pizza
     * @param size size of pizza
     * @param sauce sauce on pizza
     */
    public Pizza (ArrayList <Topping> pizzaToppings, Size size, Sauce sauce)
    {
        this.toppings = pizzaToppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    /**
     * Abstract method returning pizza price
     * @return pizza price as a double
     */
    public abstract double price(); //polymorphism

    /**
     * Getter method returns boolean for extra cheese on pizza or not
     * @return extraCheese
     */
    public boolean isExtraCheese() {
        return extraCheese;
    }

    /**
     * Setter method updates boolean value of extra cheese on pizza
     * @param extraCheese boolean
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Getter method returns boolean value for extra sauce on pizza or not.
     * @return extraSauce
     */
    public boolean isExtraSauce() {
        return extraSauce;
    }

    /**
     * Setter method to update boolean value of extra sauce on pizza or not
     * @param extraSauce
     */
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    /**
     * Getter method to get sauce type for pizza
     * @return sauce
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * Setter method to modify sauce type for pizza
     * @param sauce new sauce type to set
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Getter method to get the size of a pizza
     * @return size of pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Setter method to update the size of a pizza
     * @param size new size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Getter method to get list of toppings on pizza
     * @return toppings arraylist
     */
    public ArrayList<Topping> getToppings()
    {
        return toppings;
    }

    /**
     * Setter method to update list toppings on pizza
     * @param toppings list of toppings on pizza
     */
    public void setToppings(ArrayList<Topping> toppings)
    {
        this.toppings = toppings;
    }

    /**
     * toString method returns pizza type, toppings, sauce,
     * extras, and price as a string
     * @return String pizza
     */
    @Override
    public String toString()
    {
        StringBuilder pizzaString = new StringBuilder("[" + getClass().getSimpleName() + "] ");
        for (Topping topping: this.getToppings())
        {
            pizzaString.append(topping.toString()).append(", ");
        }
        pizzaString.append(this.size.toString()).append(", ");
        pizzaString.append("sauce: ").append(this.sauce.toString()).append(", ");
        if (this.extraSauce)
        {
            pizzaString.append("extra sauce, ");
        }
        if (this.extraCheese)
        {
            pizzaString.append("extra cheese, ");
        }
        pizzaString.append(String.format("$%.2f", this.price()));
        return pizzaString.toString();
    }
}
