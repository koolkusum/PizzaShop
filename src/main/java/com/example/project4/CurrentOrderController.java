/**
 * CurrentOrderController.java initializes FXML UI components in
 *  CurrentOrder.fxml and contains methods to place, update, and display current order.
 */
package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CurrentOrderController
{
    @FXML
    public TextField orderNum, subtotal, salesTax, orderTotal;

    private static final double tax = 0.0625;

    @FXML
    public ListView<String> pizzasInOrder;

    @FXML
    public Button remove, placeOrder;
    public ObservableList<String> pizzasToDisplay = FXCollections.observableArrayList();

    private OrderManager orderManager;

    /**
     * Initializes FXML UI components declared above, sets event handler methods,
     * and gets current instance of order manager singleton class to handle order operations.
     */
    public void initialize()
    {
        orderManager = OrderManager.getInstance();
        orderNum.setText(String.valueOf(orderManager.getCurrentOrder().getOrderNumber()));
        for (Pizza pizza: orderManager.getCurrentOrder().getPizzas())
        {
            pizzasToDisplay.add(pizza.toString());
        }
        pizzasInOrder.setItems(pizzasToDisplay);
        updateTotal();
    }

    /**
     * Removes a pizza from current order when a pizza is selected and
     * remove pizza button clicked.
     * @param event remove pizza button clicked
     */
    @FXML
    private void handleRemove(ActionEvent event)
    {
        String removeThis = pizzasInOrder.getSelectionModel().getSelectedItem();
       if (removeThis == null)
       {
           displayError("Select pizza to remove.");
       }
       for (Pizza pizza : orderManager.getCurrentOrder().getPizzas())
       {
           if(pizza.toString().equals(removeThis))
           {
               orderManager.removeFromOrder(pizza);
               pizzasToDisplay.remove(pizza.toString());
               break;
           }
       }
       pizzasInOrder.setItems(pizzasToDisplay);
       updateTotal();
    }

    /**
     * Places current order and adds it to list of store orders when
     * Place Order button clicked.
     * @param event place order button clicked
     */
    @FXML
    private void handlePlaceOrder(ActionEvent event)
    {
        if (pizzasToDisplay.isEmpty())
        {
            displayError("Cannot place empty order!");
            return;
        }
        orderManager.getStoreOrders().addToStoreOrders(orderManager.getCurrentOrder());
        orderManager.getStoreOrders().incrementNextOrderNum();
        Order nextOrder = new Order (orderManager.getStoreOrders().getNextAvailableOrderNum(),
                                        new ArrayList<Pizza>());
        orderManager.setCurrentOrder(nextOrder);
        orderNum.setText(String.valueOf(orderManager.getCurrentOrder().getOrderNumber()));
        pizzasToDisplay = FXCollections.observableArrayList();
        for (Pizza pizza: orderManager.getCurrentOrder().getPizzas())
        {
            pizzasToDisplay.add(pizza.toString());
        }
        pizzasInOrder.setItems(pizzasToDisplay);
        updateTotal();
        displayMessage("Current order placed!");
    }

    /**
     * Private helper method to update displayed total price of the order
     */
    private void updateTotal()
    {
        double sub = orderManager.getCurrentOrder().totalPrice();
        double taxes = tax*sub;
        double total = sub+taxes;
        subtotal.setText(String.format("$%.2f", sub));
        salesTax.setText(String.format("$%.2f", taxes));
        orderTotal.setText(String.format("$%.2f", total));
    }

    /**
     * Displays an error pop up to the user.
     * @param s, string to be displayed in the message pop up
     */
    public void displayError (String s)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }

    /**
     * Displays a message pop up to the user.
     * @param s, string to be displayed in the message pop up
     */
    public void displayMessage(String s)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }

}
