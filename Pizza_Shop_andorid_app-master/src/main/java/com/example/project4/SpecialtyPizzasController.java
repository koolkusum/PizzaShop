/**
 * SpecialtyPizzasController.java initializes FXML UI components in
 *  SpecialtyPizzas.fxml and contains methods to view and add pizzas to current order.
 */
package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SpecialtyPizzasController
{
    public RadioButton small, medium, large;

    public ToggleGroup size;
    @FXML
    public ImageView pizzaPic;
    @FXML
    public ChoiceBox<String> pizzaType;
    @FXML
    public ListView<String> toppingsListView;
    public TextArea sauceText, totalText;
    public ObservableList<String> deluxe = FXCollections.observableArrayList(
            "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");

    public ObservableList<String> supreme = FXCollections.observableArrayList(
            "Sausage", "Pepperoni","Ham", "Green Pepper", "Onion", "Black Olive", "Mushroom");

    public ObservableList<String> meatzza= FXCollections.observableArrayList(
            "Sausage", "Pepperoni", "Beef", "Ham");

    public ObservableList<String> seafood = FXCollections.observableArrayList(
         "Shrimp", "Squid", "Crab Meats");
    public ObservableList<String> pepperoni = FXCollections.observableArrayList(
            "Pepperoni");
    public CheckBox extraSauce, extraCheese;
    public Button submitOrder;
    private OrderManager orderManager;

    /**
     * Initializes FXML UI components declared above and sets event handler methods.
     */
    @FXML
    private void initialize()
    {
        orderManager = OrderManager.getInstance();

        String imageUrl = "/com/example/project4/images/default.jpg";
        Image defaultImage = new Image(getClass().getResourceAsStream(imageUrl));
        pizzaPic.setImage(defaultImage);

       // setOrderManager(orderManager);
        pizzaType.setOnAction(this::handlePizzaTypeChange);
        size.selectedToggleProperty().addListener((observable, oldValue, newValue) -> updateTotal());
        pizzaType.setValue("Choose a Pizza Type!");
        small.setSelected(true);
        totalText.setText(String.format("$%.2f", 0.0));
        submitOrder.setOnAction(this::handleAddToOrder);
    }

    /**
     * Private helper method to add a specialty pizza to current order
     * after performing error checking when add to order button selected.
     * @param event add to order button selected
     */
    @FXML
    private void handleAddToOrder(ActionEvent event) {
        if (pizzaType.getValue().equals("Choose a Pizza Type!"))
        {
            displayError("Must select a pizza type!");
            return;
        }
        String selectedPizzaType = pizzaType.getValue();
        Toggle selectedSize = size.getSelectedToggle();

        Pizza pizza = PizzaMaker.createPizza(selectedPizzaType);
        pizza.setSize(Size.valueOf((((RadioButton) selectedSize).getText()).toUpperCase()));
        pizza.setExtraCheese(extraCheese.isSelected());
        pizza.setExtraSauce(extraSauce.isSelected());

        orderManager.addToOrder(pizza);
        Stage stage = (Stage) submitOrder.getScene().getWindow();
        stage.close();
        displayMessage("Pizza added to order!");
    }

    /**
     * Private helper method to change list of toppings, type of sauce,
     * and image depending on specialty pizza type selected from choicebox
     * @param event new choice box pizza type selection
     */
    private void handlePizzaTypeChange(ActionEvent event)
    {
        String selectedPizzaType = pizzaType.getValue();
        handleImageChange(selectedPizzaType);
        ObservableList<String> selectedPizzaList = getToppingsList(selectedPizzaType);
        toppingsListView.setItems(selectedPizzaList);
        String selectPizzaSauce = getSauceList(selectedPizzaType);
        sauceText.setText(selectPizzaSauce);
        updateTotal();
    }

    /**
     * Private helper method to update total price for specialty pizza
     * depending on if extra sauce or cheese selected
     * @param event extra sauce / cheese checkboxes
     */
    @FXML
    private void handleExtras(ActionEvent event)
    {
        updateTotal();
    }

    /**
     * Private helper method to update image view based on type of
     * specialty pizza selected
     * @param pizza type of specialty pizza selected
     */
    private void handleImageChange(String pizza)
    {
        String imageUrl;
        switch(pizza)
        {
            case "Deluxe":
                imageUrl = "/com/example/project4/images/deluxe.jpg"; break;
            case "Supreme":
                imageUrl = "/com/example/project4/images/supreme.jpg"; break;
            case "Seafood":
                imageUrl = "/com/example/project4/images/seafood.jpg"; break;
            case "Pepperoni":
                imageUrl = "/com/example/project4/images/pepperoni.jpg"; break;
            case "Meatzza":
                imageUrl = "/com/example/project4/images/meatzza.jpg"; break;
            default:
                imageUrl = "/com/example/project4/images/default.jpg"; break;

        }
        Image defaultImage = new Image(getClass().getResourceAsStream(imageUrl));
        pizzaPic.setImage(defaultImage);
    }

    /**
     * Private helper method to get type of toppings as a list of Strings
     * depending on kind of specialty pizza
     */
    private ObservableList<String> getToppingsList(String pizzaType) {
        switch (pizzaType) {
            case "Deluxe":
                return deluxe;
            case "Supreme":
                return supreme;
            case "Meatzza":
                return meatzza;
            case "Seafood":
                return seafood;
            case "Pepperoni":
                return pepperoni;
            default:
                return FXCollections.emptyObservableList();
        }
    }

    /**
     * Private helper method to get type of sauce as a String depending
     * on kind of specialty pizza
     */
    private String getSauceList(String pizzaType){
        switch(pizzaType){
            case "Deluxe", "Supreme", "Meatzza", "Pepperoni":
                return "tomato";
            case "Seafood":
                return "alfredo";
            default:
                return "";
        }
    }

    /**
     * Private helper method to update displayed total price of specialty pizza
     */
    private void updateTotal()
    {
        String selectedPizzaType = pizzaType.getValue();
        Toggle selectedSize = size.getSelectedToggle();

        if (!selectedPizzaType.equals("Choose a Pizza Type!") && selectedSize != null) {
            double totalPrice = calculateTotalPrice(selectedPizzaType, ((RadioButton) selectedSize).getText(),
                    extraCheese.isSelected(), extraSauce.isSelected());
            totalText.setText(String.format("$%.2f", totalPrice));
        }
    }

    /**
     * Private helper method to calculate total price of a specialty pizza
     */
    private double calculateTotalPrice(String pizzaType, String size, boolean extraCheese, boolean extraSauce)
    {
        double basePrice = 0.0;
        Pizza pizza = PizzaMaker.createPizza(pizzaType);
        pizza.setSize(Size.valueOf(size.toUpperCase()));
        pizza.setExtraCheese(extraCheese);
        pizza.setExtraSauce(extraSauce);
        basePrice += pizza.price();
        return basePrice;
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
