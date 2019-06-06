package models.locations;

import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.PostOfficeObservable;

import java.util.ArrayList;
import java.util.List;

public class PostOffice implements Location, PostOfficeObservable {

    // Variables
    private static PostOffice postOffice;
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
    public static PostOffice getInstance() {
        if (postOffice == null) {
            postOffice = new PostOffice();
        }
        return postOffice;
    }
}
