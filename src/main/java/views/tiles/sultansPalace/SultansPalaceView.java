package views.tiles.sultansPalace;

import controllers.GameController;
import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Player;
import models.locations.SultanPalace;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SultansPalaceView implements Initializable {

    private LocationController locationController = LocationController.getInstance();

    @FXML
    private Text blueprice, redprice, greenprice, yellowprice, choiceamount;
    @FXML
    private Button sultanspalacebuy;


    //Singleton
    private static SultansPalaceView sultansPalaceView;
    public static SultansPalaceView getInstance() {
        if (sultansPalaceView == null) {
            sultansPalaceView = new SultansPalaceView();
        }
        return sultansPalaceView;
    }
    //

    public void confirmPurchase() throws IOException {
        Player player = GameController.getInstance().getPlayer();

        if(player.getFabrics() >= SultanPalace.getInstance().getFabricPrice() &&
           player.getFruits()  >= SultanPalace.getInstance().getFruitPrice()  &&
           player.getJewels()  >= SultanPalace.getInstance().getJewelPrice()  &&
           player.getSpices()  >= SultanPalace.getInstance().getSpicePrice()  ){
                Stage stage = (Stage) sultanspalacebuy.getScene().getWindow();
                stage.close();
                LocationController.getInstance().confirmPurchase();
        }else{
            // doe niks
        }

    }

    public void updatePrice(){
        blueprice.setText (Integer.toString(locationController.getBluePrice()));
        greenprice.setText(Integer.toString(locationController.getGreenPrice()));
        redprice.setText(Integer.toString(locationController.getRedPrice()));
        yellowprice.setText(Integer.toString(locationController.getYellowPrice()));
        choiceamount.setText(Integer.toString(locationController.getChoiceAmount()));
    }

    public void close(){
        Stage stage = (Stage) blueprice.getScene().getWindow();
        stage.close();
    }



    public void sultansPalace() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../../fxml/tiles/sultansPalace/sultansPalace.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePrice();

    }
}
