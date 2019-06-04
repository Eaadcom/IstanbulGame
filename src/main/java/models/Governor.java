package models;

import observers.GameViewObserver;
import observers.GovernorObservable;

import java.util.ArrayList;
import java.util.List;

public class Governor implements GovernorObservable {
    private List<GameViewObserver> observers = new ArrayList<>();


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
