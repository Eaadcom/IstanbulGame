package firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseController {
    private static Firestore db;

    public static void firebaseWriter(LinkedHashMap<String, String> variables){

        Firestore db = firebaseLogin();

        System.out.println(variables);
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

//    public static void firebaseReader(Map<String, Object> data){
//        ArrayList<Object> variables = new ArrayList<>(data.values());
//        ArrayList<String> keys = new ArrayList<>(data.keySet());
//
//        for (int i  = 0; i < variables.size(); i++){
//            data.get("className").
//        }
//    }

    public static void firebaseListener(){

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

    public static Firestore firebaseLogin(){
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
}
