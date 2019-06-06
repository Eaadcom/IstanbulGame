package observers.locations;

import observers.LocationViewObserver;

public interface FountainObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
