package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CaravansaryView {
    private LocationController locationController = LocationController.getInstance();
    private static CaravansaryView caravansaryView;
    public static CaravansaryView getInstance() {
        if (caravansaryView == null) {
            caravansaryView = new CaravansaryView();
        }
        return caravansaryView;


    }


    public void caravansary() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/caravansary.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        /*
        locationController.AssistantLocation(16);
        if(locationController.hasAssistants == true) {
            locationController.wainrightBuyer();
        } else{
            System.out.println("Nope");
        }*/


    }
}
