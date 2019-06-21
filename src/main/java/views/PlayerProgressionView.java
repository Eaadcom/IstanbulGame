package views;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.GameController;
import controllers.MenuViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.GameInformation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerProgressionView implements Initializable {

    // Variables
    private static PlayerProgressionView playerProgressionView;

    // FXML Variables
    @FXML
    public Text playername;


    public void playerProgression(String color){
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/playerProgression.fxml"));
        Parent root2 = null;
        try {
            root2 = (Parent) fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        playername = (Text) root2.lookup("#playername");

        updateScreen(color);
    }

    public void updateScreen(String color){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                if(color == "red"){
                    System.out.println(GameController.getInstance().getGame().board.players.get(0).getName());
                    playername.setText(GameController.getInstance().getGame().board.players.get(0).getName());
                }else if(color == "yellow"){
                    playername.setText(GameController.getInstance().getGame().board.players.get(1).getName());
                }else if(color == "green"){
                    playername.setText(GameController.getInstance().getGame().board.players.get(2).getName());
                }else if(color == "blue"){
                    playername.setText(GameController.getInstance().getGame().board.players.get(3).getName());
                }else if(color == "white"){
                    playername.setText(GameController.getInstance().getGame().board.players.get(4).getName());
                }
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    // Singleton Pattern
    public static PlayerProgressionView getInstance() {
        if (playerProgressionView == null) {
            playerProgressionView = new PlayerProgressionView();
        }
        return playerProgressionView;
    }
}
