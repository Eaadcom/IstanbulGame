package views.tiles.sultansPalace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SultansPalaceView3 implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ChoiceBox choiceBox2, choiceBox3;


    //Singleton
    private static SultansPalaceView3 sultansPalaceView;
    public static SultansPalaceView3 getInstance() {
        if (sultansPalaceView == null) {
            sultansPalaceView = new SultansPalaceView3();
        }
        return sultansPalaceView;
    }
    //


    public void chooseTwo() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/sultansPalace/sultansPalace3.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox2.getItems().add("Fabric (Red)");
        choiceBox2.getItems().add("Fruit (Yellow)");
        choiceBox2.getItems().add("Spice (Green)");
        choiceBox2.getItems().add("Jewel (Blue)");

        choiceBox3.getItems().add("Fabric (Red)");
        choiceBox3.getItems().add("Fruit (Yellow)");
        choiceBox3.getItems().add("Spice (Green)");
        choiceBox3.getItems().add("Jewel (Blue)");
    }
}
