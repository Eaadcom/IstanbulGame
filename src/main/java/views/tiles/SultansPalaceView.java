package views.tiles;

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
import models.Game;
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
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ChoiceBox choiceBox1, choiceBox2, choiceBox3;

    //private static GameController gameController = GameController.getInstance();

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
        Stage stage = (Stage) sultanspalacebuy.getScene().getWindow();
        stage.close();
        locationController.confirmPurchase();
    }

    public void updatePrice(){
        blueprice.setText (Integer.toString(locationController.getBluePrice()));
        greenprice.setText(Integer.toString(locationController.getGreenPrice()));
        redprice.setText(Integer.toString(locationController.getRedPrice()));
        yellowprice.setText(Integer.toString(locationController.getYellowPrice()));
        choiceamount.setText(Integer.toString(locationController.getChoiceAmount()));
    }


    public void sultansPalace() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/sultansPalace/sultansPalace.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void chooseOne() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/sultansPalace/sultansPalace2.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    public void chooseTwo() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../fxml/tiles/sultansPalace/sultansPalace3.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePrice();
        //choiceBox1.getItems().add("Fabric (Red)");
        //choiceBox1.getItems().add("Fruit (Yellow)");
        //choiceBox1.getItems().add("Spice (Green)");
        //choiceBox1.getItems().add("Jewel (Blue");

        //choiceBox2.getItems().add("Fabric (Red)");
        //choiceBox2.getItems().add("Fruit (Yellow)");
        //choiceBox2.getItems().add("Spice (Green)");
        //choiceBox2.getItems().add("Jewel (Blue");

        //choiceBox3.getItems().add("Fabric (Red)");
        //choiceBox3.getItems().add("Fruit (Yellow)");
        //choiceBox3.getItems().add("Spice (Green)");
        //choiceBox3.getItems().add("Jewel (Blue");
    }
}
