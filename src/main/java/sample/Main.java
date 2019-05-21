package sample;

import firebase.FirebaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        //Player.writeToController();

        FirebaseController.firebaseListener();
        //testPrinter();

        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void testPrinter(){
        while (true) {
            try{
                System.out.println("test");
                TimeUnit.MINUTES.sleep(1);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
