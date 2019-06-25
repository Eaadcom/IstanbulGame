package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SmallMosqueObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallMosque implements Location, SmallMosqueObservable {

    // Variables
    public static SmallMosque smallMosque;
    private List<LocationViewObserver> observers = new ArrayList<>();
    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

    public int getRedPrice() {
        return redPrice;
    }

    public int getGreenPrice() {
        return greenPrice;
    }

    public int redPrice =2;
    public int greenPrice =2;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        return Data;
    }

    public void setData(Map variables){
    }

    public void incrementPrice(String color){
        if(color == "red" && redPrice != 5){
            redPrice++;
        }else if(color == "green" && greenPrice != 5){
            greenPrice++;
        }
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
    public static SmallMosque getInstance() {
        if (smallMosque == null) {
            smallMosque = new SmallMosque();
        }
        return smallMosque;
    }
}
