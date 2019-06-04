package models.locations;

import observers.GameViewObserver;
import observers.locations.LargeMarketObservable;

import java.util.ArrayList;
import java.util.List;

public class LargeMarket implements Location, LargeMarketObservable {

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
