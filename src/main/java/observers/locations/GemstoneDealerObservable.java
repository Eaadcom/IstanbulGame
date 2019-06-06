package observers.locations;

import observers.LocationViewObserver;

public interface GemstoneDealerObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
