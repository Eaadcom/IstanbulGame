package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.WainwrightObservable;

import java.util.ArrayList;
import java.util.List;

public class Wainwright implements Location, WainwrightObservable {

    // Variables
    private static Wainwright wainwright;
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


    private void upgradeCar() {

    }

    // Singleton Pattern
    public static Wainwright getInstance() {
        if (wainwright == null) {
            wainwright = new Wainwright();
        }
        return wainwright;
    }
}
