package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.LargeMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class LargeMarket implements Location, LargeMarketObservable {

    // Variables
    private static LargeMarket largeMarket;
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
    public static LargeMarket getInstance() {
        if (largeMarket == null) {
            largeMarket = new LargeMarket();
        }
        return largeMarket;
    }
}
