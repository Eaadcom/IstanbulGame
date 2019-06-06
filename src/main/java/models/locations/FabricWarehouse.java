package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.FabricWarehouseObservable;

import java.util.ArrayList;
import java.util.List;

public class FabricWarehouse implements Location, FabricWarehouseObservable {

    // Variables
    private static FabricWarehouse fabricWarehouse;
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
    public static FabricWarehouse getInstance() {
        if (fabricWarehouse == null) {
            fabricWarehouse = new FabricWarehouse();
        }
        return fabricWarehouse;
    }
}
