package observers.locations;

import observers.LocationViewObserver;

public interface SmallMarketObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
