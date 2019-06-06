package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class SmallMarket implements Location, SmallMarketObservable {

    // Variables
    private static SmallMarket smallMarket;
    private ArrayList<String> demandTiles = new ArrayList<>();
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
    public static SmallMarket getInstance() {
        if (smallMarket == null) {
            smallMarket = new SmallMarket();
        }
        return smallMarket;
    }
}
