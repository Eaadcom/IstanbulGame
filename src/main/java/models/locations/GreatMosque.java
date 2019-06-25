package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.GreatMosqueObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreatMosque implements Location, GreatMosqueObservable {

    // Variables
    private static GreatMosque greatMosque;
    public int bluePrice = 2;
    public int yellowPrice = 2;
    private List<LocationViewObserver> observers = new ArrayList<>();


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
    public static GreatMosque getInstance() {
        if (greatMosque == null) {
            greatMosque = new GreatMosque();
        }
        return greatMosque;
    }
}
