/**
 * StoreOrdersController.java initializes FXML UI components in
 *  StoreOrders.fxml and contains methods to cancel, update, and export store orders.
 */
package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class StoreOrdersController
{
    private static final double tax = 0.0625;

    @FXML
    public ChoiceBox<Integer> orderNums;

    public ObservableList<Integer> numbers = FXCollections.observableArrayList();
    public ObservableList <String> orderPizzas = FXCollections.observableArrayList();

    @FXML
    public ListView<String> storeOrders;

    @FXML
    public TextField total;
    @FXML
    public Button cancelOrder, exportOrders;
    private OrderManager orderManager;


    /**
     * Initializes FXML UI components declared above, sets event handler methods,
     * and gets current instance of order manager singleton class to handle order operations.
     */
    public void initialize()
    {
        orderManager = OrderManager.getInstance();
        setOrderNumbers(); //for drop down choice box
        total.setText(String.format("$%.2f", 0.0));
        //list view will be empty until order number chosen
    }

    /**
     * Private helper method to set order number choices and add
     * them to choice box ui component.
     */
    private void setOrderNumbers()
    {
        numbers = FXCollections.observableArrayList();
        for (Order order : orderManager.getStoreOrders().getUserOrders())
        {
            numbers.add(order.getOrderNumber());
        }
        orderNums.setItems(numbers);
    }

    /**
     * Changes order displayed when different order number
     * selected from choice box.
     * @param event choice box selection
     */
    @FXML
    private void changeOrder(ActionEvent event)
    {
        Order temp = null;
        if (orderNums.getValue() != null)
        {
            for (Order order : orderManager.getStoreOrders().getUserOrders())
            {
                if (order.getOrderNumber() == orderNums.getValue())
                {
                    temp = order;
                    break;
                }
            }
            orderPizzas = FXCollections.observableArrayList();
            for (Pizza pizza : temp.getPizzas())
            {
                orderPizzas.add(pizza.toString());
            }
            storeOrders.setItems(orderPizzas);
            updateTotal();
        }
        else
        {
            storeOrders.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Cancels an order when cancel order button clicked.
     * @param event cancel order button clicked.
     */
    @FXML
    private void handleCancelOrder(ActionEvent event)
    {
        if (orderNums.getValue() == null)
        {
            displayError("Select order to cancel.");
        }
        else
        {
            Order toRemove = null;
            for (Order order : orderManager.getStoreOrders().getUserOrders())
            {
                System.out.println(order.getOrderNumber());
                if (order.getOrderNumber() == orderNums.getValue())
                {
                    orderManager.removeFromStoreOrder(order);
                    setOrderNumbers();
                    orderNums.setValue(null);
                    changeOrder(null);
                    total.setText(String.format("$%.2f", 0.0));
                    break;
                }
            }
            displayMessage("Order cancelled.");
        }
    }

    /**
     * Exports store orders to a text file when export store orders
     * button selected
     * @param event export store orders button click
     * @throws FileNotFoundException
     */
    @FXML
    private void exportOrders(ActionEvent event) throws FileNotFoundException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save orders to text file");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = (Stage) exportOrders.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null)
        {
            try (FileWriter fileWriter = new FileWriter(file))
            {
                for (Order order : orderManager.getStoreOrders().getUserOrders())
                {
                    fileWriter.write("Order " + order.getOrderNumber() + ": \n");
                    for (Pizza pizza: order.getPizzas())
                    {
                        fileWriter.write(pizza.toString() + "\n");
                    }
                }
                displayMessage("Orders exported successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper method updates displayed order total when order number changes.
     */
    private void updateTotal()
    {
        double totalPrice = 0.0;

        Order toPrice = null;
        if (orderNums.getValue() == null)
        {
            total.setText(String.format("$%.2f", 0.0));
            return;
        }
        for (Order order : orderManager.getStoreOrders().getUserOrders())
        {
            if (order.getOrderNumber() == orderNums.getValue())
            {
                toPrice = order;
            }
        }
        totalPrice += toPrice.totalPrice() + (toPrice.totalPrice() * tax);
        total.setText(String.format("$%.2f", totalPrice));
    }


    /**
     * Displays an error pop up to the user.
     * @param s, string to be displayed in the error pop up
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
