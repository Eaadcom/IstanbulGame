package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SpiceWarehouseObservable;

import java.util.ArrayList;
import java.util.List;

public class SpiceWarehouse implements Location, SpiceWarehouseObservable {

    // Variables
    private static SpiceWarehouse spiceWarehouse;
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
    public static SpiceWarehouse getInstance() {
        if (spiceWarehouse == null) {
            spiceWarehouse = new SpiceWarehouse();
        }
        return spiceWarehouse;
    }
}
