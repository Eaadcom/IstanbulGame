package models;

import observers.GameViewObserver;
import observers.SmugglerObservable;

import java.util.ArrayList;
import java.util.List;

public class Smuggler implements SmugglerObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();

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
