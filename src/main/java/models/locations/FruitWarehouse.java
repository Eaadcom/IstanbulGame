package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FruitWarehouseObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitWarehouse implements Location, FruitWarehouseObservable {

    // Variables
    private static FruitWarehouse fruitWarehouse;
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
    public static FruitWarehouse getInstance() {
        if (fruitWarehouse == null) {
            fruitWarehouse = new FruitWarehouse();
        }
        return fruitWarehouse;
    }
}
