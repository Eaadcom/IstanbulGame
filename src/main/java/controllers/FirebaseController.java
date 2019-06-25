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


    // gets all game documents and returns them in a list
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

    //TODO can i remove this? -Ed
    public void saveLobbyInfo(List<QueryDocumentSnapshot> documents) {
        ArrayList<Map> documentsList = new ArrayList<Map>();

        for (QueryDocumentSnapshot document : documents) {
            documentsList.add(document.getData());
        }
    }

    // Outputs firestore token used to do operations with firebase
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
     * @author Edward Deen, Joeri
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
     * Nethode om alle properties van de spelers op te slaan in een map
     * @author Joeri
     * @version 12 june
     */
    private Map<String, Object> createKeyValueMapForPlayers(Board board) {
//        try {
            Map<String, Object> playerMap = new HashMap<>();
            List<Player> players = board.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                playerMap.put(Integer.toString(i), players.get(i).getVariableMap());
            }
            return playerMap;

//
//            int playerTotal = GameController.getInstance().getGame().getPlayerTotal();
//            int playerListLength = gameController.getInstance().getGame().board.players.size();
//
//            Player player1 = board.getPlayers().get(0);
//            players.put("Player1", player1.getVariableMap());
//            if (playerListLength > 1) {
//                Player player2 = board.getPlayers().get(1);
//                players.put("Player2", player2.getVariableMap());
//            }
//            if (playerTotal >= 3) {
//                Player player3 = board.getPlayers().get(2);
//                players.put("Player3", player3.getVariableMap());
//            }
//            if (playerTotal >= 4) {
//                Player player4 = board.getPlayers().get(3);
//                players.put("Player4", player4.getVariableMap());
//            }
//            if (playerTotal == 5) {
//                Player player5 = board.getPlayers().get(4);
//                players.put("Player1", player5.getVariableMap());
//            }
//
//            return players;
//        } catch (IndexOutOfBoundsException ie) {
//            Map<String, Object> players = new HashMap<>();
//            Player player1 = board.getPlayers().get(0);
//            players.put("Player1", player1.getVariableMap());
//            return players;
//        }

    }

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
     * Methode om de positie van de spelers op te slaan
     * @author Joeri
     * @param playerTiles de posities van de spelers
     * @version 12 june
     */
    private Map<String, String> createPlayerTiles(Map<Integer, String> playerTiles) {
        Map<String, String> stringMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : playerTiles.entrySet()) {
            stringMap.put(entry.getKey().toString(), entry.getValue());
        }
        return stringMap;
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
        data.put("hasMoved", game.isHasMoved());
//        data.put("playerNames", userNames);
        data.put("Board", createKeyValueMapForBoard(game.board));
        return data;
    }

    /**
     * Methode om een document aan te maken met data, in een meegegeven collection
     * @author Joeri
     * @param collection de collection naam waarin opgeslagen wordt
     * @param documentName de naam van de game
     * @param data de data van de game
     * @version 12 june
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

    // Create Online Game
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
