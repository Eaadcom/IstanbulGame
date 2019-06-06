package models;

import observers.GameObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    private int playerTotal;
    private Board board = new Board();

    // Setters
    public void setPlayerTotal(int plt){
        playerTotal = plt;
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
