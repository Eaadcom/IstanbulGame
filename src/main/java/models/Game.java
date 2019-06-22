package models;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import observers.GameObservable;
import observers.GameViewLobbyViewObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is the model for the most important game elements
 * @author Stan Hogenboom, Thomas van Velzen, Edward Deen, Joeri van Duijkeren, Floris Dekker
 * @version 21-6-2019
 */
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
    public boolean hasMoved = false;

    // SystemVariables
    private  List<GameViewLobbyViewObserver> observers = new ArrayList<>();
    public boolean removeDoubleGames = true;


    public void nextTurn(){
        if (turnCounter == board.players.size() - 1){
            turnCounter = 0;
        } else{
            turnCounter++;
        }
    }

    public Game() {

    }

    public Game(String name, int playerTotal, Difficulty difficulty) {
        this.name = name;
        this.playerTotal = playerTotal;
        this.difficulty = difficulty;
    }

    public void updateFromSnapShot(DocumentSnapshot documentSnapshot) {
        this.name = documentSnapshot.getId();
        Map<String, Object> data = documentSnapshot.getData();
        setGameData(data);
        board.setBoardData((Map)data.get("Board"));
        //setPlayers(data.get("playerNames"));
        notifyAllObservers();
    }

    private void setGameData(Map<String, Object> data) {
        this.playerTotal = Integer.parseInt(data.get("playerTotal").toString());
        this.gameStarted = Boolean.parseBoolean(data.get("gameStarted").toString());
        this.gameEnded = Boolean.parseBoolean(data.get("gameEnded").toString());
        this.difficulty = Difficulty.fromString(data.get("gameDifficulty").toString());
        this.turnCounter = Integer.parseInt(data.get("turnCounter").toString());
        this.hasMoved = Boolean.parseBoolean(data.get("hasMoved").toString());
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setHasMoved(Boolean b) {
        hasMoved = b;
    } //ik was hier gebleven

    public boolean isHasMoved() {
        return hasMoved;
    }

    //Getters
    public Integer getPlayerTotal(){
        return playerTotal;
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

    public void increaseTurnCounter() {
        nextTurn();
        notifyAllObservers();
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
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

    public void updatePlayerTile(String tileString, int playerID) {
        board.updatePlayerTile(tileString, playerID);
    }
}
