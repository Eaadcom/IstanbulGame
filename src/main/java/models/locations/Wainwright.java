package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.WainwrightObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wainwright implements Location, WainwrightObservable {
    private static Wainwright wainwright;
    public int Location = 16;


    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("Location", Location);

        return Data;
    }

    public void setData(Map variables){
        this.Location = Math.toIntExact((long) variables.get("Location"));
    }

    // Variables
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
    public static Wainwright getInstance() {
        if (wainwright == null) {
            wainwright = new Wainwright();
        }
        return wainwright;
    }
}
