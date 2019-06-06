package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GreatMosqueObservable;

import java.util.ArrayList;
import java.util.List;

public class GreatMosque implements Location, GreatMosqueObservable {

    // Variables
    private static GreatMosque greatMosque;
    private List<LocationViewObserver> observers = new ArrayList<>();

    // Observer pattern
    @Override
    public void register(LocationViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (LocationViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    // Singleton Pattern
    public static GreatMosque getInstance() {
        if (greatMosque == null) {
            greatMosque = new GreatMosque();
        }
        return greatMosque;
    }
}
