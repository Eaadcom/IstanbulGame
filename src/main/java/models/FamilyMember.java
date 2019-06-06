package models;

import observers.FamilyMemberObservable;
import observers.GameViewObserver;

import java.util.ArrayList;
import java.util.List;

public class FamilyMember implements FamilyMemberObservable {

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
