package controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import models.Firebase;
import models.Game;
import models.Player;

import java.util.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class FirebaseController {

    // Variables
    private static FirebaseController firebaseController;
    private MenuViewController menuViewController;
    private Firestore db = firebaseLogin();
    private Firebase firebase;

    // Write to Firebase
    public void firebaseWriter(LinkedHashMap<String, String> variables){

        //System.out.println(variables);
        ArrayList<String> variablesInput = new ArrayList<>(variables.values());
        ArrayList<String> keysInput = new ArrayList<>(variables.keySet());

        try{
            DocumentReference docRef = db.collection("Classes").document(variables.get("className"));
            Map<String, Object> data = new HashMap<>();

            for (int i = 0; i < variables.size(); i++){
                data.put(keysInput.get(i), variablesInput.get(i));
            }

            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e){
            System.out.println(e);
        }
    }

    // gets all game documents and returns them in a list
    public List fillGameLobby(){
        try{
            ApiFuture<QuerySnapshot> future = db.collection("Games").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            //for (QueryDocumentSnapshot document : documents) {
            //    System.out.println(document.getData());
            //}
            //saveLobbyInfo(documents);

            QueryDocumentSnapshot result = null;
            for (QueryDocumentSnapshot queryDocumentSnapshot : documents) {
                if(queryDocumentSnapshot.getId().equals("_A_Test2")) {
                    result = queryDocumentSnapshot;
                    break;
                }
            }

            firebase = Firebase.getInstance();
            firebase.setLobbyInfo(Collections.singletonList(result));
            return documents;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveLobbyInfo(List<QueryDocumentSnapshot> documents){
        ArrayList<Map> documentsList = new ArrayList<Map>();

        for (QueryDocumentSnapshot document : documents) {
            documentsList.add(document.getData());
        }
    }

    // Get data from Firebase
    public void firebaseReader(Map<String, Object> data){
        ArrayList<Object> variables = new ArrayList<>(data.values());
        ArrayList<String> keys = new ArrayList<>(data.keySet());

        for (int i  = 0; i < variables.size(); i++){
        }
    }

    // Listen for changes to the Firebase
    public void firebaseListener(String game){
        Runnable runnable = () -> {

            DocumentReference docRef = db.collection("Games").document(game);
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot,
                                    @Nullable FirestoreException e) {
                    if (e != null) {
                        System.err.println("Listen failed: " + e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        Map<String, Object> newData = snapshot.getData();
                        System.out.println("Current data: " + newData);
//                        firebaseReader(newData);
                    } else {
                        System.out.print("Current data: null");
                    }
                }
            });
        };

        Thread ListenerThread = new Thread(runnable);
        ListenerThread.start();
    }

    // Outputs firestore token used to do operations with firebase
    public Firestore firebaseLogin(){
        try{
            InputStream serviceAccount = new FileInputStream("src//main//resources//istanbulgame-c7958-firebase-adminsdk-gn1yc-811acdb682.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();
            return db;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return db;
        }
    }

    // Create Online Game
    public void createOnlineGame(Game game){
        try{
            menuViewController = MenuViewController.getInstance();
            DocumentReference docRef = db.collection("Games").document(game.getName());

            Map<String, Object> data = new HashMap<>();
            ArrayList<String> userNames = new ArrayList<>();
            for (Player player : game.getPlayers()) {
                userNames.add(player.getName());
            }

            data.put("gameName", game.getName());
            data.put("playerTotal", game.getPlayerTotal());
            data.put("gameDifficulty", game.getDifficulty().getValue());
            data.put("dateCreated", new Date());
            data.put("playerNames", userNames);

            System.out.println("inserting data: " + data.toString() + " for game with name: " + game.getName());

            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Singleton Pattern
    public static FirebaseController getInstance() {
        if (firebaseController == null) {
            firebaseController = new FirebaseController();
        }
        return firebaseController;
    }
}
