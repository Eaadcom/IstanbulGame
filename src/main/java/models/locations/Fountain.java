package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FountainObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fountain implements Location, FountainObservable {

    // Variables
    public int location = 4;
    private static Fountain fountain;
    private List<LocationViewObserver> observers = new ArrayList<>();

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("Location", location);

        return Data;
    }

    public void setData(Map variables){
        this.location = (int) variables.get("Location");
    }

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
