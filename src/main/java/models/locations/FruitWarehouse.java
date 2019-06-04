package models.locations;

import observers.GameViewObserver;
import observers.locations.FruitWarehouseObservable;

import java.util.ArrayList;
import java.util.List;

public class FruitWarehouse implements Location, FruitWarehouseObservable {

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
