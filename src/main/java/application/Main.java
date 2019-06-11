package application;

import controllers.FirebaseController;
import controllers.LocationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MenuView;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        MenuView menuView = MenuView.getInstance();
        menuView.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}