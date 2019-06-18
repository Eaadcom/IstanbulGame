package observers.locations;

import observers.LocationViewObserver;

public interface GreatMarketObservable {
    void register(LocationViewObserver observer);
    void notifyAllObservers();
}
