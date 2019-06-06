package observers.locations;

import observers.LocationViewObserver;

public interface BlackMarketObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
