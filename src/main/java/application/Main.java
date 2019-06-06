package application;

import controllers.LocationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MenuView;

public class Main extends Application {
    LocationController locationController;

    @Override
    public void start(Stage stage) throws Exception{

        MenuView menuView = new MenuView();
        menuView.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
