package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeaHouseView implements Initializable {
    private LocationController locationController = LocationController.getInstance();
    @FXML
    public Label rollNumber;
    @FXML
    public Label liraNumber;
    @FXML
    public Button sweet;

    public void teaHouseResult() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/teaHouse/teaHouse3.fxml"));
        Parent root = fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void close(){
        Stage stage = (Stage) sweet.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(locationController.teahouseNumberChoice < locationController.diceResult || locationController.teahouseNumberChoice == locationController.diceResult) {
            rollNumber.setText(String.valueOf(locationController.getTeaHouseNumber()));
        } else if (locationController.teahouseNumberChoice > locationController.diceResult){
            rollNumber.setText(String.valueOf(2));
        }

        liraNumber.setText(String.valueOf(locationController.getTeaHouseDiceNumber()));
    }
}
