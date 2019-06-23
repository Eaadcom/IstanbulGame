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
import models.Player;
import models.locations.SmallMosque;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SmallMosqueView implements Initializable {

    @FXML
    Button neither;

    private LocationController locationController = LocationController.getInstance();
    private SmallMosque smallMosque = SmallMosque.getInstance();
    //Singleton
    private static SmallMosqueView smallMosqueView;

    public static SmallMosqueView getInstance() {
        if (smallMosqueView == null) {
            smallMosqueView = new SmallMosqueView();
        }
        return smallMosqueView;
    }
    //

    public void smallMosque() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/smallMosque.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void greenTile(){
        locationController.SmallMosque(smallMosque.greenPrice, "spice");
        close();
    }

    public void redTile(){
        locationController.SmallMosque(smallMosque.redPrice, "fabric");
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
