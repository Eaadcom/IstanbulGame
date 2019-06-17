package models;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Firebase {

    // Variabelen
    private static Firebase firebase;
    private List<QueryDocumentSnapshot> lobbyInfo;

    // Setters
    public void setLobbyInfo(List<QueryDocumentSnapshot> info){

        this.lobbyInfo  = info;
    }

    // Getters
    public List<QueryDocumentSnapshot> getLobbyInfo(){
        return lobbyInfo;
    }

    // Singleton Pattern
    public static Firebase getInstance() {
        if (firebase == null) {
            firebase = new Firebase();
        }
        return firebase;
    }
}
