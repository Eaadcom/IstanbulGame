package observers.locations;

import observers.LocationViewObserver;

public interface FabricWarehouseObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
