package util;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import models.Firebase;
import views.LobbyView;
import views.MenuView;

import java.io.IOException;
import java.util.List;

public class GameInformation {
    private String roomname;
    private Integer totalPlayers;
    private Button button;
    private static int buttonAmount = 0;
    private Firebase firebase;

    public GameInformation(String roomName, Integer totalPlayers, String q){
        this.roomname = roomName;
        this.totalPlayers = totalPlayers;
        this.button = new Button("Join");
        this.button.setId(Integer.toString(buttonAmount));
        buttonAmount++;
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try{
                    firebase = Firebase.getInstance();
                    List<QueryDocumentSnapshot> data = firebase.getLobbyInfo();
                    String gameName = data.get(Integer.parseInt(button.getId())).getData().get("gameName").toString();
                    String playerTotal = data.get(Integer.parseInt(button.getId())).getData().get("playerTotal").toString();
                    LobbyView.getInstance().start(gameName, playerTotal, Integer.parseInt(button.getId()));
                } catch (Exception ee){
                    ee.printStackTrace();
                }
            }
        });
    }

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

}
