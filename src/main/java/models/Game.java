package models;

import observers.GameObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    private String name;

    private Board board = new Board();
    private int playerTotal = 2;
    public int turnCounter = playerTotal;
    public static boolean gameEnd = false;
    public int myPlayerID = 1;
    public boolean hasMoved = false;
    private Difficulty difficulty;

    public Game(String name, int playerTotal, Difficulty difficulty) {
        this.name = name;
        this.playerTotal = playerTotal;
        this.difficulty = difficulty;
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
    public boolean getGameEnd(){
        return gameEnd;
    }
    public int getMyPlayerID(){
        return myPlayerID;
    }

    // Observer pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    public void increaseTurnCounter() {
        turnCounter++;
        notifyAllObservers();
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    public Player getCurrentPlayerTurn() {
        return board.getCurrentPlayerTurn();
    }

    public void addPlayer(Player player) {
        board.addPlayer(player);
    }

    public Player getPlayer() {
        return board.getPlayer();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
