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

    Main(LocationController locationController){
        this.locationController = locationController;

    }

    @Override
    public void start(Stage stage) throws Exception{
        System.out.println("test4");

        MenuView menuView = new MenuView();
        menuView.start(stage);

        locationController.BlackMarketChoice(3);
        System.out.println("test4");


    }

    public static void main(String[] args) {
        launch(args);
    }
}
