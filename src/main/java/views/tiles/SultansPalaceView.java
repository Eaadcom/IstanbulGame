package views.tiles;

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

public class SultansPalaceView implements Initializable {

    //Singleton
    private static SultansPalaceView sultansPalaceViewView;
    public static SultansPalaceView getInstance() {
        if (sultansPalaceViewView == null) {
            sultansPalaceViewView = new SultansPalaceView();
        }
        return sultansPalaceViewView;
    }
    //

    public void sultansPalace() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/sultansPalace.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set red tile price
        //set green tile price
        //set blue tile price
        //set yellow tile price
        //set choice tile price
    }
}
