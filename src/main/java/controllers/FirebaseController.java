package controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Firebase;
import models.GameInformation;

import java.lang.reflect.Array;
import java.util.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

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

            firebase = Firebase.getInstance();
            firebase.setLobbyInfo(documents);
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
    public void firebaseListener(){

        Firestore db = firebaseLogin();

        Runnable runnable = () -> {

            DocumentReference docRef = db.collection("Classes").document("Player");
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
    public void createOnlineGame(){
        try{
            menuViewController = MenuViewController.getInstance();
            DocumentReference docRef = db.collection("Games").document(menuViewController.getGameName());

            Map<String, Object> data = new HashMap<>();
            data.put("gameName", menuViewController.getGameName());
            data.put("playerTotal", menuViewController.getPlayerTotal());
            data.put("gameDifficulty", menuViewController.getGameDifficulty());
            data.put("dateCreated", new Date());

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
