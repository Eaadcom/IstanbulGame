package observers.locations;

import observers.LocationViewObserver;

public interface FruitWarehouseObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
