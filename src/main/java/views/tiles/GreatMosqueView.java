package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.GreatMosque;
import models.locations.Location;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GreatMosqueView implements Initializable {
    @FXML
    Button neither;

    private LocationController locationController = LocationController.getInstance();
    private GreatMosque greatMosque = GreatMosque.getInstance();
    //Singleton
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
    public void blueTile(){

        locationController.BigMosque(greatMosque.bluePrice, "jewel");
        close();


    }

    public void yellowTile(){
        locationController.BigMosque(greatMosque.yellowPrice, "fruit");
        close();
    }

    public void close(){
        Stage stage = (Stage) neither.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set mosque tile 1 price
        //set mosque tile 2 price
    }
}
