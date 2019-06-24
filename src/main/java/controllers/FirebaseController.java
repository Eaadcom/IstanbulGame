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

/**
 * This is the controller for everything that goes to the Firebase server.
 *
 * @author Edward Deen, Joeri van Duijkeren
 * @version 21-6-2019
 */
public class FirebaseController {

    // Variables
    private static FirebaseController firebaseController;
    private static GameController gameController = GameController.getInstance();
    private Firestore db;
    private Firebase firebase;

    public void initialize() {
        db = firebaseLogin();
    }


    /**
     * Gets all game documents and returns them in a list.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @return
     */
    public List<QueryDocumentSnapshot> fillGameLobby() {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("Games").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            QueryDocumentSnapshot result = null;
            for (QueryDocumentSnapshot queryDocumentSnapshot : documents) {
                if (queryDocumentSnapshot.getId().equals("_A_Test2")) {
                    result = queryDocumentSnapshot;
                    break;
                }
            }

            return documents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Outputs firestore token used to do operations with firebase.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @return
     */
    public Firestore firebaseLogin() {
        try {
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
     * @param game
     * @version 21-6-2019
     * @author Edward Deen
     */
    public void updateGame(Game game) {
        try {
            DocumentReference docRef = db.collection("Games").document(game.getName());
            Map<String, Object> data = createKeyValueMapForGame(game);
            System.out.println("updating data: " + data.toString() + " for game with name: " + game.getName());

            ApiFuture<WriteResult> result = docRef.update(data);
            WriteResult writeResult = result.get();
            System.out.println("Update result: " + writeResult.toString());
            System.out.println("Update time : " + writeResult.getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a map containing all data of one tile and returns it.
     *
     * @version 24-62019
     * @author Edward Deen
     * @param board
     * @return
     */
    private Map<String, Object> createKeyValueMapForTiles(Board board) {
        Map<String, Object> tileData = new HashMap<>();
        tileData.put("BlackMarket", BlackMarket.getInstance().getVariableMap());
        tileData.put("Caravansary", Caravansary.getInstance().getVariableMap());
        tileData.put("FabricWarehouse", FabricWarehouse.getInstance().getVariableMap());
        tileData.put("Fountain", Fountain.getInstance().getVariableMap());

        tileData.put("FruitWarehouse", FruitWarehouse.getInstance().getVariableMap());
        tileData.put("GemstoneDealer", GemstoneDealer.getInstance().getVariableMap());
        tileData.put("GreatMarket", GreatMarket.getInstance().getVariableMap());
        tileData.put("PoliceStation", PoliceStation.getInstance().getVariableMap());

        tileData.put("PostOffice", PostOffice.getInstance().getVariableMap());
        tileData.put("SmallMarket", SmallMarket.getInstance().getVariableMap());
        tileData.put("SmallMosque", SmallMosque.getInstance().getVariableMap());
        tileData.put("SpiceWarehouse", SpiceWarehouse.getInstance().getVariableMap());

        tileData.put("SultanPalace", SultanPalace.getInstance().getVariableMap());
        tileData.put("TeaHouse", TeaHouse.getInstance().getVariableMap());
        tileData.put("Wainwright", Wainwright.getInstance().getVariableMap());
        tileData.put("GreatMosque", GreatMarket.getInstance().getVariableMap());
        return tileData;
    }

    /**
     * Creates a map containing all player data and returns it.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param board
     * @return
     */
    private Map<String, Object> createKeyValueMapForPlayers(Board board) {
        Map<String, Object> playerMap = new HashMap<>();
        List<Player> players = board.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            playerMap.put(Integer.toString(i), players.get(i).getVariableMap());
        }
        return playerMap;
    }

    /**
     * Creates a map containing all board data and returns it.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param board
     * @return
     */
    private Map<String, Object> createKeyValueMapForBoard(Board board) {
        Map<String, Object> boardData = new HashMap<>();
        boardData.put("Players", createKeyValueMapForPlayers(board));
        boardData.put("Tiles", createKeyValueMapForTiles(board));
        boardData.put("playerTiles", createPlayerTiles(board.getPlayerTiles()));
        //.put("Cards", board.cards);
        //boardData.put("Dice", board.dice);
        //boardData.put("Governor", board.governor);
        //boardData.put("Smuggler", board.smuggler);
        return boardData;
    }

    /**
     * Creates a map from maps of tile data and returns it.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param playerTiles
     * @return
     */
    private Map<String, String> createPlayerTiles(Map<Integer, String> playerTiles) {
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : playerTiles.entrySet()) {
            stringMap.put(entry.getKey().toString(), entry.getValue());
        }
        return stringMap;
    }

    /**
     * Creates a map of all game data and returns it.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param game
     * @return
     */
    private Map<String, Object> createKeyValueMapForGame(Game game) {
        Map<String, Object> data = new HashMap<>();

        data.put("gameName", game.getName());
        data.put("playerTotal", game.getPlayerTotal());
        data.put("gameDifficulty", game.getDifficulty().getValue());
        data.put("dateCreated", new Date());
        data.put("gameStarted", game.isGameStarted());
        data.put("gameEnded", game.isGameEnded());
        data.put("turnCounter", game.getTurnCounter());
        data.put("hasMoved", game.isHasMoved());
//        data.put("playerNames", userNames);
        data.put("Board", createKeyValueMapForBoard(game.board));
        return data;
    }

    /**
     *
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param collection
     * @param documentName
     * @param data
     */
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

    /**
     * Creates a new game.
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @param game
     */
    public void createNewGame(Game game) {
        try {
            Map<String, Object> data = createKeyValueMapForGame(game);
            create("Games", game.getName(), data);
        } catch (Exception e) {
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

    /**
     * Gets data from the firebase
     *
     * @author Edward Deen
     * @version 24-6-2019
     * @return
     */
    public DocumentSnapshot getGameDataFromFirebase() {
        try {
            System.out.println(db);
            ApiFuture<DocumentSnapshot> future = db.collection("Games").document(GameInformation.getGameInfo().getRoomname()).get();
            DocumentSnapshot documentSnapshot = future.get();
            //Map<String, Object> dataMap = documentSnapshot.getData();
            return documentSnapshot;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Starts a thread that listens for changes in the firebase,
     * and updates the models when it registers a change.
     *
     * @param game
     * @version 21-6-2019
     * @author Edward Deen
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
