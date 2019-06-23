package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.Wainwright;


import java.io.IOException;

public class WainwrightView {

    @FXML
    public Button yes, no;
    private LocationController locationController = LocationController.getInstance();
    private static WainwrightView wainwrightView;
    public static WainwrightView getInstance() {
        if (wainwrightView == null) {
            wainwrightView = new WainwrightView();
        }
        return wainwrightView;
    }

    public void confirmWain(){
        locationController.wainrightBuyer();
        close();
    }

    public void close(){
            Stage stage = (Stage) no.getScene().getWindow();
            stage.close();
        }


    public void wainwright() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/wainwright.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
