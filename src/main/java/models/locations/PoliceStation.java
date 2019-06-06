package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.PoliceStationObservable;

import java.util.ArrayList;
import java.util.List;

public class PoliceStation implements Location, PoliceStationObservable {

    // Variables
    private static PoliceStation policeStation;
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
    public static PoliceStation getInstance() {
        if (policeStation == null) {
            policeStation = new PoliceStation();
        }
        return policeStation;
    }
}
