package models;

public class GameInformation {
    private String roomname;
    private int totalPlayers;
    private String joinButtons;

    public GameInformation(String r, int p, String q){
        this.roomname = r;
        this.totalPlayers = p;
        this.joinButtons = q;
    }

    public void setRoomname(String roomname){
        this.roomname = roomname;
    }
    public void setTotalPlayers(int players){
        this.totalPlayers = players;
    }

    public String getRoomname(){
        return roomname;
    }
    public int getTotalPlayers(){
        return totalPlayers;
    }
}
