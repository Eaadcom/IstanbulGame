package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GemstoneDealerObservable;

import java.util.ArrayList;
import java.util.List;

public class GemstoneDealer implements Location, GemstoneDealerObservable {

    // Variables
    private static GemstoneDealer gemstoneDealer;
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
    public static GemstoneDealer getInstance() {
        if (gemstoneDealer == null) {
            gemstoneDealer = new GemstoneDealer();
        }
        return gemstoneDealer;
    }
}
