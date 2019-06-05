package models.locations;

import observers.GameViewObserver;
import observers.locations.SmallMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class SmallMarket implements Location, SmallMarketObservable {

    private ArrayList<String> demandTiles = new ArrayList<>();

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
