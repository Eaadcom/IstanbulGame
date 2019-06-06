package observers.locations;

import observers.LocationViewObserver;

public interface LargeMarketObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
