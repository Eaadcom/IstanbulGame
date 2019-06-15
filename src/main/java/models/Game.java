package models;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import observers.GameObservable;
import observers.GameViewLobbyViewObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game implements GameObservable {

    // Variables
    private List<GameViewLobbyViewObserver> observers = new ArrayList<>();
    private String name;
    private Board board = new Board();
    private Integer playerTotal;
    private boolean gameStarted = false;
    private boolean gameEnded = false;
    private Difficulty difficulty;
    private int turnCounter = 0;

    public int myPlayerID = 1;


    public boolean hasMoved = false;


    public Game(String name, int playerTotal, Difficulty difficulty) {
        this.name = name;
        this.playerTotal = playerTotal;
        this.difficulty = difficulty;
    }

    public Game(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        this.name = document.getId();
        setGameData(data);
    }

    public void updateFromSnapShot(DocumentSnapshot documentSnapshot) {
        this.name = documentSnapshot.getId();
        Map<String, Object> data = documentSnapshot.getData();
        setPlayers(data.get("playerNames"));
        setGameData(data);
        notifyAllObservers();
    }

    private void setPlayers(Object playerNames) {
        List<String> names = (List<String>) playerNames;
        for(String name : names) {
            board.addPlayer(new Player(name));
        }
    }

    private void setGameData(Map<String, Object> data) {
        this.playerTotal = Integer.parseInt(data.get("playerTotal").toString());
        this.gameStarted = Boolean.parseBoolean(data.get("gameStarted").toString());
        this.gameEnded = Boolean.parseBoolean(data.get("gameEnded").toString());
        this.difficulty = Difficulty.fromString(data.get("gameDifficulty").toString());
        this.turnCounter = Integer.parseInt(data.get("turnCounter").toString());
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setHasMoved(Boolean b) {
        hasMoved = b;
    } //ik was hier gebleven

    //Getters
    public Integer getPlayerTotal(){
        return playerTotal;
    }
    public int getMyPlayerID(){
        return myPlayerID;
    }

    // Observer pattern
    @Override
    public void register(GameViewLobbyViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewLobbyViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    public void startGame() {
        gameStarted = true;
        notifyAllObservers();
    }

    public void endGame() {
        gameEnded = true;
        notifyAllObservers();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void increaseTurnCounter() {
        turnCounter++;
        notifyAllObservers();
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    public Player getCurrentPlayerTurn() {
        return board.getCurrentPlayerTurn();
    }

    public void addPlayer(Player player) {
        board.addPlayer(player);
        notifyAllObservers();
    }

    public Player getPlayer() {
        return board.getPlayer();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
}
