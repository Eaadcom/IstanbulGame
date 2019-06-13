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

public class PostOfficeView implements Initializable {

    //Singleton
    private static PostOfficeView postOfficeView;
    public static PostOfficeView getInstance() {
        if (postOfficeView == null) {
            postOfficeView = new PostOfficeView();
        }
        return postOfficeView;
    }
    //

    public void postOffice() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/postOffice.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set red tile amount
        //set green tile amount
        //set blue tile amount
        //set yellow tile amount
        //set lira amount
    }
}
