package views;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.database.annotations.Nullable;
import controllers.MenuViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Firebase;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LobbyView implements Initializable {

    // Variables
    private static LobbyView lobbyView;

    // FXML Variables
    @FXML Text roomName;
    @FXML Text p1;
    @FXML Text p2;
    @FXML Text p3;
    @FXML Text p4;
    @FXML Text p5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomName.setText("TEST");
    }

    @FXML
    public void start(String gameName, String playerTotal, int gameNum){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../fxml/lobby.fxml"));
            Parent root8 = (Parent) fxmlloader.load();

            roomName = (Text) root8.lookup("#roomName");
            roomName.setText(gameName);

            playerAmount(root8, Integer.parseInt(playerTotal));
            setNameByFirebase(root8, gameNum);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Istanbul");
            stage.setScene(new Scene(root8));
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setNameByFirebase(Parent root8, int gameNum){
        List namesList = (ArrayList) Firebase.getInstance().getLobbyInfo().get(gameNum).getData().get("playerNames");
        p1 = (Text) root8.lookup("#p1");
        p2 = (Text) root8.lookup("#p2");
        p3 = (Text) root8.lookup("#p3");
        p4 = (Text) root8.lookup("#p4");
        p5 = (Text) root8.lookup("#p5");

//        if (namesList.get(0) == "") {
//            p1.setText(MenuViewController.getInstance().getUserName());
//        } else if (namesList.get(1) == ""){
//
//        } else if (namesList.get(2) == ""){
//
//        } else if (namesList.get(3) == ""){
//
//        } else if (namesList.get(4) == ""){
//
//        }
    }

    private void playerAmount(Parent root8, int playerTotal){
        p1 = (Text) root8.lookup("#p1");
        p2 = (Text) root8.lookup("#p2");
        p3 = (Text) root8.lookup("#p3");
        p4 = (Text) root8.lookup("#p4");
        p5 = (Text) root8.lookup("#p5");

        if (playerTotal == 2){
            p3.setText("-----"); p4.setText("-----"); p5.setText("-----");
        } if (playerTotal == 3){
            p4.setText("-----"); p5.setText("-----");
        } if (playerTotal == 4){
            p5.setText("-----");
        }
    }

    // Singleton Pattern
    public static LobbyView getInstance() {
        if (lobbyView == null) {
            lobbyView = new LobbyView();
        }
        return lobbyView;
    }
}
