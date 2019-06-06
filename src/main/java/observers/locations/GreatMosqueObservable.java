package observers.locations;

import observers.LocationViewObserver;

public interface GreatMosqueObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
