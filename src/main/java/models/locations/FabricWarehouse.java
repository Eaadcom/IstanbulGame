package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FabricWarehouseObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabricWarehouse implements Location, FabricWarehouseObservable {

    // Variables
    private static FabricWarehouse fabricWarehouse;
    private List<LocationViewObserver> observers = new ArrayList<>();


    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        return Data;
    }

    public void setData(Map variables){
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
    public static FabricWarehouse getInstance() {
        if (fabricWarehouse == null) {
            fabricWarehouse = new FabricWarehouse();
        }
        return fabricWarehouse;
    }
}
