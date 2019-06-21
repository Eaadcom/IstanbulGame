package controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import models.Board;
import models.Firebase;
import models.Game;
import models.Player;
import models.locations.*;
import util.GameInformation;

import java.util.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class FirebaseController {

    // Variables
    private static FirebaseController firebaseController;
    private static GameController gameController = GameController.getInstance();
    private Firestore db;
    private Firebase firebase;

    public void initialize() {
        db = firebaseLogin();
    }


    // gets all game documents and returns them in a list
    public List<QueryDocumentSnapshot> fillGameLobby(){
        try{
                       ApiFuture<QuerySnapshot> future = db.collection("Games").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            QueryDocumentSnapshot result = null;
            for (QueryDocumentSnapshot queryDocumentSnapshot : documents) {
                if(queryDocumentSnapshot.getId().equals("_A_Test2")) {
                    result = queryDocumentSnapshot;
                    break;
                }
            }

            return documents;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //TODO can i remove this? -Ed
    public void saveLobbyInfo(List<QueryDocumentSnapshot> documents){
        ArrayList<Map> documentsList = new ArrayList<Map>();

        for (QueryDocumentSnapshot document : documents) {
            documentsList.add(document.getData());
        }
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

    /**
     * Updates the game in the firebase firebase snapshot it
     * gets by using the current game model it gets as an argument.
     *
     * @version 21-6-2019
     * @author Edward Deen
     * @param game
     */
    public void updateGame(Game game) {
        try {
            DocumentReference docRef = db.collection("Games").document(game.getName());
            Map<String, Object> data = createKeyValueMapForGame(game);
            System.out.println("updating data: " + data.toString() + " for game with name: " + game.getName());

            ApiFuture<WriteResult> result = docRef.update(data);
            WriteResult writeResult = result.get();
            System.out.println("Update result: " + writeResult);
            System.out.println("Update time : " + writeResult.getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> createKeyValueMapForTiles(Board board){
        Map<String, Object> tileData = new HashMap<>();
        tileData.put("BlackMarket", BlackMarket.getInstance().getVariableMap()); tileData.put("Caravansary", Caravansary.getInstance().getVariableMap());
        tileData.put("FabricWarehouse", FabricWarehouse.getInstance().getVariableMap()); tileData.put("Fountain", Fountain.getInstance().getVariableMap());

        tileData.put("FruitWarehouse", FruitWarehouse.getInstance().getVariableMap()); tileData.put("GemstoneDealer", GemstoneDealer.getInstance().getVariableMap());
        tileData.put("GreatMarket", GreatMarket.getInstance().getVariableMap()); tileData.put("PoliceStation", PoliceStation.getInstance().getVariableMap());

        tileData.put("PostOffice", PostOffice.getInstance().getVariableMap()); tileData.put("SmallMarket", SmallMarket.getInstance().getVariableMap());
        tileData.put("SmallMosque", SmallMosque.getInstance().getVariableMap()); tileData.put("SpiceWarehouse", SpiceWarehouse.getInstance().getVariableMap());

        tileData.put("SultanPalace", SultanPalace.getInstance().getVariableMap()); tileData.put("TeaHouse", TeaHouse.getInstance().getVariableMap());
        tileData.put("Wainwright", Wainwright.getInstance().getVariableMap()); tileData.put("GreatMosque", GreatMarket.getInstance().getVariableMap());
        return tileData;
    }

    private Map<String, Object> createKeyValueMapForPlayers(Board board){
        try{
            Map<String, Object> players = new HashMap<>();
            int playerTotal = GameController.getInstance().getGame().getPlayerTotal();
            int playerListLength = gameController.getInstance().getGame().board.players.size();

            Player player1 = board.getPlayers().get(0); players.put("Player1", player1.getVariableMap());
            if (playerListLength > 1){
                Player player2 = board.getPlayers().get(1); players.put("Player2", player2.getVariableMap());
            } if (playerTotal >= 3){
                Player player3 = board.getPlayers().get(2);
                players.put("Player3", player3.getVariableMap());
            } if (playerTotal >= 4){
                Player player4 = board.getPlayers().get(3);
                players.put("Player4", player4.getVariableMap());
            } if (playerTotal == 5){
                Player player5 = board.getPlayers().get(4);
                players.put("Player1", player5.getVariableMap());
            }

            return players;
        } catch (IndexOutOfBoundsException ie){
            Map<String, Object> players = new HashMap<>();
            Player player1 = board.getPlayers().get(0);
            players.put("Player1", player1.getVariableMap());
            return players;
        }

    }

    private Map<String, Object> createKeyValueMapForBoard(Board board){
        Map<String, Object> boardData = new HashMap<>();
        boardData.put("Players", createKeyValueMapForPlayers(board));
        boardData.put("Tiles", createKeyValueMapForTiles(board));
        //.put("Cards", board.cards);
        //boardData.put("Dice", board.dice);
        //boardData.put("Governor", board.governor);
        //boardData.put("Smuggler", board.smuggler);
        return boardData;
    }

    private Map<String, Object> createKeyValueMapForGame(Game game) {
        Map<String, Object> data = new HashMap<>();
//        ArrayList<String> userNames = new ArrayList<>();
//        for (Player player : game.getPlayers()) {
//            userNames.add(player.getName());
//        }

        data.put("gameName", game.getName());
        data.put("playerTotal", game.getPlayerTotal());
        data.put("gameDifficulty", game.getDifficulty().getValue());
        data.put("dateCreated", new Date());
        data.put("gameStarted", game.isGameStarted());
        data.put("gameEnded", game.isGameEnded());
        data.put("turnCounter", game.getTurnCounter());
//        data.put("playerNames", userNames);
        data.put("Board", createKeyValueMapForBoard(game.board));
        return data;
    }

    private void create(String collection, String documentName, Map<String, Object> data) {
        try {
            DocumentReference docRef = db.collection(collection).document(documentName);

            System.out.println("inserting into collection: " + collection + " document: " + documentName + " data: " + data.toString());

            ApiFuture<WriteResult> result = docRef.set(data);
            WriteResult writeResult = result.get();
            System.out.println("create result: " + writeResult);
            System.out.println("create time : " + writeResult.getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFromObject(String collection, String documentName, Object object) {
        try {
            DocumentReference docRef = db.collection(collection).document(documentName);

            System.out.println("inserting into collection: " + collection + " document: " + documentName + " object: " + object);

            ApiFuture<WriteResult> result = docRef.set(object);
            WriteResult writeResult = result.get();
            System.out.println("create result: " + writeResult);
            System.out.println("create time : " + writeResult.getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T getAsObject(String collection, String documentName, Class<T> clazz) {
        try{
            ApiFuture<DocumentSnapshot> future = db.collection(collection).document(documentName).get();
            DocumentSnapshot documentSnapshot = future.get();
            return documentSnapshot.toObject(clazz);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createNewPlayer(Player player) {
        createFromObject("Players", player.getName(), player);
    }

    public Player getPlayer(String name) {
        return getAsObject("Players", name, Player.class);
    }

    // Create Online Game
    public void createNewGame(Game game){
        try{
            Map<String, Object> data = createKeyValueMapForGame(game);
            create("Games", game.getName(), data);
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

    public DocumentSnapshot getGameDataFromFirebase(){
        try{
            System.out.println(db);
            ApiFuture<DocumentSnapshot> future = db.collection("Games").document(GameInformation.getGameInfo().getRoomname()).get();
            DocumentSnapshot documentSnapshot = future.get();
            //Map<String, Object> dataMap = documentSnapshot.getData();
            return documentSnapshot;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Starts a thread that listens for changes in the firebase,
     * and updates the models when it registers a change.
     *
     * @version 21-6-2019
     * @author Edward Deen
     * @param game
     */
    public void startWatchForChangesForGame(Game game) {
        System.out.println("Start watching for changes for game: " + game.getName());
        Runnable runnable = () -> {

            DocumentReference docRef = db.collection("Games").document(game.getName());
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
                        gameController.updateGameData(snapshot);
                    } else {
                        System.out.print("Current data: null");
                    }
                }
            });
        };
        Thread ListenerThread = new Thread(runnable);
        ListenerThread.start();
    }
}
