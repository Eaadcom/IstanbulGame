package views.tiles.sultansPalace;

import controllers.LocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SultansPalaceView2 implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ChoiceBox choiceBox1;
    @FXML
    private Button confirmChoice;


    //Singleton
    private static SultansPalaceView2 sultansPalaceView;
    public static SultansPalaceView2 getInstance() {
        if (sultansPalaceView == null) {
            sultansPalaceView = new SultansPalaceView2();
        }
        return sultansPalaceView;
    }
    //

    public void chooseOne() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../../fxml/tiles/sultansPalace/sultansPalace2.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void close(){
        Stage stage = (Stage) choiceBox1.getScene().getWindow();
        stage.close();
    }

    public void confirmChoice(){
        LocationController.getInstance().handleChoice(choiceBox1.getValue().toString());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox1.getItems().add("Fabric (Red)");
        choiceBox1.getItems().add("Fruit (Yellow)");
        choiceBox1.getItems().add("Spice (Green)");
        choiceBox1.getItems().add("Jewel (Blue)");
    }
}
