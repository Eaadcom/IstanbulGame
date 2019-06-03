package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception{


        stage.setTitle("Istanbul");
        Parent   root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        root.setId("pane");
        Scene scene = new Scene(root, 1920, 1080);
        stage.setFullScreen(true);


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}