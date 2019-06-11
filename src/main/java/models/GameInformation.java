package models;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;

public class GameInformation {
    private String roomname;
    private java.lang.Object totalPlayers;
    private Button button;
    private static int buttonAmount = 0;
    private Firebase firebase;

    public GameInformation(String r, java.lang.Object p, String q){
        this.roomname = r;
        this.totalPlayers = p;
        this.button = new Button("Join");
        this.button.setId(Integer.toString(buttonAmount));
        buttonAmount++;
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                firebase = Firebase.getInstance();
                List<QueryDocumentSnapshot> data = firebase.getLobbyInfo();
                Object gameInfo = data.get(Integer.parseInt(button.getId())).getData().get("gameName");
                System.out.println(gameInfo);
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
