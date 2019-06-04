package models;

import observers.AssistantObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class Assistant implements AssistantObservable {
    private int location;

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
