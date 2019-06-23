package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GreatMosqueView implements Initializable {

    @FXML public Button BlueTile, YellowTile, neither;


    //Singleton
    LocationController locationController = LocationController.getInstance();
    private static GreatMosqueView largeMosqueView;
    public static GreatMosqueView getInstance() {
        if (largeMosqueView == null) {
            largeMosqueView = new GreatMosqueView();
        }
        return largeMosqueView;
    }
    //

    public void greatMosque() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/greatMosque.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void BlueTile() throws IOException{
        locationController.BigMosque("jewel");
        System.out.println("Jewel");

    }

    public void handleBlueTile(){

    }

    public void YellowTile(){
        locationController.BigMosque("fruit");
        System.out.println("Fruit");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set mosque tile 1 price

        //set mosque tile 2 price
    }
}
