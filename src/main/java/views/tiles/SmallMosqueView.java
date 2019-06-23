package views.tiles;

import controllers.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Player;
import models.locations.SmallMosque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SmallMosqueView implements Initializable {

    @FXML
    public Button neither;

    //Singleton
    private static SmallMosqueView smallMosqueView;

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

    public void greenTile() {
        Player player = GameController.getInstance().getPlayer();
        if (!player.isGreenTile() && SmallMosque.getInstance().getGreenPrice() <= player.getSpices()) {
            player.setGreenTile(true);
            if(player.isGreenTile() && player.isRedTile()){
                player.setRubies(player.getRubies()+1);
            }
            player.setSpices(player.getSpices()-1);
            SmallMosque.getInstance().incrementPrice("green");
            System.out.println(SmallMosque.getInstance().getGreenPrice());
            close();
        } else {
            //doe niks
        }
    }

    public void redTile(){
        Player player = GameController.getInstance().getPlayer();
        if(!player.isRedTile()&& SmallMosque.getInstance().getRedPrice() <= player.getFabrics()){
            player.setRedTile(true);
            if(player.isGreenTile() && player.isRedTile()){
                player.setRubies(player.getRubies()+1);
            }
            player.setFabrics(player.getFabrics()-1);
            SmallMosque.getInstance().incrementPrice("red");
            System.out.println(SmallMosque.getInstance().getRedPrice());
            close();
        }else{
            //doe niks
        }
    }

    public void close(){
        Stage stage = (Stage) neither.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set mosque tile 1 price
        //set mosque tile 2 price
    }
}
