package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SultanPalaceObservable;

import java.util.ArrayList;
import java.util.List;

public class SultanPalace implements Location, SultanPalaceObservable {

    // Variables
    private static SultanPalace sultanPalace;
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
    public static SultanPalace getInstance() {
        if (sultanPalace == null) {
            sultanPalace = new SultanPalace();
        }
        return sultanPalace;
    }
}
