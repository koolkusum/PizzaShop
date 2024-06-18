/**
 * PizzaApplication.java extends Application and defines a
 * JavaFX application, containing methods to launch and start it.
 */
package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzaApplication extends Application {

    /**
     * Initializes what javaFX application looks like after being
     * launched in main method.
     * @param stage initial stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException
    {

        FXMLLoader mainMenuLoader = new FXMLLoader(PizzaApplication.class.getResource("MainMenu.fxml"));
        Parent mainMenuRoot = mainMenuLoader.load();

        Scene scene = new Scene(mainMenuRoot, 600, 400);

        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches javaFX application
     * @param args
     */
    public static void main(String[] args)
    {
        launch();
    }
}