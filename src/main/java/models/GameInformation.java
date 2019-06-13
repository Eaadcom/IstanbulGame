package models;

import javafx.scene.control.Button;

public class GameInformation {
    private String roomname;
    private int totalPlayers;
    private Button button;

    public GameInformation(String r, int p){
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
    public int getTotalPlayers(){ return totalPlayers; }
    public Button getButton(){ return button; }
}
