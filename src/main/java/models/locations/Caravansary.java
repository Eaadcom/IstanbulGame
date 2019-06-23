package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.CaravansaryObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caravansary implements Location, CaravansaryObservable {

    // Variables
    private List<LocationViewObserver> observers = new ArrayList<>();
    private int Location = 2;
    private int PlayerCardChoice;
    ArrayList usedBonusCards = new ArrayList();
    private static Caravansary caravansary;


    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("Location", Location); Data.put("usedBonusCards", usedBonusCards);

        return Data;
    }

    public void setData(Map variables){
        this.Location = Math.toIntExact((Long) variables.get("Location"));
    }



    // Constructor
    public Caravansary(){
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
    public static Caravansary getInstance() {
        if (caravansary == null) {
            caravansary = new Caravansary();
        }
        return caravansary;
    }
}
