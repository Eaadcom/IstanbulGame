package application;

import controllers.FirebaseController;
import controllers.LocationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.MainMenu;
import views.MenuView;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        MenuView.getInstance().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}