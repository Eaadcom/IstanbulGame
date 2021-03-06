package util;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Firebase;
import views.AvailableRoomsView;
import views.LobbyView;
import views.MenuView;

import java.io.IOException;
import java.util.List;

public class GameInformation {
    private GameController gameController;
    private static GameInformation gameInformation;
    private String roomname;
    private Integer totalPlayers;
    private Button button;
    private QueryDocumentSnapshot document;
//    private static int buttonAmount = 0;
    private Firebase firebase;

    public GameInformation(QueryDocumentSnapshot document) {
        gameController = GameController.getInstance();
        this.document = document;
        this.roomname = document.getId();
        this.totalPlayers = new Long((long) document.getData().get("playerTotal")).intValue();
        this.button = new Button("Join");
        this.button.setOnAction(setButtonActionHandler());
    }

    private EventHandler<ActionEvent> setButtonActionHandler() {
        return event -> {
            try {
                gameInformation = this;
                gameController.joinGame(document);
                LobbyView.getInstance().showLobbyView();
                MenuView.getInstance().getStage2().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public GameInformation(String roomName, Integer totalPlayers, String q){
        this.roomname = roomName;
        this.totalPlayers = totalPlayers;
        this.button = new Button("Join");
//        this.button.setId(Integer.toString(buttonAmount));
//        buttonAmount++;
        this.button.setOnAction(e -> {
            try{

//                firebase = Firebase.getInstance();
//                List<QueryDocumentSnapshot> data = firebase.getLobbyInfo();
//                String gameName = data.get(Integer.parseInt(button.getId())).getData().get("gameName").toString();
//                String playerTotal = data.get(Integer.parseInt(button.getId())).getData().get("playerTotal").toString();
//                LobbyView.getInstance().start(gameName, playerTotal, Integer.parseInt(button.getId()));
            } catch (Exception ee){
                ee.printStackTrace();
            }
        });
    }

    // Setters
    public void setRoomname(String roomname){
        this.roomname = roomname;
    }
    public void setTotalPlayers(int players){
        this.totalPlayers = players;
    }
    public void setButton(Button button){
        this.button = button;
    }

    public String getRoomname(){
        return roomname;
    }
    public java.lang.Object getTotalPlayers(){
        return totalPlayers;
    }
    public Button getButton(){
        return button;
    }

    // Getters
    public static GameInformation getGameInfo(){
        return gameInformation;
    }

}
