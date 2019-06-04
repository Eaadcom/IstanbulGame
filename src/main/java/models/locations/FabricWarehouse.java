package models.locations;

import observers.GameViewObserver;
import observers.locations.FabricWarehouseObservable;

import java.util.ArrayList;
import java.util.List;

public class FabricWarehouse implements Location, FabricWarehouseObservable {

    private List<GameViewObserver> observers = new ArrayList<>();


    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }

}
