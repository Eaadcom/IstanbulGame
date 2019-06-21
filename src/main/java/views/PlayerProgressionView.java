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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.GameInformation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerProgressionView implements Initializable {

    // Variables
    private static PlayerProgressionView playerProgressionView;

    // FXML Variables
    @FXML
    public Text playername, redProg, blueProg, yellowProg, greenProg, liraProg, rubyProg, carProg;
    @FXML
    public Pane playerIcon;

    public int STATE;


    public void playerProgression(int id){
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
        redProg = (Text) root2.lookup("#redProg");
        yellowProg = (Text) root2.lookup("#yellowProg");
        greenProg = (Text) root2.lookup("#greenProg");
        blueProg = (Text) root2.lookup("#blueProg");
        liraProg = (Text) root2.lookup("#liraProg");
        rubyProg = (Text) root2.lookup("#rubyProg");
        carProg = (Text) root2.lookup("#carProg");

        playerIcon = (Pane) root2.lookup("#playerIcon");



        updateScreen(id);
    }

    public void nextPlayer(){
        updateScreen(STATE+1);
    }

    public void previousPlayer(){
        updateScreen(STATE-1);
    }

    public void updateScreen(int id){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                try {
                    playerIcon.setId("playerIcon" + id);
                    playername.setText(GameController.getInstance().getGame().board.players.get(id).getName());
                    redProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getFabrics()));
                    yellowProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getFruits()));
                    greenProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getSpices()));
                    blueProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getJewels()));
                    liraProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getLira()));
                    rubyProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getRubies()));
                    carProg.setText(Integer.toString(GameController.getInstance().getGame().board.players.get(id).getCarUpgrades()));
                    STATE = id;
                }
                catch(Exception e) {
                    System.out.println("Je klikte op een speler die er niet is. De gegevens konden niet geladen worden.");
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
