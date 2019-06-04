package models.locations;

import observers.GameViewObserver;
import observers.locations.PostOfficeObservable;

import java.util.ArrayList;
import java.util.List;

public class PostOffice implements Location, PostOfficeObservable {

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
