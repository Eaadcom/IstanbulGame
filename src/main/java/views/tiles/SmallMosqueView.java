package views.tiles;

<<<<<<< Updated upstream
=======
import controllers.LocationController;
import javafx.fxml.FXML;
>>>>>>> Stashed changes
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
<<<<<<< Updated upstream
=======
import models.Player;
import models.locations.Location;
import models.locations.SmallMosque;
>>>>>>> Stashed changes

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SmallMosqueView implements Initializable {

    //Singleton
    private static SmallMosqueView smallMosqueView;
<<<<<<< Updated upstream
=======
    private LocationController locationController = LocationController.getInstance();
    private SmallMosque smallMosque = SmallMosque.getInstance();

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public void Close(){
    }

=======
    public void greenTile() {
        locationController.SmallMosque(smallMosque.boughtGreen, "spice");
        close();

    }

    public void redTile(){
       locationController.SmallMosque(smallMosque.boughtRed, "fabric");
       close();
    }

    public void close(){
        Stage stage = (Stage) neither.getScene().getWindow();
        stage.close();
    }


>>>>>>> Stashed changes
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set mosque tile 1 price
        //set mosque tile 2 price
    }
}
