package views.tiles;

import controllers.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.locations.PostOffice;

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

    @FXML
    private Text blue, red, green, yellow, lira;
    @FXML
    public Button yes, no;

    public void postOffice() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../fxml/tiles/postOffice.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void confirm(){
        PostOffice.getInstance().confirmPurchase(GameController.getInstance().getPlayer());
        Stage stage = (Stage) yes.getScene().getWindow();
        stage.close();
    }

    public void close(){
        Stage stage = (Stage) no.getScene().getWindow();
        stage.close();
    }

    private void updatePrice(){
        blue.setText (Integer.toString(PostOffice.getInstance().PostOfficeGetJewel()));
        green.setText(Integer.toString(PostOffice.getInstance().PostOfficeGetSpice()));
        red.setText(Integer.toString(PostOffice.getInstance().PostOfficeGetFabric()));
        yellow.setText(Integer.toString(PostOffice.getInstance().PostOfficeGetFruit()));
        lira.setText(Integer.toString(PostOffice.getInstance().PostOfficeGetLira()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePrice();
    }
}
