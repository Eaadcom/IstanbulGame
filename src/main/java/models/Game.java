package models;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import observers.GameObservable;
import observers.GameViewLobbyViewObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game implements GameObservable {

    // LobbyVariables
    public String name;
    public Integer playerTotal;
    public boolean gameStarted = false;
    public boolean gameEnded = false;
    public Difficulty difficulty;

    // GameVariables
    public Board board = new Board();
    public int turnCounter = 0;
    public int myPlayerID = 1;
    public boolean hasMoved = false;

    // SystemVariables
    private  List<GameViewLobbyViewObserver> observers = new ArrayList<>();

    public void nextTurn(){
        if (turnCounter == getPlayerTotal() - 1){
            turnCounter = 0;
        } else{
            turnCounter++;
        }
        notifyAllObservers();
    }

    public Game(String name, int playerTotal, Difficulty difficulty) {
        this.name = name;
        this.playerTotal = playerTotal;
        this.difficulty = difficulty;
    }

    public Game(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        this.name = document.getId();
        //setPlayers(data.get("playerNames"));
        setGameData(data);
    }

    public void updateFromSnapShot(DocumentSnapshot documentSnapshot) {
        this.name = documentSnapshot.getId();
        Map<String, Object> data = documentSnapshot.getData();
        board.setBoardData((Map)data.get("Board"));
        //setPlayers(data.get("playerNames"));
        setGameData(data);
        notifyAllObservers();
    }

    private void setPlayers(Object playerNames) {
        List<String> names = (List<String>) playerNames;
        List<Player> players = new LinkedList<>();
        for(String name : names) {
            players.add(new Player(name));
        }
        board.setPlayers(players);
    }

    private void setGameData(Map<String, Object> data) {
        this.playerTotal = Integer.parseInt(data.get("playerTotal").toString());
        this.gameStarted = Boolean.parseBoolean(data.get("gameStarted").toString());
        this.gameEnded = Boolean.parseBoolean(data.get("gameEnded").toString());
        this.difficulty = Difficulty.fromString(data.get("gameDifficulty").toString());
        this.turnCounter = Integer.parseInt(data.get("turnCounter").toString());
        notifyAllObservers();
    }

    public String getName() {
        return name;
    }

    public int getPlayerID(){
        return myPlayerID;
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
        for (GameViewLobbyViewObserver gvo : new ArrayList<>(observers)){
            gvo.update(this);
        }
    }

    public void startGame() {
        gameStarted = true;
        //board.shufflePlayers();
        notifyAllObservers();
    }

    public void endGame() {
        gameEnded = true;
        notifyAllObservers();
    }

    public void pauseGame(){
        gameStarted = false;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

//    public void increaseTurnCounter() {
//        turnCounter++;
//        notifyAllObservers();
//    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    public Player getCurrentPlayerTurn() {
        return board.getCurrentPlayerTurn();
    }

    public void addInitialPlayer(Player player) {
        board.addPlayer(player);
    }

    public void addPlayer(Player player){
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
