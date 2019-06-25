package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GreatMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class LargeMarket implements Location {

    // Variables
    private static LargeMarket largeMarket;
    private List<LocationViewObserver> observers = new ArrayList<>();




    // Singleton Pattern
    public static LargeMarket getInstance() {
        if (largeMarket == null) {
            largeMarket = new LargeMarket();
        }
        return largeMarket;
    }
}
