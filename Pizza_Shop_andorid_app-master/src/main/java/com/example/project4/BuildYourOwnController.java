/**
 * BuildYourOwnController.java initializes FXML UI components in
 *  BuildYourOwn.fxml and contains methods to customize toppings, size, and sauce
 *  for pizza based on user action.
 */
package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BuildYourOwnController
{
    public RadioButton small, medium, large, tomato, alfredo;

    public ToggleGroup sauce;

    public ToggleGroup size;
    @FXML
    public ImageView pizzaPic;

    @FXML
    public ListView<String> toppingsToAdd;
    public ListView<String> selectedToppings;
    public ObservableList<String> toppingsList = FXCollections.observableArrayList();
    public ObservableList<String> addedToppingsList = FXCollections.observableArrayList();

    @FXML
    public TextField totalPrice;

    public CheckBox extraSauce, extraCheese;
    public Button add, remove, submitOrder;
    private OrderManager orderManager;

    /**
     * Initializes FXML UI components declared above, sets event handler methods,
     * and gets current instance of order manager singleton class to handle order operations.
     */
    @FXML
    private void initialize()
    {
        orderManager = OrderManager.getInstance();
        for (Topping topping : Topping.values())
        {
            toppingsList.add(topping.toString());
        }
        toppingsToAdd.setItems(toppingsList);
        selectedToppings.setItems(addedToppingsList);
        String imageUrl = "/com/example/project4/images/default.jpg";
        Image defaultImage = new Image(getClass().getResourceAsStream(imageUrl));
        pizzaPic.setImage(defaultImage);
        size.selectedToggleProperty().addListener((observable, oldValue, newValue) -> updateTotal());
        small.setSelected(true);
        tomato.setSelected(true);
        add.setOnAction(this::handleAddTopping);
        remove.setOnAction(this::handleRemoveTopping);
        submitOrder.setOnAction(this::handleSubmitOrder);
        updateTotal();
    }

    /**
     * Adds a topping to build your own pizza topping list and updates listview
     * when a topping is selected and add button is clicked.
     * @param actionEvent Add button clicked
     */
    @FXML
    private void handleAddTopping(ActionEvent actionEvent)
    {
        String topping = toppingsToAdd.getSelectionModel().getSelectedItem();
        if (topping == null) //no topping selected to add
        {
            displayError("Select topping to add.");
            return;
        }
        else if (addedToppingsList.size() == 7) //already 7 toppings
        {
            displayError("Maximum 7 toppings allowed.");
            return;
        }
        else
        {
            addedToppingsList.add(topping);
            toppingsList.remove(topping);
            toppingsToAdd.setItems(toppingsList);
            selectedToppings.setItems(addedToppingsList);
        }
        updateTotal();
    }

    /**
     * Removes a topping from build your own pizza topping list and updates listview
     * when a topping is selected and remove button is clicked.
     * @param actionEvent Remove button clicked
     */
    @FXML
    private void handleRemoveTopping(ActionEvent actionEvent)
    {
        String topping = selectedToppings.getSelectionModel().getSelectedItem();
        if (topping == null) //no topping selected to add
        {
            displayError("Select topping to remove.");
        }
        else
        {
            toppingsList.add(topping);
            addedToppingsList.remove(topping);
            toppingsToAdd.setItems(toppingsList);
            selectedToppings.setItems(addedToppingsList);
        }
        updateTotal();
    }

    /**
     * Updates price of build your own pizza when extra sauce or extra cheese
     * checkboxes are selected.
     * @param event extra sauce / extra cheese checkboxes.
     */
    @FXML
    private void handleExtras(ActionEvent event)
    {
        updateTotal();
    }

    /**
     * Adds a pizza to current order after performing error checking when
     * submit order button clicked.
     * @param actionEvent submit button clicked
     */
    @FXML
    private void handleSubmitOrder(ActionEvent actionEvent)
    {
        if (addedToppingsList.size() < 3)
        {
            displayError("Minimum 3 toppings to add to order.");
            return;
        }
        ArrayList<Topping> toppings = new ArrayList<>();
        for (String topping: addedToppingsList)
        {
            toppings.add(Topping.valueOf(topping));
        }
        Pizza pizza = PizzaMaker.createPizza("Build Your Own");
        pizza.setToppings(toppings);
        pizza.setSize((Size.valueOf((((RadioButton) size.getSelectedToggle()).getText()).toUpperCase())));
        pizza.setSauce((Sauce.valueOf((((RadioButton) sauce.getSelectedToggle()).getText()).toUpperCase())));
        pizza.setExtraCheese(extraCheese.isSelected());
        pizza.setExtraSauce(extraSauce.isSelected());

        orderManager.addToOrder(pizza);
        Stage stage = (Stage) submitOrder.getScene().getWindow();
        stage.close();
        displayMessage("Pizza added to order!");
    }

    /**
     * Private helper method to update displayed total price of build your own pizza
     */
    private void updateTotal()
    {
        double price = 0.0;
        ArrayList<Topping> toppings = new ArrayList<>();
        for (String topping: addedToppingsList)
        {
            toppings.add(Topping.valueOf(topping));
        }
        if (toppings.size() >= 3) //less than 3 toppings = can't make a pizza, so no update in price
        {
            Pizza pizza = PizzaMaker.createPizza("Build Your Own");
            pizza.setToppings(toppings);
            pizza.setSize((Size.valueOf((((RadioButton) size.getSelectedToggle()).getText()).toUpperCase())));
            pizza.setSauce(Sauce.valueOf((((RadioButton) sauce.getSelectedToggle()).getText()).toUpperCase()));
            pizza.setExtraCheese(extraCheese.isSelected());
            pizza.setExtraSauce(extraSauce.isSelected());
            price += pizza.price();
        }
        totalPrice.setText(String.format("$%.2f", price));

    }

    /**
     * Displays an error pop up to the user.
     * @param s, string to be displayed in the message pop up
     */
    private void displayError (String s)
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
    private void displayMessage(String s)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }
}
