package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.TeaHouseObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaHouse implements Location, TeaHouseObservable {

    // Variables
    private static TeaHouse teaHouse;
    public int Location = 15;


    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("Location", Location);

        return Data;
    }

    public void setData(Map variables){
        this.Location = Math.toIntExact((long) variables.get("Location"));
    }


    public int teahouseNumberChoice;
    public int teahouseDiceNumber;
    private List<LocationViewObserver> observers = new ArrayList<>();


    // Constructor
    public TeaHouse(){
    }

    //setter
    public void setTeahouseNumberChoice(int teahouseNumberChoice) {
        this.teahouseNumberChoice = teahouseNumberChoice;
    }

    //getter
    public int getTeahouseNumberChoice() {
        return teahouseNumberChoice;
    }

    public void setTeahouseDiceNumber(int teahouseDiceNumber) {
        this.teahouseDiceNumber = teahouseDiceNumber;
    }

    public int getTeahouseDiceNumber(){
        return teahouseDiceNumber;
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
    public static TeaHouse getInstance() {
        if (teaHouse == null) {
            teaHouse = new TeaHouse();
        }
        return teaHouse;
    }
}


