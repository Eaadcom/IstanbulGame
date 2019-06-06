package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FountainObservable;

import java.util.ArrayList;
import java.util.List;

public class Fountain implements Location, FountainObservable {

    // Variables
    private static Fountain fountain;
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
    public static Fountain getInstance() {
        if (fountain == null) {
            fountain = new Fountain();
        }
        return fountain;
    }
}
