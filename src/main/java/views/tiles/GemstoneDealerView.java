package views.tiles;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.GemstoneDealer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the view class for the pop-up of the gemstonedealer.
 * @author Stan Hogenboom
 * @version 21-6-2019
 *
 */
public class GemstoneDealerView implements Initializable {

    private LocationController locationController = LocationController.getInstance();

    @FXML
    private Label gemprice;

    //Singleton
    private static GemstoneDealerView gemstoneDealerView;
    public static GemstoneDealerView getInstance() {
        if (gemstoneDealerView == null) {
            gemstoneDealerView = new GemstoneDealerView();
        }
        return gemstoneDealerView;
    }

    public void gemstoneDealer() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/gemstoneDealer.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void gemstoneDealerYes() {
        locationController.gemstoneDealerAction();
        close();
    }

    public void close() {
        Stage stage = (Stage) gemprice.getScene().getWindow();
        stage.close();
    }


    /**'
     * converts int to String so it can be displayed in the game
     * @param price
     * @return
     */
    private String convertPrice(int price) {
        return Integer.toString(price);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gemprice.setText(convertPrice(GemstoneDealer.getInstance().getGemstonePrice()));
    }
}
