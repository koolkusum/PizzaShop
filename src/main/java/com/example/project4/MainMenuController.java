/**
 * MainMenuController.java initializes FXML UI components in
 *  MainMenu.fxml and contains methods to load different scenes based on user action.
 */

package com.example.project4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainMenuController
{
    /**
     * Private helper method to load a new scene based on passed in
     * fxml file name and title for stage.
     * @param fxmlFileName name of fxml file to open
     * @param stageTitle title for new stage
     */
    private void loadFXML(String fxmlFileName, String stageTitle)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle(stageTitle);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens corresponding fxml file when Specialty Pizzas label is clicked.
     */
    @FXML
    public void handleSpecialityPizzaClick()
    {
        loadFXML("SpecialtyPizzas.fxml", "Specialty Pizzas");
    }

    /**
     * Opens corresponding fxml file when Current Order label is clicked.
     */
    @FXML
    public void handleCurrentOrder()
    {
        loadFXML("CurrentOrder.fxml", "View Current Order");
    }

    /**
     * Opens corresponding fxml file when Build Your Own label is clicked.
     */
    @FXML
    public void handleBuildYourOwnPizzaClick()
    {
        loadFXML("BuildYourOwn.fxml", "Build Your Own Pizza");
    }

    /**
     * Opens corresponding fxml file when Store Orders is clicked.
     */
    @FXML
    public void handleStoreOrder()
    {
        loadFXML("StoreOrders.fxml", "View Store Orders");
    }

}
