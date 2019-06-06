package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMosqueObservable;

import java.util.ArrayList;
import java.util.List;

public class SmallMosque implements Location, SmallMosqueObservable {

    // Variables
    public static SmallMosque smallMosque;
    private List<LocationViewObserver> observers = new ArrayList<>();

    // Observer Pattern
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
    public static SmallMosque getInstance() {
        if (smallMosque == null) {
            smallMosque = new SmallMosque();
        }
        return smallMosque;
    }
}
