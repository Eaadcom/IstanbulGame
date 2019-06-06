package observers.locations;

import observers.LocationViewObserver;

public interface SmallMosqueObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
