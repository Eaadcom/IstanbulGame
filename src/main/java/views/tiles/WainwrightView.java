package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.Wainwright;


import java.io.IOException;

public class WainwrightView {
    private LocationController locationController = LocationController.getInstance();
    private static WainwrightView wainwrightView;
    public static WainwrightView getInstance() {
        if (wainwrightView == null) {
            wainwrightView = new WainwrightView();
        }
        return wainwrightView;


    }


    public void wainwright() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/tiles/wainwright.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        locationController.wainrightBuyer();
    }
}
