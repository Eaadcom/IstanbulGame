package models;

import javafx.scene.control.Button;

public class GameInformation {
    private String roomname;
    private java.lang.Object totalPlayers;
    private Button button;

    public GameInformation(String r, java.lang.Object p, String q){
        this.roomname = r;
        this.totalPlayers = p;
        this.button = new Button("Join");
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
