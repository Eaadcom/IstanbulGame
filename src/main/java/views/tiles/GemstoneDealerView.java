package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GemstoneDealerView implements Initializable {

    private LocationController locationController = LocationController.getInstance();

    //Singleton
    private static GemstoneDealerView gemstoneDealerView;
    public static GemstoneDealerView getInstance() {
        if (gemstoneDealerView == null) {
            gemstoneDealerView = new GemstoneDealerView();
        }
        return gemstoneDealerView;
    }
    //

    public void gemstoneDealer() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/gemstoneDealer.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set gem price
        //LocationController.getGemPrice();
    }
}
