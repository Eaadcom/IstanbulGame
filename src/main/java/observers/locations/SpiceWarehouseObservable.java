package observers.locations;

import observers.LocationViewObserver;

public interface SpiceWarehouseObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
