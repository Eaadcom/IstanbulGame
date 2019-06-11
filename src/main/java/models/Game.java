package models;

import observers.GameObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    private Board board = new Board();
    private int playerTotal = 3;
    public int TURNCOUNTER = playerTotal;
    public static boolean gameEnd = false;
    public int myPlayerID = 1;

    // Setters
    public void setPlayerTotal(int plt){
        playerTotal = plt;
    }

    //Getters
    public int getPlayerTotal(){
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
}
