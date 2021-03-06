package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SpiceWarehouseObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiceWarehouse implements Location, SpiceWarehouseObservable {

    // Variables
    private static SpiceWarehouse spiceWarehouse;
    private List<LocationViewObserver> observers = new ArrayList<>();   public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        return Data;
    }

    public void setData(Map variables){
    }



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
